# Chapter 5 Docker 网络

## 目录

- Docker 基础网络介绍
   - 外部访问容器
   - 容器互联
   - 配置DNS

- Docker的网络模式
   - Bridge 模式
   - Host 模式
   - None 模式
   - Container 模式

- Docker高级网络配置
   - 快速配置指南
   - 容器访问控制
   - 端口映射实现
   - 配置docker0网桥
   - 自定义网桥
   - 工具和示例
   - 编辑网络配置文件
  - 实例：创建一个点到点连接

#  Docker 基础网络介绍

## <span id="jump">外部访问容器</span>

容器中可以运行一些网络应用，要让外部也可以访问这些应用，可以通过`-P`或`-p`参数来指定端口映射。

当使用`-P`标记时，`Docker`会随机映射一个端口到内部容器开放的网络端口。
使用`docker container ls`可以看到，本地主机的 32768 被映射到了容器的 80 端口。此时访问本机的 32768 端口即可访问容器内 NGINX 默认页面。

```
$ docker run -d -P nginx:alpine

$ docker container ls -l
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                   NAMES
fae320d08268        nginx:alpine        "/docker-entrypoint.…"   24 seconds ago      Up 20 seconds       0.0.0.0:32768->80/tcp   bold_mcnulty
```

同样的，可以通过`docker logs`命令来查看访问记录。

```
$ docker logs fa
172.17.0.1 - - [25/Aug/2020:08:34:04 +0000] "GET / HTTP/1.1" 200 612 "-" "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:80.0) Gecko/20100101 Firefox/80.0" "-"
```

`-p`则可以指定要映射的端口，并且，在一个指定端口上只可以绑定一个容器。支持的格式有`ip:hostPort:containerPort | ip::containerPort | hostPort:containerPort`.

### 映射所有接口地址

使用`hostPort:containerPort`格式本地的 80 端口映射到容器的 80 端口，可以执行

```
$ docker run -d -p 80:80 nginx:alpine
```

此时默认会绑定本地所有接口上的所有地址。

### 映射到指定地址的指定端口

可以使用`ip:hostPort:containerPort`格式指定映射使用一个特定地址，比如`localhost`地址127.0.0.1

```
$ docker run -d -p 127.0.0.1:80:80 nginx:alpine
```

### 映射到指定地址的任意端口

使用`ip::containerPort`绑定`localhost`的任意端口到容器的80端口，本地主机会自动分配一个端口。

```
$ docker run -d -p 127.0.0.1::80 nginx:alpine
```

还可以使用`udp`标记来指定`udp`端口

```
$ docker run -d -p 127.0.0.1:80:80/udp nginx:alpine
```

### 查看映射端口配置

使用`docker port`来查看当前映射的端口配置，也可以查看到绑定的地址

```
$ docker port fa 80
0.0.0.0:32768
```

**注意：**
容器有自己的内部网络和 ip 地址（使用`docker inspect`查看，`Docker`还可以有一个可变的网络配置。）
`-p`标记可以多次使用来绑定多个端口

例如

```
$ docker run -d \
    -p 80:80 \
    -p 443:443 \
    nginx:alpine
```

## <span id="jump">容器互联</span>

如果之前有 `Docker`使用经验，可能已经习惯了使用`--link`参数来使容器互联。
随着 `Docker` 网络的完善，强烈建议大家将容器加入自定义的`Docker`网络来连接多个容器，而不是使用 `--link`参数。

### 新建网络

下面先创建一个新的 `Docker`网络。

```
$ docker network create -d bridge my-net
```

`-d`参数指定`Docker`网络类型，有`bridge overlay`,其中`overlay`网络类型用于`Swarm mode`，在本小节中你可以忽略它。

### 连接容器

运行一个容器并连接到新建的`my-net`网络

```
$ docker run -it --rm --name busybox1 --network my-net busybox sh
```

打开新的终端，再运行一个容器并加入到 `my-net`网络

```
$ docker run -it --rm --name busybox2 --network my-net busybox sh
```

再打开一个新的终端查看容器信息

