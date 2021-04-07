# Chapter 6 Docker Compose

相信大家学完之前的内容已经对docker的操作很熟悉了，但是有没有一种感觉，如果我一个项目要起好多个容器，每个容器之间又相互之间有一些关联，有些情况下又要修改一些容器，这种情况写起来会特别的麻烦，那么有没有一种方式能让我把项目快速的启动起来呢？

答案肯定是有的，接下来就让我们学习一下docker compose。

关于docker compose的安装请移步[docker compose安装与卸载](https://vuepress.mirror.docker-practice.com/compose/install/)或根据[docker官网](https://docs.docker.com/compose/install/)进行安装。

> 对于docker compose的学习推荐大家多看看一些项目的docker-compose.yml文件是怎么写的，慢慢模仿着去写很多就越来越熟练清晰了。在[Compose文件夹](https://github.com/datawhalechina/team-learning-program/tree/master/Docker/Compose)下也在网上收集了一些docker-compose.yml文件，还有**[awesome-compose](https://github.com/docker/awesome-compose)**这个项目也非常推荐。

## 目录

- 什么是docker compose
- 如何使用docker compose
  - web应用
- docker compose基本使用
  - 启动服务
  - 查看服务状态
  - 停止或删除服务
  - 进入服务
  - 查看服务输出日志
- Compose模板文件
  - build
  - depends_on
  - environment
  - expose
  - ports
  - secrets
  - image
  - labels
  - network_mode
  - networks
  - volumes
- Compose命令
  - 命令对象与格式
  - 命令选项

## 什么是docker compose

通过之前的介绍，我们知道使用一个 `Dockerfile` 模板文件，可以让用户很方便的定义一个单独的应用容器。然而，在日常工作中，经常会碰到需要多个容器相互配合来完成某项任务的情况。例如要实现一个 Web 项目，除了 Web 服务容器本身，往往还需要再加上后端的数据库服务容器，甚至还包括负载均衡容器等。

`Compose`恰好满足了这样的需求。它允许用户通过一个单独的 `docker-compose.yml` 模板文件（YAML 格式）来定义一组相关联的应用容器为一个项目（project）。不理解没关系，我们先看下面这样一个文件：

![](https://tva1.sinaimg.cn/large/008eGmZEly1goup5rlyyuj30g40ykq6b.jpg)

通过这个例子我们可以发现，这个文件里面我们好像看见了image、ports、networks这些，那么这些标签与之前docker run时候的一些指令是不是有一些关系呢？接下来就让我们继续学习。

## 如何使用docker compose

在`Compose` 中有两个重要的概念：

- 服务 (`service`)：一个应用的容器，实际上可以包括若干运行相同镜像的容器实例。
- 项目 (`project`)：由一组关联的应用容器组成的一个完整业务单元，在 `docker-compose.yml` 文件中定义。

`Compose`的默认管理对象是项目，也就是通过docker-compose.yml定义的一组服务集合，通过一些命令来对项目中的一组容器进行便捷地生命周期管理。

下面我们来看一个真实的场景，在该场景下我们是通过Python来写一个能够记录页面访问次数的 web 网站。完整代码：[计数器](./Compose/计数器)

### web 应用

新建文件夹，在该目录中编写 `app.py` 文件

```python
from flask import Flask
from redis import Redis
import os
import socket

app = Flask(__name__)
redis = Redis(host=os.environ.get('REDIS_HOST', '127.0.0.1'), port=6379)


@app.route('/')
def hello():
    redis.incr('hits')
    return 'Hello Container World! I have been seen %s times and my hostname is %s.\n' % (redis.get('hits'),socket.gethostname())


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=True)
```

### Dockerfile

编写 `Dockerfile` 文件，内容为

```docker
FROM python:2.7
COPY . /app
WORKDIR /app
RUN pip install flask redis
EXPOSE 5000
CMD [ "python", "app.py" ]
```

### docker-compose.yml

编写 `docker-compose.yml` 文件，这个是 Compose 使用的主模板文件。

```yaml
version: "3"

services:

  redis:
    image: redis

  web:
    build:
      context: .
      dockerfile: Dockerfile
    environment:
      REDIS_HOST: redis
```

### 运行 compose 项目

```bash
$ docker-compose up -d
```

此时访问本地 `5000` 端口，每次刷新页面，计数就会加 1。

## docker compose基本使用

### 启动服务

在创建好`docker-compose.yml`文件后，可以通过下面这个命令将文件中定义的容器都启动起来，在docker compose中我们更习惯于将每一个容器叫做service。

```bash
docker-compose up
```

命令后会自动接一个默认值`-f docker-compose.yml`，也就是默认是使用docker-compose.yml文件的。我们也可以给文件起名为`docke-test.yml`，这样在使用时指定文件名，但是为了符合规范，还是统一为`docker-compose.yml`。

```bash
docker-compose up -f docer-test.yml
```

**但是直接通过这种方式的话会直接将启动时的输出打印到终端，所以我们常会加上`-d`参数。**

```bash
docker-compose up -d
```

### 查看服务状态

接下来可以查看一下我们创建的service状态

```
docker-compose ps
```

要是想要查看所有service的状态可以使用-a参数：

```
docker-compose ps -a
```

### 停止或删除服务

如何停止已经运行的services呢，可以使用以下两个命令

```
docker-compose stop
docker-compose down
```

其中stop是直接停止services，而down则会停止并删除创建的service，volume和network。

### 进入服务

有些情况下我们还需要进入容器来执行一些命令，可以通过如下方式进入容器

```
docker-compose exec mysql bash
```

exec后面接的就是我们要进入具体的service的名字，名字后面就是我们要执行的命令。

### 查看服务输出日志

有些情况下一些服务可能无法正常启动，这时可以使用命令查看日志并定位发生错误的原因

```
docker-compose logs
```

以上的一些操作就能满足你在大多数情况下的场景了，但是对于一些我们个人的应用还需要详细的编写dock er-compose.yml文件才行，下面我们就更详细的学习一下。

## Compose模板文件

**模板文件是使用 `Compose` 的核心**，涉及到的指令关键字也比较多。但大家不用担心，这里面大部分指令跟 `docker run` 相关参数的含义都是类似的。

*注：这里仅介绍一些较为常用的指令，更多指令请见：[Compose模板文件](https://vuepress.mirror.docker-practice.com/compose/compose_file/#cap-add-cap-drop)*

默认的模板文件名称为 `docker-compose.yml`，格式为 YAML 格式。

```yaml
version: "3"

services:
  webapp:
    image: examples/web
    ports:
      - "80:80"
    volumes:
      - "/data"
```

注意每个服务都必须通过 `image` 指令指定镜像或 `build` 指令（需要 Dockerfile）等来自动构建生成镜像。

如果使用 `build` 指令，在 `Dockerfile` 中设置的选项(例如：`CMD`, `EXPOSE`, `VOLUME`, `ENV` 等) 将会自动被获取，无需在 `docker-compose.yml` 中重复设置。

下面分别介绍各个指令的用法。

### build

指定 `Dockerfile` 所在文件夹的路径（可以是绝对路径，或者相对 docker-compose.yml 文件的路径）。 `Compose` 将会利用它自动构建这个镜像，然后使用这个镜像。

```yaml
version: '3'
services:

  webapp:
    build: ./dir
```

你也可以使用 `context` 指令指定 `Dockerfile` 所在文件夹的路径。使用 `dockerfile` 指令指定 `Dockerfile` 文件名。使用 `arg` 指令指定构建镜像时的变量。

```yaml
version: '3'
services:

  webapp:
    build:
      context: ./dir
      dockerfile: Dockerfile-alternate
      args:
        buildno: 1
```

使用 `cache_from` 指定构建镜像的缓存。

```yaml
build:
  context: .
  cache_from:
    - alpine:latest
    - corp/web_app:3.14
```

### depends_on

解决容器的依赖、启动先后的问题。以下例子中会先启动 `redis` `db` 再启动 `web`

```yaml
version: '3'

services:
  web:
    build: .
    depends_on:
      - db
      - redis

  redis:
    image: redis

  db:
    image: postgres
```

> 注意：`web` 服务不会等待 `redis` `db` 「完全启动」之后才启动。这里需要注意，如果redis启动失败，那么web依然会正常启动。

### environment

设置环境变量。你可以使用数组或字典两种格式。只给定名称的变量会自动获取运行 Compose 主机上对应变量的值，可以用来防止泄露不必要的数据。

```yaml
environment:
  RACK_ENV: development
  SESSION_SECRET:

environment:
  - RACK_ENV=development
  - SESSION_SECRET
```

如果变量名称或者值中用到 `true|false，yes|no` 等表达 [布尔 (opens new window)](https://yaml.org/type/bool.html)含义的词汇，最好放到引号里，避免 YAML 自动解析某些内容为对应的布尔语义。这些特定词汇，包括

```bash
y|Y|yes|Yes|YES|n|N|no|No|NO|true|True|TRUE|false|False|FALSE|on|On|ON|off|Off|OFF
```

### expose

暴露端口，但不映射到宿主机，只被连接的服务访问。仅可以指定内部端口为参数

```yaml
expose:
 - "3000"
 - "8000"
```

### ports

暴露端口信息。使用宿主端口：容器端口 `(HOST:CONTAINER)` 格式，或者仅仅指定容器的端口（宿主将会随机选择端口）都可以。

```yaml
ports:
 - "3000"
 - "8000:8000"
 - "49100:22"
 - "127.0.0.1:8001:8001"
```

*注意：当使用 `HOST:CONTAINER` 格式来映射端口时，如果你使用的容器端口小于 60 并且没放到引号里，可能会得到错误结果，因为 `YAML` 会自动解析 `xx:yy` 这种数字格式为 60 进制。为避免出现这种问题，建议数字串都采用引号包括起来的字符串格式。*

### secrets

存储敏感数据，例如 `mysql` 服务密码。

```yaml
version: "3.1"
services:

mysql:
  image: mysql
  environment:
    MYSQL_ROOT_PASSWORD_FILE: /run/secrets/db_root_password
  secrets:
    - db_root_password
    - my_other_secret

secrets:
  my_secret:
    file: ./my_secret.txt
  my_other_secret:
    external: true
```

### image

指定为镜像名称或镜像 ID。如果镜像在本地不存在，`Compose` 将会尝试拉取这个镜像。

```yaml
image: ubuntu
image: orchardup/postgresql
image: a4bc65fd
```

### labels

为容器添加 Docker 元数据（metadata）信息。例如可以为容器添加辅助说明信息。

```yaml
labels:
  com.startupteam.description: "webapp for a startup team"
  com.startupteam.department: "devops department"
  com.startupteam.release: "rc3 for v1.0"
```

### network_mode

设置网络模式。使用和 `docker run` 的 `--network` 参数一样的值。

```yaml
network_mode: "bridge"
network_mode: "host"
network_mode: "none"
network_mode: "service:[service name]"
network_mode: "container:[container name/id]"
```

### networks

配置容器连接的网络。

```yaml
version: "3"
services:

  some-service:
    networks:
     - some-network
     - other-network

networks:
  some-network:
  other-network:
```

### volumes

数据卷所挂载路径设置。可以设置为宿主机路径(`HOST:CONTAINER`)或者数据卷名称(`VOLUME:CONTAINER`)，并且可以设置访问模式 （`HOST:CONTAINER:ro`）。该指令中路径支持相对路径。

```yaml
volumes:
 - /var/lib/mysql
 - cache/:/tmp/cache
 - ~/configs:/etc/configs/:ro
```

如果路径为数据卷名称，必须在文件中配置数据卷。

```yaml
version: "3"

services:
  my_src:
    image: mysql:8.0
    volumes:
      - mysql_data:/var/lib/mysql

volumes:
  mysql_data:  
```

## Compose命令

之前我们已经介绍了一些命令，已经能够满足一些基本的日常使用了，下面我们再了解一些其他命令。

### 命令对象与格式

对于 Compose 来说，大部分命令的对象既可以是项目本身，也可以指定为项目中的服务或者容器。如果没有特别的说明，命令对象将是项目，这意味着项目中所有的服务都会受到命令影响。

执行 `docker-compose [COMMAND] --help` 或者 `docker-compose help [COMMAND]` 可以查看具体某个命令的使用格式。

`docker-compose` 命令的基本的使用格式是

```bash
docker-compose [-f=<arg>...] [options] [COMMAND] [ARGS...]
```

### 命令选项

- `-f, --file FILE` 指定使用的 Compose 模板文件，默认为 `docker-compose.yml`，可以多次指定。
- `-p, --project-name NAME` 指定项目名称，默认将使用所在目录名称作为项目名。
- `--verbose` 输出更多调试信息。
- `-v, --version` 打印版本并退出。

### build

格式为 `docker-compose build [options] [SERVICE...]`。

构建（重新构建）项目中的服务容器。

服务容器一旦构建后，将会带上一个标记名，例如对于 web 项目中的一个 db 容器，可能是 web_db。

可以随时在项目目录下运行 `docker-compose build` 来重新构建服务。

选项包括：

- `--force-rm` 删除构建过程中的临时容器。
- `--no-cache` 构建镜像过程中不使用 cache（这将加长构建过程）。
- `--pull` 始终尝试通过 pull 来获取更新版本的镜像。

### config

验证 Compose 文件格式是否正确，若正确则显示配置，若格式错误显示错误原因。

### down

此命令将会停止 `up` 命令所启动的容器，并移除网络

### exec

进入指定的容器。

### help

获得一个命令的帮助。

### images

列出 Compose 文件中包含的镜像。

### kill

格式为 `docker-compose kill [options] [SERVICE...]`。

通过发送 `SIGKILL` 信号来强制停止服务容器。

支持通过 `-s` 参数来指定发送的信号，例如通过如下指令发送 `SIGINT` 信号。

```bash
$ docker-compose kill -s SIGINT
```

### logs

格式为 `docker-compose logs [options] [SERVICE...]`。

查看服务容器的输出。默认情况下，docker-compose 将对不同的服务输出使用不同的颜色来区分。可以通过 `--no-color` 来关闭颜色。

该命令在调试问题的时候十分有用。

### pause

格式为 `docker-compose pause [SERVICE...]`。

暂停一个服务容器。

### port

格式为 `docker-compose port [options] SERVICE PRIVATE_PORT`。

打印某个容器端口所映射的公共端口。

选项：

- `--protocol=proto` 指定端口协议，tcp（默认值）或者 udp。
- `--index=index` 如果同一服务存在多个容器，指定命令对象容器的序号（默认为 1）。

### ps

格式为 `docker-compose ps [options] [SERVICE...]`。

列出项目中目前的所有容器。

选项：

- `-q` 只打印容器的 ID 信息。

### pull

格式为 `docker-compose pull [options] [SERVICE...]`。

拉取服务依赖的镜像。

选项：

- `--ignore-pull-failures` 忽略拉取镜像过程中的错误。

### push

推送服务依赖的镜像到 Docker 镜像仓库。

### restart

格式为 `docker-compose restart [options] [SERVICE...]`。

重启项目中的服务。

选项：

- `-t, --timeout TIMEOUT` 指定重启前停止容器的超时（默认为 10 秒）。

### rm

格式为 `docker-compose rm [options] [SERVICE...]`。

删除所有（停止状态的）服务容器。推荐先执行 `docker-compose stop` 命令来停止容器。

选项：

- `-f, --force` 强制直接删除，包括非停止状态的容器。一般尽量不要使用该选项。
- `-v` 删除容器所挂载的数据卷。

### start

格式为 `docker-compose start [SERVICE...]`。

启动已经存在的服务容器。

### stop

格式为 `docker-compose stop [options] [SERVICE...]`。

停止已经处于运行状态的容器，但不删除它。通过 `docker-compose start` 可以再次启动这些容器。

选项：

- `-t, --timeout TIMEOUT` 停止容器时候的超时（默认为 10 秒）。

### top

查看各个服务容器内运行的进程。

### unpause

格式为 `docker-compose unpause [SERVICE...]`。

恢复处于暂停状态中的服务。

### up

格式为 `docker-compose up [options] [SERVICE...]`。

该命令十分强大，它将尝试自动完成包括构建镜像，（重新）创建服务，启动服务，并关联服务相关容器的一系列操作。

链接的服务都将会被自动启动，除非已经处于运行状态。

可以说，大部分时候都可以直接通过该命令来启动一个项目。

默认情况，`docker-compose up` 启动的容器都在前台，控制台将会同时打印所有容器的输出信息，可以很方便进行调试。

当通过 `Ctrl-C` 停止命令时，所有容器将会停止。

如果使用 `docker-compose up -d`，将会在后台启动并运行所有的容器。一般推荐生产环境下使用该选项。

默认情况，如果服务容器已经存在，`docker-compose up` 将会尝试停止容器，然后重新创建（保持使用 `volumes-from` 挂载的卷），以保证新启动的服务匹配 `docker-compose.yml` 文件的最新内容。如果用户不希望容器被停止并重新创建，可以使用 `docker-compose up --no-recreate`。这样将只会启动处于停止状态的容器，而忽略已经运行的服务。如果用户只想重新部署某个服务，可以使用 `docker-compose up --no-deps -d ` 来重新创建服务并后台停止旧服务，启动新服务，并不会影响到其所依赖的服务。

选项：

- `-d` 在后台运行服务容器。
- `--no-color` 不使用颜色来区分不同的服务的控制台输出。
- `--no-deps` 不启动服务所链接的容器。
- `--force-recreate` 强制重新创建容器，不能与 `--no-recreate` 同时使用。
- `--no-recreate` 如果容器已经存在了，则不重新创建，不能与 `--force-recreate` 同时使用。
- `--no-build` 不自动构建缺失的服务镜像。
- `-t, --timeout TIMEOUT` 停止容器时候的超时（默认为 10 秒）。

### version

格式为 `docker-compose version`。

打印版本信息。

## 参考文献

- [Docker Compose项目](https://vuepress.mirror.docker-practice.com/compose/)