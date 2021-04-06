# Chapter 4 Docker 数据管理

## 目录

* 数据卷
  * 创建数据卷
  * 启动一个挂载数据卷的容器
  * 查看数据卷的具体信息
  * 删除数据卷
* 挂载主机目录
  * 挂载一个主机目录作为数据卷
  * 查看数据卷的具体信息
  * 挂载一个本地主机文件作为数据卷


## 数据卷

数据卷是一个可供一个或多个容器使用的特殊目录，它绕过 UFS (UNIX File System) ，可以提供很多有用的特性：

* 数据卷可以在容器之间共享和重用

* 对数据卷的修改会立马生效

* 对数据卷的更新，不会影响镜像

* 数据卷默认会一直存在，即使容器被删除

>注意：数据卷的使用，类似于 Linux 下对目录或文件进行 mount，镜像中的被指定为挂载点的目录中的文件会复制到数据卷中（仅数据卷为空时会复制）。

### 创建一个数据卷

```bash
$ docker volume create datawhale
```

查看所有的数据卷

```bash
$ docker volume ls

DRIVER              VOLUME NAME
local               datawhale
```

在主机里使用以下命令可以查看指定数据卷的信息

```bash
$ docker volume inspect datawhale
[
    {
        "Driver": "local",
        "Labels": {},
        "Mountpoint": "/var/lib/docker/volumes/datawhale/_data",
        "Name": "datawhale",
        "Options": {},
        "Scope": "local"
    }
]
```

### 启动一个挂载数据卷的容器

在用 `docker run` 命令的时候，使用 `--mount` 标记来将数据卷挂载到容器里。在一次 `docker run` 中可以挂载多个 `数据卷`。

下面创建一个名为 `web` 的容器，并加载一个数据卷到容器的 `/usr/share/nginx/html` 目录。

```bash
$ docker run -d -P \
    --name web \
    --mount source=datawhale,target=/usr/share/nginx/html \
    nginx:alpine
```
>–-mount参数说明：   
> source ：数据卷   
> target ：是容器内文件系统挂载点

>注意，可以不需要提前创建好数据卷，直接在运行容器的时候mount 这时如果不存在指定的数据卷，docker会自动创建，自动生成。

### 查看数据卷的具体信息

在主机里使用以下命令可以查看 `web` 容器的信息

```bash
$ docker inspect web
```

`数据卷` 信息在 "Mounts" Key 下面

```json
"Mounts": [
    {
        "Type": "volume",
        "Name": "datawhale",
        "Source": "/var/lib/docker/volumes/datawhale/_data",
        "Destination": "/usr/share/nginx/html",
        "Driver": "local",
        "Mode": "",
        "RW": true,
        "Propagation": ""
    }
],
```

### 删除数据卷

```bash
$ docker volume rm datawhale  #datawhale为卷名
```

数据卷是被设计用来持久化数据的，它的生命周期独立于容器，Docker 不会在容器被删除后自动删除 `数据卷`，并且也不存在垃圾回收这样的机制来处理没有任何容器引用的 `数据卷`。如果需要在删除容器的同时移除数据卷。可以在删除容器的时候使用 `docker rm -v` 这个命令。

无主的数据卷可能会占据很多空间，要清理请使用以下命令

```bash
$ docker volume prune
```

## 挂载主机目录

### 挂载一个主机目录作为数据卷

使用 `--mount` 标记可以指定挂载一个本地主机的目录到容器中去。

```bash
$ docker run -d -P \
    --name web \
    --mount type=bind,source=/src/webapp,target=/usr/share/nginx/html \
    nginx:alpine
```

上面的命令加载主机的 `/src/webapp` 目录到容器的 `/usr/share/nginx/html`目录。这个功能在进行测试的时候十分方便，比如用户可以放置一些程序到本地目录中，来查看容器是否正常工作。本地目录的路径必须是绝对路径，以前使用 `-v` 参数时如果本地目录不存在 Docker 会自动为你创建一个文件夹，现在使用 `--mount` 参数时如果本地目录不存在，Docker 会报错。

Docker 挂载主机目录的默认权限是 `读写`，用户也可以通过增加 `readonly` 指定为 `只读`。

>注意： 如果挂载的目录不存在，创建容器时，docker 不会自动创建，此时会报错

```bash
$ docker run -d -P \
    --name web \
    --mount type=bind,source=/src/webapp,target=/usr/share/nginx/html,readonly \
    nginx:alpine
```

加了 `readonly` 之后，就挂载为 `只读` 了。如果你在容器内 `/usr/share/nginx/html` 目录新建文件，会显示如下错误

```bash
/usr/share/nginx/html # touch new.txt
touch: new.txt: Read-only file system
```

### 查看数据卷的具体信息

在主机里使用以下命令可以查看 `web` 容器的信息

```bash
$ docker inspect web
```

`挂载主机目录` 的配置信息在 "Mounts" Key 下面

```json
"Mounts": [
    {
        "Type": "bind",
        "Source": "/src/webapp",
        "Destination": "/usr/share/nginx/html",
        "Mode": "",
        "RW": true,
        "Propagation": "rprivate"
    }
],
```

### 挂载一个本地主机文件作为数据卷

`--mount` 标记也可以从主机挂载单个文件到容器中

```bash
$ docker run --rm -it \
   --mount type=bind,source=$HOME/.bash_history,target=/root/.bash_history \
   ubuntu:18.04 \
   bash
```

## 参考文献

- [Docker数据管理](https://vuepress.mirror.docker-practice.com/data_management/)