```
$ docker container ls

CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
b47060aca56b        busybox             "sh"                11 minutes ago      Up 11 minutes                           busybox2
8720575823ec        busybox             "sh"                16 minutes ago      Up 16 minutes                           busybox1
```

下面通过 `ping`来证明`busybox1`容器和`busybox2`容器建立了互联关系。
在`busybox1`容器输入以下命令

```
/ # ping busybox2
PING busybox2 (172.19.0.3): 56 data bytes
64 bytes from 172.19.0.3: seq=0 ttl=64 time=0.072 ms
64 bytes from 172.19.0.3: seq=1 ttl=64 time=0.118 ms
```

用`ping`来测试连接`busybox2`容器，它会解析成 172.19.0.3。
同理在`busybox2`容器执行`ping busybox1`，也会成功连接到。

```
/ # ping busybox1
PING busybox1 (172.19.0.2): 56 data bytes
64 bytes from 172.19.0.2: seq=0 ttl=64 time=0.064 ms
64 bytes from 172.19.0.2: seq=1 ttl=64 time=0.143 ms
```

这样，`busybox1` 容器和 `busybox2` 容器建立了互联关系。

`Docker Compose`
如果你有多个容器之间需要互相连接，推荐使用`Docker`Compose。

## <span id="jump">配置DNS</span>

如何自定义配置容器的主机名和 DNS 呢？秘诀就是`Docker`利用虚拟文件来挂载容器的 3个相关配置文件。

在容器中使用 `mount`命令可以看到挂载信息：

```
$ mount
/dev/disk/by-uuid/1fec...ebdf on /etc/hostname type ext4 ...
/dev/disk/by-uuid/1fec...ebdf on /etc/hosts type ext4 ...
tmpfs on /etc/resolv.conf type tmpfs ...
```

这种机制可以让宿主主机 DNS 信息发生更新后，所有`Docker`容器的 DNS 配置通过 `/etc/resolv.conf`文件立刻得到更新。

配置全部容器的 DNS ，也可以在 `/etc/docker/daemon.json` 文件中增加以下内容来设置。

```
{
  "dns" : [
    "114.114.114.114",
    "8.8.8.8"
  ]
}
```

这样每次启动的容器 DNS 自动配置为 114.114.114.114 和8.8.8.8。使用以下命令来证明其已经生效。

```
$ docker run -it --rm ubuntu:18.04  cat etc/resolv.conf

nameserver 114.114.114.114
nameserver 8.8.8.8
```

如果用户想要手动指定容器的配置，可以在使用`docker run`命令启动容器时加入如下参数：
`-h HOSTNAME`或者`--hostname=HOSTNAME`设定容器的主机名，它会被写到容器内的`/etc/hostname 和 /etc/hosts`。但它在容器外部看不到，既不会在`docker container ls`中显示，也不会在其他的容器的`/etc/hosts`看到。

`--dns=IP_ADDRESS`添加 DNS 服务器到容器的`/etc/resolv.conf`中，让容器用这个服务器来解析所有不在 `/etc/hosts `中的主机名。

`--dns-search=DOMAIN`设定容器的搜索域，当设定搜索域为`.example.com`时，在搜索一个名为`host`的主机时，DNS 不仅搜索 `host`，还会搜索`host.example.com`。

**注意：**如果在容器启动时没有指定最后两个参数，`Docker`会默认用主机上的`/etc/resolv.conf`来配置容器。

# Docker的网络模式

可以通过`docker network ls`查看网络，默认创建三种网络。

```
[root@localhost ~]# docker network ls
NETWORK ID          NAME                DRIVER              SCOPE
688d1970f72e        bridge              bridge              local
885da101da7d        host                host                local
f4f1b3cf1b7f        none                null                local
```

常见网络的含义：

| 网络模式  |                             简介                             |
| :-------: | :----------------------------------------------------------: |
|  Bridge   | 为每一个容器分配、设置 IP 等，并将容器连接到一个 `docker0` 虚拟网桥，默认为该模式。 |
|   Host    | 容器将不会虚拟出自己的网卡，配置自己的 IP 等，而是使用宿主机的 IP 和端口。 |
|   None    | 容器有独立的 Network namespace，但并没有对其进行任何网络设置，如分配 veth pair 和网桥连接，IP 等。 |
| Container | 新创建的容器不会创建自己的网卡和配置自己的 IP，而是和一个指定的容器共享 IP、端口范围等。 |

## <span id="jump">Bridge模式</span>

当`Docker`进程启动时，会在主机上创建一个名为`docker0`的虚拟网桥，此主机上启动的`Docker`容器会连接到这个虚拟网桥上，附加在其上的任何网卡之间都能自动转发数据包。虚拟网桥的工作方式和物理交换机类似，这样主机上的所有容器就通过交换机连在了一个二层网络中。从`docker0`子网中分配一个 IP 给容器使用，并设置 `docker0 `的 IP 地址为容器的默认网关。在主机上创建一对虚拟网卡`veth pair`设备，`Docker `将 `veth pair` 设备的一端放在新创建的容器中，并命名为`eth0`（容器的网卡），另一端放在主机中，以`vethxxx`这样类似的名字命名，并将这个网络设备加入到 `docker0` 网桥中。可以通过`brctl show`命令查看。

比如运行一个基于 `busybox` 镜像构建的容器 `bbox01`，查看 `ip addr`：

> busybox 被称为嵌入式 Linux 的瑞士军刀，整合了很多小的 unix 下的通用功能到一个小的可执行文件中。

![](https://tva1.sinaimg.cn/large/008eGmZEly1gp9yisxf2hj30o8089go6.jpg)

然后宿主机通过 `ip addr` 查看信息如下：

![](https://tva1.sinaimg.cn/large/008eGmZEly1gp9yj3hqloj30zs0gbtek.jpg)

　　通过以上的比较可以发现，证实了之前所说的：守护进程会创建一对对等虚拟设备接口 `veth pair`，将其中一个接口设置为容器的 `eth0` 接口（容器的网卡），另一个接口放置在宿主机的命名空间中，以类似 `vethxxx` 这样的名字命名。

　　同时，守护进程还会从网桥 `docker0` 的私有地址空间中分配一个 IP 地址和子网给该容器，并设置 docker0 的 IP 地址为容器的默认网关。也可以安装 `yum install -y bridge-utils` 以后，通过 `brctl show` 命令查看网桥信息。

![](https://tva1.sinaimg.cn/large/008eGmZEly1gp9yjciu52j30zt0it45e.jpg)

　　对于每个容器的 IP 地址和 Gateway 信息，可以通过 `docker inspect 容器名称|ID` 进行查看，在 `NetworkSettings` 节点中可以看到详细信息。

![](https://tva1.sinaimg.cn/large/008eGmZEly1gp9yjjize3j30o20lsady.jpg)

　　可以通过 `docker network inspect bridge` 查看所有 `bridge` 网络模式下的容器，在 `Containers` 节点中可以看到容器名称。

![](https://tva1.sinaimg.cn/large/008eGmZEly1gp9yjquu1yj30mf0myq6g.jpg)

> 　　关于 `bridge` 网络模式的使用，只需要在创建容器时通过参数 `--net bridge` 或者 `--network bridge` 指定即可，当然这也是创建容器默认使用的网络模式，也就是说这个参数是可以省略的。　　

![](https://tva1.sinaimg.cn/large/008eGmZEly1gp9yjzonksj30s50e1myt.jpg)

bridge模式是 `docker `的默认网络模式，不写`–net`参数，就是bridge模式。使用`docker run -p`时，`docker `实际是在`iptables`做了`DNAT`规则，实现端口转发功能。可以使用`iptables -t nat -vnL`查看。演示：

 ```
$ docker run -tid --net=bridge --name docker_bri1 \
             ubuntu-base:v3
             docker run -tid --net=bridge --name docker_bri2 \
             ubuntu-base:v3 

$ brctl show
$ docker exec -ti docker_bri1 /bin/bash
$ ifconfig –a
$ route –n
 ```



## <span id="jump">Host 模式</span>

- host 网络模式需要在创建容器时通过参数 `--net host` 或者 `--network host` 指定；
- 采用 host 网络模式的 Docker Container，可以直接使用宿主机的 IP 地址与外界进行通信，若宿主机的 eth0 是一个公有 IP，那么容器也拥有这个公有 IP。同时容器内服务的端口也可以使用宿主机的端口，无需额外进行 NAT 转换；
- host 网络模式可以让容器共享宿主机网络栈，这样的好处是外部主机与容器直接通信，但是容器的网络缺少隔离性。

![img](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/32ab62e6be9d4b4dbe9280ca3b9206f9~tplv-k3u1fbpfcp-zoom-1.image)

　　

　　比如基于 `host` 网络模式创建了一个基于 `busybox` 镜像构建的容器 `bbox02`，查看 `ip addr`：

![](https://tva1.sinaimg.cn/large/008eGmZEly1gp9ykedxwpj30p40exgq7.jpg)

　　然后宿主机通过 `ip addr` 查看信息如下：

![](https://tva1.sinaimg.cn/large/008eGmZEly1gp9yklkpxyj30w30dktd8.jpg)

　　对，你没有看错，返回信息一模一样，也可以肯定没有截错图，不信接着往下看。可以通过 `docker network inspect host` 查看所有 `host` 网络模式下的容器，在 `Containers` 节点中可以看到容器名称。

![](https://tva1.sinaimg.cn/large/008eGmZEly1gp9yktmta0j30ru0mkjun.jpg)

如果启动容器的时候使用`host`模式，那么这个容器将不会获得一个独立的`Network Namespace`，而是和宿主机共用一个`Network Namespace`。容器将不会虚拟出自己的网卡，配置自己的`IP`等，而是使用宿主机的`IP`和端口。但是，容器的其他方面，如文件系统、进程列表等还是和宿主机隔离的。 演示：

```
$ docker run -tid --net=host --name docker_host1 ubuntu-base:v3
$ docker run -tid --net=host --name docker_host2 ubuntu-base:v3

$ docker exec -ti docker_host1 /bin/bash
$ docker exec -ti docker_host1 /bin/bash

$ ifconfig –a
$ route –n
```

## <span id="jump">None模式</span>

- none 网络模式是指禁用网络功能，只有 lo 接口 local 的简写，代表 127.0.0.1，即 localhost 本地环回接口。在创建容器时通过参数 `--net none` 或者 `--network none` 指定；
- none 网络模式即不为 Docker Container 创建任何的网络环境，容器内部就只能使用 loopback 网络设备，不会再有其他的网络资源。可以说 none 模式为 Docke Container 做了极少的网络设定，但是俗话说得好“少即是多”，在没有网络配置的情况下，作为 Docker 开发者，才能在这基础做其他无限多可能的网络定制开发。这也恰巧体现了 Docker 设计理念的开放。

　比如基于 `none` 网络模式创建了一个基于 `busybox` 镜像构建的容器 `bbox03`，查看 `ip addr`：

![](https://tva1.sinaimg.cn/large/008eGmZEly1gp9yl6dza2j30le05lta3.jpg)

　　可以通过 `docker network inspect none` 查看所有 `none` 网络模式下的容器，在 `Containers` 节点中可以看到容器名称。

![](https://tva1.sinaimg.cn/large/008eGmZEly1gp9ylcuw30j30rt0mjwhr.jpg)

使用`none`模式，`Docker` 容器拥有自己的 `Network Namespace`，但是，并不为`Docker` 容器进行任何网络配置。也就是说，这个 `Docker` 容器没有网卡、IP、路由等信息。需要自己为 `Docker` 容器添加网卡、配置 IP 等。演示：

```
$ docker run -tid --net=none --name \
                docker_non1 ubuntu-base:v3

$ docker exec -ti docker_non1 /bin/bash

$ ifconfig –a
$ route -n
```

## <span id="jump">Container 模式</span>

- Container 网络模式是 Docker 中一种较为特别的网络的模式。在创建容器时通过参数 `--net container:已运行的容器名称|ID` 或者 `--network container:已运行的容器名称|ID` 指定；
- 处于这个模式下的 Docker 容器会共享一个网络栈，这样两个容器之间可以使用 localhost 高效快速通信。

![](https://tva1.sinaimg.cn/large/008eGmZEly1gp9ylkvrb8j30or0d50u7.jpg)

　　**Container 网络模式即新创建的容器不会创建自己的网卡，配置自己的 IP，而是和一个指定的容器共享 IP、端口范围等**。同样两个容器除了网络方面相同之外，其他的如文件系统、进程列表等还是隔离的。

　　比如基于容器 `bbox01` 创建了 `container` 网络模式的容器 `bbox04`，查看 `ip addr`：

![](https://tva1.sinaimg.cn/large/008eGmZEly1gp9ylvw4l3j30p3086ju3.jpg)

　　容器 `bbox01` 的 `ip addr` 信息如下：

![](https://tva1.sinaimg.cn/large/008eGmZEly1gp9ym5pqzqj30o506vgns.jpg)

　　宿主机的 `ip addr` 信息如下：

![](https://tva1.sinaimg.cn/large/008eGmZEly1gp9ymfqy5cj30zt0g3tf6.jpg)

　　通过以上测试可以发现，Docker 守护进程只创建了一对对等虚拟设备接口用于连接 bbox01 容器和宿主机，而 bbox04 容器则直接使用了 bbox01 容器的网卡信息。

　　这个时候如果将 bbox01 容器停止，会发现 bbox04 容器就只剩下 lo 接口了。

![](https://tva1.sinaimg.cn/large/008eGmZEly1gp9ymnpkruj30k704t757.jpg)

　　然后 bbox01 容器重启以后，bbox04 容器也重启一下，就又可以获取到网卡信息了。

![](https://tva1.sinaimg.cn/large/008eGmZEly1gp9ymvszr7j30ot084wh1.jpg)

这个模式指定新创建的容器和已经存在的一个容器共享一个`Network Namespace`，而不是和宿主机共享。新创建的容器不会创建自己的网卡，配置自己的`IP`，而是和一个指定的容器共享`IP`、端口范围等。同样，两个容器除了网络方面，其他的如文件系统、进程列表等还是隔离的。两个容器的进程可以通过lo网卡设备通信。 演示：

```
$ docker run -tid --net=container:docker_bri1 \
              --name docker_con1 ubuntu-base:v3

$ docker exec -ti docker_con1 /bin/bash
$ docker exec -ti docker_bri1 /bin/bash

$ ifconfig –a
$ route -n
```



# 高级网络配置

## <span id="jump">快速配置指南</span>

下面是一个跟 Docker 网络相关的命令列表。

其中有些命令选项只有在 Docker 服务启动的时候才能配置，而且不能马上生效。

 - `-b BRIDGE` 或 `--bridge=BRIDGE` 指定容器挂载的网桥
 - `--bip=CIDR`定制 `docker0` 的掩码
 - `-H SOCKET...` 或 `--host=SOCKET... Docker` 服务端接收命令的通道
 - `--icc=true|false` 是否支持容器之间进行通信
 - `--ip-forward=true|false` 请看下文容器之间的通信
 - `--iptables=true|false` 是否允许 Docker 添加 `iptables` 规则
 - `--mtu=BYTES` 容器网络中的 `MTU`

下面2个命令选项既可以在启动服务时指定，也可以在启动容器时指定。在 Docker服务启动的时候指定则会成为默认值，后面执行 `docker run` 时可以覆盖设置的默认值。

 - `--dns=IP_ADDRESS...` 使用指定的DNS服务器
 - `--dns-search=DOMAIN...` 指定DNS搜索域

最后这些选项只有在 `docker run` 执行时使用，因为它是针对容器的特性内容。

 - `-h HOSTNAME` 或 `--hostname=HOSTNAME` 配置容器主机名
 - `--link=CONTAINER_NAME:ALIAS` 添加到另一个容器的连接
 - `--net=bridge|none|container:NAME_or_ID|host` 配置容器的桥接模式
 - `-p SPEC` 或 --publish=SPEC` 映射容器端口到宿主主机
 - `-P or --publish-all=true|false` 映射容器所有端口到宿主主机


## <span id="jump">容器访问控制</span>

容器的访问控制，主要通过 Linux 上的 `iptables` 防火墙来进行管理和实现。`iptables` 是 Linux 上默认的防火墙软件，在大部分发行版中都自带。

### 容器访问外部网络

容器要想访问外部网络，需要本地系统的转发支持。在Linux 系统中，检查转发是否打开。

```
$sysctl net.ipv4.ip_forward
net.ipv4.ip_forward = 1
```

如果为 0，说明没有开启转发，则需要手动打开。

```
$sysctl -w net.ipv4.ip_forward=1
```

如果在启动 Docker 服务的时候设定 `--ip-forward=true`, Docker 就会自动设定系统的 `ip_forward` 参数为 1。

### 容器之间访问

容器之间相互访问，需要两方面的支持。

- 容器的网络拓扑是否已经互联。默认情况下，所有容器都会被连接到 `docker0` 网桥上。
- 本地系统的防火墙软件 `-- iptables` 是否允许通过。

### 访问所有端口

当启动 Docker 服务（即 dockerd）的时候，默认会添加一条转发策略到本地主机 iptables 的 FORWARD 链上。策略为通过（`ACCEPT`）还是禁止（`DROP`）取决于配置`--icc=true`（缺省值）还是 `--icc=false`。当然，如果手动指定 `--iptables=false` 则不会添加 `iptables` 规则。

可见，默认情况下，不同容器之间是允许网络互通的。如果为了安全考虑，可以在 `/etc/docker/daemon.json` 文件中配置 `{"icc": false}` 来禁止它。

### 访问指定端口

在通过 `-icc=false` 关闭网络访问后，还可以通过 `--link=CONTAINER_NAME:ALIAS` 选项来访问容器的开放端口。

例如，在启动 Docker 服务时，可以同时使用 `icc=false --iptables=true` 参数来关闭允许相互的网络访问，并让 Docker 可以修改系统中的 `iptables` 规则。

此时，系统中的 `iptables` 规则可能是类似

```
$ sudo iptables -nL
...
Chain FORWARD (policy ACCEPT)
target     prot opt source               destination
DROP       all  --  0.0.0.0/0            0.0.0.0/0
...
```

之后，启动容器（`docker run`）时使用 `--link=CONTAINER_NAME:ALIAS` 选项。Docker 会在 `iptable` 中为 两个容器分别添加一条 `ACCEPT` 规则，允许相互访问开放的端口（取决于 `Dockerfile` 中的 `EXPOSE` 指令）。

当添加了 `--link=CONTAINER_NAME:ALIAS` 选项后，添加了 `iptables` 规则。

```
$ sudo iptables -nL
...
Chain FORWARD (policy ACCEPT)
target     prot opt source               destination
ACCEPT     tcp  --  172.17.0.2           172.17.0.3           tcp spt:80
ACCEPT     tcp  --  172.17.0.3           172.17.0.2           tcp dpt:80
DROP       all  --  0.0.0.0/0            0.0.0.0/0
```

**注意**：`--link=CONTAINER_NAME:ALIAS` 中的 `CONTAINER_NAME` 目前必须是 Docker 分配的名字，或使用 `--name` 参数指定的名字。主机名则不会被识别。

## <span id="jump">端口映射实现</span>

默认情况下，容器可以主动访问到外部网络的连接，但是外部网络无法访问到容器。

### 容器访问外部实现

容器所有到外部网络的连接，源地址都会被 NAT 成本地系统的 IP 地址。这是使用 `iptables` 的源地址伪装操作实现的。

查看主机的 NAT 规则。



```
$ sudo iptables -t nat -nL
...
Chain POSTROUTING (policy ACCEPT)
target     prot opt source               destination
MASQUERADE  all  --  172.17.0.0/16       !172.17.0.0/16
...
```

其中，上述规则将所有源地址在 `172.17.0.0/16` 网段，目标地址为其他网段（外部网络）的流量动态伪装为从系统网卡发出。MASQUERADE 跟传统 SNAT 的好处是它能动态从网卡获取地址。

### 外部访问容器实现

容器允许外部访问，可以在 `docker run` 时候通过 `-p` 或 `-P` 参数来启用。

不管用那种办法，其实也是在本地的 `iptable` 的 nat 表中添加相应的规则。

使用 `-P` 时：

```
$ iptables -t nat -nL
...
Chain DOCKER (2 references)
target     prot opt source               destination
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            tcp dpt:49153 to:172.17.0.2:80
```

使用 `-p 80:80` 时：

```
$ iptables -t nat -nL
Chain DOCKER (2 references)
target     prot opt source               destination
DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            tcp dpt:80 to:172.17.0.2:80
```

**注意**：

- 这里的规则映射了 `0.0.0.0`，意味着将接受主机来自所有接口的流量。用户可以通过 `-p IP:host_port:container_port` 或 `-p IP::port` 来指定允许访问容器的主机上的 IP、接口等，以制定更严格的规则。
- 如果希望永久绑定到某个固定的 IP 地址，可以在 Docker 配置文件 `/etc/docker/daemon.json` 中添加如下内容。

```
{
  "ip": "0.0.0.0"
}
```

## <span id="jump">配置 docker0 网桥</span>

Docker 服务默认会创建一个 `docker0` 网桥（其上有一个 `docker0` 内部接口），它在内核层连通了其他的物理或虚拟网卡，这就将所有容器和本地主机都放到同一个物理网络。

Docker 默认指定了 `docker0` 接口 的 IP 地址和子网掩码，让主机和容器之间可以通过网桥相互通信，它还给出了 MTU（接口允许接收的最大传输单元），通常是 1500 Bytes，或宿主主机网络路由上支持的默认值。这些值都可以在服务启动的时候进行配置。

- `--bip=CIDR` IP 地址加掩码格式，例如 192.168.1.5/24
- `--mtu=BYTES` 覆盖默认的 Docker mtu 配置

也可以在配置文件中配置 DOCKER_OPTS，然后重启服务。

由于目前 Docker 网桥是 Linux 网桥，用户可以使用 `brctl show` 来查看网桥和端口连接信息。

```
$ sudo brctl show
bridge name     bridge id               STP enabled     interfaces
docker0         8000.3a1d7362b4ee       no              veth65f9
                                             vethdda6
```

**注**：`brctl` 命令在 Debian、Ubuntu 中可以使用 `sudo apt-get install bridge-utils` 来安装。

每次创建一个新容器的时候，Docker 从可用的地址段中选择一个空闲的 IP 地址分配给容器的 eth0 端口。使用本地主机上 `docker0` 接口的 IP 作为所有容器的默认网关。

```
$ sudo docker run -i -t --rm base /bin/bash
$ ip addr show eth0
24: eth0: <BROADCAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP group default qlen 1000
    link/ether 32:6f:e0:35:57:91 brd ff:ff:ff:ff:ff:ff
    inet 172.17.0.3/16 scope global eth0
       valid_lft forever preferred_lft forever
    inet6 fe80::306f:e0ff:fe35:5791/64 scope link
       valid_lft forever preferred_lft forever
$ ip route
default via 172.17.42.1 dev eth0
172.17.0.0/16 dev eth0  proto kernel  scope link  src 172.17.0.3
```

## <span id="jump">自定义网桥</span>

除了默认的 `docker0` 网桥，用户也可以指定网桥来连接各个容器。

在启动 Docker 服务的时候，使用 `-b BRIDGE`或`--bridge=BRIDGE` 来指定使用的网桥。

如果服务已经运行，那需要先停止服务，并删除旧的网桥。

```
$ sudo systemctl stop docker
$ sudo ip link set dev docker0 down
$ sudo brctl delbr docker0
```

然后创建一个网桥 `bridge0`。

```
$ sudo brctl addbr bridge0
$ sudo ip addr add 192.168.5.1/24 dev bridge0
$ sudo ip link set dev bridge0 up
```

查看确认网桥创建并启动。

```
$ ip addr show bridge0
4: bridge0: <BROADCAST,MULTICAST> mtu 1500 qdisc noop state UP group default
    link/ether 66:38:d0:0d:76:18 brd ff:ff:ff:ff:ff:ff
    inet 192.168.5.1/24 scope global bridge0
       valid_lft forever preferred_lft forever
```

在 Docker 配置文件 `/etc/docker/daemon.json` 中添加如下内容，即可将 Docker 默认桥接到创建的网桥上。

```
{
  "bridge": "bridge0",
}
```

启动 Docker 服务。

新建一个容器，可以看到它已经桥接到了 `bridge0` 上。

可以继续用 `brctl show` 命令查看桥接的信息。另外，在容器中可以使用 `ip addr` 和 `ip route` 命令来查看 IP 地址配置和路由信息。

## <span id="jump">工具和示例</span>

在介绍自定义网络拓扑之前，你可能会对一些外部工具和例子感兴趣：

### **pipework**

Jérôme Petazzoni 编写了一个叫 [pipework](https://github.com/jpetazzo/pipework) 的 shell 脚本，可以帮助用户在比较复杂的场景中完成容器的连接。

### **playground**

Brandon Rhodes 创建了一个提供完整的 Docker 容器网络拓扑管理的 [Python库](https://github.com/brandon-rhodes/fopnp/tree/m/playground)，包括路由、NAT 防火墙；以及一些提供 `HTTP` `SMTP` `POP` `IMAP` `Telnet` `SSH` `FTP` 的服务器。



## <span id="jump">编辑网络配置文件</span>

Docker 1.2.0 开始支持在运行中的容器里编辑 `/etc/hosts`, `/etc/hostname` 和 `/etc/resolv.conf` 文件。

但是这些修改是临时的，只在运行的容器中保留，容器终止或重启后并不会被保存下来，也不会被 `docker commit` 提交。



## <span id="jump">实例：创建一个点到点连接</span>

默认情况下，Docker 会将所有容器连接到由 `docker0` 提供的虚拟子网中。

用户有时候需要两个容器之间可以直连通信，而不用通过主机网桥进行桥接。

解决办法很简单：创建一对 `peer` 接口，分别放到两个容器中，配置成点到点链路类型即可。

首先启动 2 个容器：



```
$ docker run -i -t --rm --net=none base /bin/bash
root@1f1f4c1f931a:/#
$ docker run -i -t --rm --net=none base /bin/bash
root@12e343489d2f:/#
```

找到进程号，然后创建网络命名空间的跟踪文件。



```
$ docker inspect -f '{{.State.Pid}}' 1f1f4c1f931a
2989
$ docker inspect -f '{{.State.Pid}}' 12e343489d2f
3004
$ sudo mkdir -p /var/run/netns
$ sudo ln -s /proc/2989/ns/net /var/run/netns/2989
$ sudo ln -s /proc/3004/ns/net /var/run/netns/3004
```

创建一对 `peer` 接口，然后配置路由



```
$ sudo ip link add A type veth peer name B

$ sudo ip link set A netns 2989
$ sudo ip netns exec 2989 ip addr add 10.1.1.1/32 dev A
$ sudo ip netns exec 2989 ip link set A up
$ sudo ip netns exec 2989 ip route add 10.1.1.2/32 dev A

$ sudo ip link set B netns 3004
$ sudo ip netns exec 3004 ip addr add 10.1.1.2/32 dev B
$ sudo ip netns exec 3004 ip link set B up
$ sudo ip netns exec 3004 ip route add 10.1.1.1/32 dev B
```

现在这 2 个容器就可以相互 ping 通，并成功建立连接。点到点链路不需要子网和子网掩码。

此外，也可以不指定 `--net=none` 来创建点到点链路。这样容器还可以通过原先的网络来通信。

利用类似的办法，可以创建一个只跟主机通信的容器。但是一般情况下，更推荐使用 `--icc=false` 来关闭容器之间的通信。

### 参考文献

- [Docker 网络模式详解及容器间网络通信](https://juejin.cn/post/6868086876751085581)
- [Docker从基础到入门](https://yeasy.gitbook.io/docker_practice/advanced_network)
- [Docker网络模式](https://www.qikqiak.com/k8s-book/docs/7.Docker%E7%9A%84%E7%BD%91%E7%BB%9C%E6%A8%A1%E5%BC%8F.html)