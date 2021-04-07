# Chapter 2 Docker镜像与容器

## 目录

- Docker镜像
  - 获取镜像
  - 列出镜像
  - 删除本地镜像
  - Dockerfile构建镜像
  - 跨平台构建镜像
  - 镜像存储位置
- Docker容器
  - 新建并启动容器
  - 启动已终止的容器
  - 停止容器
  - 重启容器
  - 后台运行容器
  - 进入容器
  - 暂停容器
  - 删除容器
  - 导出容器
  - 导入容器

## Docker镜像

### 获取镜像

之前提到过，[Docker Hub](https://hub.docker.com/search?q=&type=image) 上有大量的高质量的镜像可以用，这里我们就说一下怎么获取这些镜像。

从 Docker 镜像仓库获取镜像的命令是 `docker pull`。其命令格式为：

```
$ docker pull [选项] [Docker Registry 地址[:端口号]/]仓库名[:标签]
```

具体的选项可以通过 `docker pull --help` 命令看到，这里我们说一下镜像名称的格式。

- Docker 镜像仓库地址：地址的格式一般是 `<域名/IP>[:端口号]`。默认地址是 Docker Hub(`docker.io`)。
- 仓库名：如之前所说，这里的仓库名是两段式名称，即 `<用户名>/<软件名>`。对于 Docker Hub，如果不给出用户名，则默认为 `library`，也就是官方镜像。

比如：

```bash
$ docker pull ubuntu:18.04
18.04: Pulling from library/ubuntu
92dc2a97ff99: Pull complete
be13a9d27eb8: Pull complete
c8299583700a: Pull complete
Digest: sha256:4bc3ae6596938cb0d9e5ac51a1152ec9dcac2a1c50829c74abd9c4361e321b26
Status: Downloaded newer image for ubuntu:18.04
docker.io/library/ubuntu:18.04
```

上面的命令中没有给出 Docker 镜像仓库地址，因此将会从 Docker Hub （`docker.io`）获取镜像。而镜像名称是 `ubuntu:18.04`，因此将会获取官方镜像 `library/ubuntu` 仓库中标签为 `18.04` 的镜像。`docker pull` 命令的输出结果最后一行给出了镜像的完整名称，即： `docker.io/library/ubuntu:18.04`。

从下载过程中可以看到我们之前提及的分层存储的概念，镜像是由多层存储所构成。下载也是一层层的去下载，并非单一文件。下载过程中给出了每一层的 ID 的前 12 位。并且下载结束后，给出该镜像完整的 `sha256` 的摘要，以确保下载一致性。

在使用上面命令的时候，你可能会发现，你所看到的层 ID 以及 `sha256` 的摘要和这里的不一样。这是因为官方镜像是一直在维护的，有任何新的 bug，或者版本更新，都会进行修复再以原来的标签发布，这样可以确保任何使用这个标签的用户可以获得更安全、更稳定的镜像。

*如果从 Docker Hub 下载镜像非常缓慢，可以参照 [镜像加速器](https://github.com/yeasy/docker_practice/blob/master/install/mirror.md) 一节配置加速器。*

### 列出镜像

要想列出已经下载下来的镜像，可以使用 `docker image ls` 命令。

```
$ docker image ls
REPOSITORY           TAG                 IMAGE ID            CREATED             SIZE
redis                latest              5f515359c7f8        5 days ago          183 MB
nginx                latest              05a60462f8ba        5 days ago          181 MB
mongo                3.2                 fe9198c04d62        5 days ago          342 MB
<none>               <none>              00285df0df87        5 days ago          342 MB
ubuntu               18.04               329ed837d508        3 days ago          63.3MB
ubuntu               bionic              329ed837d508        3 days ago          63.3MB
```

列表包含了 `仓库名`、`标签`、`镜像 ID`、`创建时间` 以及 `所占用的空间`。

其中仓库名、标签在之前的基础概念章节已经介绍过了。**镜像 ID** 则是镜像的唯一标识，一个镜像可以对应多个 **标签**。因此，在上面的例子中，我们可以看到 `ubuntu:18.04` 和 `ubuntu:bionic` 拥有相同的 ID，因为它们对应的是同一个镜像。

### 删除本地镜像

如果要删除本地的镜像，可以使用 `docker image rm` 命令，其格式为：

```
$ docker image rm [选项] <镜像1> [<镜像2> ...]
```

#### 用 ID、镜像名、摘要删除镜像

其中，`<镜像>` 可以是 `镜像短 ID`、`镜像长 ID`、`镜像名` 或者 `镜像摘要`。

比如我们有这么一些镜像：

```
$ docker image ls
REPOSITORY                  TAG                 IMAGE ID            CREATED             SIZE
centos                      latest              0584b3d2cf6d        3 weeks ago         196.5 MB
redis                       alpine              501ad78535f0        3 weeks ago         21.03 MB
docker                      latest              cf693ec9b5c7        3 weeks ago         105.1 MB
nginx                       latest              e43d811ce2f4        5 weeks ago         181.5 MB
```

我们可以用镜像的完整 ID，也称为 `长ID`，来删除镜像。使用脚本的时候可能会用长 ID，但是人工输入就太累了，所以更多的时候是用 `短ID` 来删除镜像。`docker image ls` 默认列出的就已经是短 ID 了，一般取前3个字符以上，只要足够区分于别的镜像就可以了。

比如这里，如果我们要删除 `redis:alpine` 镜像，可以执行：

```
$ docker image rm 501
Untagged: redis:alpine
Untagged: redis@sha256:f1ed3708f538b537eb9c2a7dd50dc90a706f7debd7e1196c9264edeea521a86d
Deleted: sha256:501ad78535f015d88872e13fa87a828425117e3d28075d0c117932b05bf189b7
Deleted: sha256:96167737e29ca8e9d74982ef2a0dda76ed7b430da55e321c071f0dbff8c2899b
Deleted: sha256:32770d1dcf835f192cafd6b9263b7b597a1778a403a109e2cc2ee866f74adf23
Deleted: sha256:127227698ad74a5846ff5153475e03439d96d4b1c7f2a449c7a826ef74a2d2fa
Deleted: sha256:1333ecc582459bac54e1437335c0816bc17634e131ea0cc48daa27d32c75eab3
Deleted: sha256:4fc455b921edf9c4aea207c51ab39b10b06540c8b4825ba57b3feed1668fa7c7
```

我们也可以用`镜像名`，也就是 `<仓库名>:<标签>`，来删除镜像。

```
$ docker image rm centos
Untagged: centos:latest
Untagged: centos@sha256:b2f9d1c0ff5f87a4743104d099a3d561002ac500db1b9bfa02a783a46e0d366c
Deleted: sha256:0584b3d2cf6d235ee310cf14b54667d889887b838d3f3d3033acd70fc3c48b8a
Deleted: sha256:97ca462ad9eeae25941546209454496e1d66749d53dfa2ee32bf1faabd239d38
```

当然，更精确的是使用 `镜像摘要` 删除镜像。

```
$ docker image ls --digests
REPOSITORY                  TAG                 DIGEST                                                                    IMAGE ID            CREATED             SIZE
node                        slim                sha256:b4f0e0bdeb578043c1ea6862f0d40cc4afe32a4a582f3be235a3b164422be228   6e0c4c8e3913        3 weeks ago         214 MB

$ docker image rm node@sha256:b4f0e0bdeb578043c1ea6862f0d40cc4afe32a4a582f3be235a3b164422be228
Untagged: node@sha256:b4f0e0bdeb578043c1ea6862f0d40cc4afe32a4a582f3be235a3b164422be228
```

#### Untagged 和 Deleted

如果观察上面这几个命令的运行输出信息的话，你会注意到删除行为分为两类，一类是 `Untagged`，另一类是 `Deleted`。我们之前介绍过，镜像的唯一标识是其 ID 和摘要，而一个镜像可以有多个标签。

因此当我们使用上面命令删除镜像的时候，实际上是在要求删除某个标签的镜像。所以首先需要做的是将满足我们要求的所有镜像标签都取消，这就是我们看到的 `Untagged` 的信息。因为一个镜像可以对应多个标签，因此当我们删除了所指定的标签后，可能还有别的标签指向了这个镜像，如果是这种情况，那么 `Delete` 行为就不会发生。所以并非所有的 `docker image rm` 都会产生删除镜像的行为，有可能仅仅是取消了某个标签而已。

当该镜像所有的标签都被取消了，该镜像很可能会失去了存在的意义，因此会触发删除行为。镜像是多层存储结构，因此在删除的时候也是从上层向基础层方向依次进行判断删除。镜像的多层结构让镜像复用变得非常容易，因此很有可能某个其它镜像正依赖于当前镜像的某一层。这种情况，依旧不会触发删除该层的行为。直到没有任何层依赖当前层时，才会真实的删除当前层。这就是为什么，有时候会奇怪，为什么明明没有别的标签指向这个镜像，但是它还是存在的原因，也是为什么有时候会发现所删除的层数和自己 `docker pull` 看到的层数不一样的原因。

除了镜像依赖以外，还需要注意的是容器对镜像的依赖。如果有用这个镜像启动的容器存在（即使容器没有运行），那么同样不可以删除这个镜像。之前讲过，容器是以镜像为基础，再加一层容器存储层，组成这样的多层存储结构去运行的。因此该镜像如果被这个容器所依赖的，那么删除必然会导致故障。如果这些容器是不需要的，应该先将它们删除，然后再来删除镜像。

#### 用 docker image ls 命令来配合

像其它可以承接多个实体的命令一样，可以使用 `docker image ls -q` 来配合使用 `docker image rm`，这样可以成批的删除希望删除的镜像。我们在“镜像列表”章节介绍过很多过滤镜像列表的方式都可以拿过来使用。

比如，我们需要删除所有仓库名为 `redis` 的镜像：

```
$ docker image rm $(docker image ls -q redis)
```

或者删除所有在 `mongo:3.2` 之前的镜像：

```
$ docker image rm $(docker image ls -q -f before=mongo:3.2)
```

充分利用你的想象力和 Linux 命令行的强大，你可以完成很多非常赞的功能。

### Dockerfile制作镜像

Dockerfile 是一个文本文件，其内包含了一条条的 **指令(Instruction)**，每一条指令构建一层，因此每一条指令的内容，就是描述该层应当如何构建。

还以之前定制 `nginx` 镜像为例，这次我们使用 Dockerfile 来定制。

在一个空白目录中，建立一个文本文件，并命名为 `Dockerfile`：

```
$ mkdir mynginx
$ cd mynginx
$ touch Dockerfile
```

其内容为：

```
FROM nginx
RUN echo '<h1>Hello, Docker!</h1>' > /usr/share/nginx/html/index.html
```

这个 Dockerfile 很简单，一共就两行。涉及到了两条指令，`FROM` 和 `RUN`。

#### FROM 指定基础镜像

所谓定制镜像，那一定是以一个镜像为基础，在其上进行定制。就像我们之前运行了一个 `nginx` 镜像的容器，再进行修改一样，基础镜像是必须指定的。而 `FROM` 就是指定 **基础镜像**，因此一个 `Dockerfile` 中 `FROM` 是必备的指令，并且必须是第一条指令。

在 [Docker Hub](https://hub.docker.com/search?q=&type=image&image_filter=official) 上有非常多的高质量的官方镜像，有可以直接拿来使用的服务类的镜像，如 [`nginx`](https://hub.docker.com/_/nginx/)、[`redis`](https://hub.docker.com/_/redis/)、[`mongo`](https://hub.docker.com/_/mongo/)、[`mysql`](https://hub.docker.com/_/mysql/)、[`httpd`](https://hub.docker.com/_/httpd/)、[`php`](https://hub.docker.com/_/php/)、[`tomcat`](https://hub.docker.com/_/tomcat/) 等；也有一些方便开发、构建、运行各种语言应用的镜像，如 [`node`](https://hub.docker.com/_/node)、[`openjdk`](https://hub.docker.com/_/openjdk/)、[`python`](https://hub.docker.com/_/python/)、[`ruby`](https://hub.docker.com/_/ruby/)、[`golang`](https://hub.docker.com/_/golang/) 等。可以在其中寻找一个最符合我们最终目标的镜像为基础镜像进行定制。

如果没有找到对应服务的镜像，官方镜像中还提供了一些更为基础的操作系统镜像，如 [`ubuntu`](https://hub.docker.com/_/ubuntu/)、[`debian`](https://hub.docker.com/_/debian/)、[`centos`](https://hub.docker.com/_/centos/)、[`fedora`](https://hub.docker.com/_/fedora/)、[`alpine`](https://hub.docker.com/_/alpine/) 等，这些操作系统的软件库为我们提供了更广阔的扩展空间。

除了选择现有镜像为基础镜像外，Docker 还存在一个特殊的镜像，名为 `scratch`。这个镜像是虚拟的概念，并不实际存在，它表示一个空白的镜像。

```
FROM scratch
...
```

如果你以 `scratch` 为基础镜像的话，意味着你不以任何镜像为基础，接下来所写的指令将作为镜像第一层开始存在。

不以任何系统为基础，直接将可执行文件复制进镜像的做法并不罕见，对于 Linux 下静态编译的程序来说，并不需要有操作系统提供运行时支持，所需的一切库都已经在可执行文件里了，因此直接 `FROM scratch` 会让镜像体积更加小巧。使用 [Go 语言](https://golang.google.cn/) 开发的应用很多会使用这种方式来制作镜像，这也是为什么有人认为 Go 是特别适合容器微服务架构的语言的原因之一。

#### RUN 执行命令

`RUN` 指令是用来执行命令行命令的。由于命令行的强大能力，`RUN` 指令在定制镜像时是最常用的指令之一。其格式有两种：

- *shell* 格式：`RUN <命令>`，就像直接在命令行中输入的命令一样。刚才写的 Dockerfile 中的 `RUN` 指令就是这种格式。

```
RUN echo '<h1>Hello, Docker!</h1>' > /usr/share/nginx/html/index.html
```

- *exec* 格式：`RUN ["可执行文件", "参数1", "参数2"]`，这更像是函数调用中的格式。

既然 `RUN` 就像 Shell 脚本一样可以执行命令，那么我们是否就可以像 Shell 脚本一样把每个命令对应一个 RUN 呢？比如这样：

```
FROM debian:stretch

RUN apt-get update
RUN apt-get install -y gcc libc6-dev make wget
RUN wget -O redis.tar.gz "http://download.redis.io/releases/redis-5.0.3.tar.gz"
RUN mkdir -p /usr/src/redis
RUN tar -xzf redis.tar.gz -C /usr/src/redis --strip-components=1
RUN make -C /usr/src/redis
RUN make -C /usr/src/redis install
```

之前说过，Dockerfile 中每一个指令都会建立一层，`RUN` 也不例外。每一个 `RUN` 的行为，就和刚才我们手工建立镜像的过程一样：新建立一层，在其上执行这些命令，执行结束后，`commit` 这一层的修改，构成新的镜像。

而上面的这种写法，创建了 7 层镜像。这是完全没有意义的，而且很多运行时不需要的东西，都被装进了镜像里，比如编译环境、更新的软件包等等。结果就是产生非常臃肿、非常多层的镜像，不仅仅增加了构建部署的时间，也很容易出错。 这是很多初学 Docker 的人常犯的一个错误。

*Union FS 是有最大层数限制的，比如 AUFS，曾经是最大不得超过 42 层，现在是不得超过 127 层。*

上面的 `Dockerfile` 正确的写法应该是这样：

```
FROM debian:stretch

RUN set -x; buildDeps='gcc libc6-dev make wget' \
    && apt-get update \
    && apt-get install -y $buildDeps \
    && wget -O redis.tar.gz "http://download.redis.io/releases/redis-5.0.3.tar.gz" \
    && mkdir -p /usr/src/redis \
    && tar -xzf redis.tar.gz -C /usr/src/redis --strip-components=1 \
    && make -C /usr/src/redis \
    && make -C /usr/src/redis install \
    && rm -rf /var/lib/apt/lists/* \
    && rm redis.tar.gz \
    && rm -r /usr/src/redis \
    && apt-get purge -y --auto-remove $buildDeps
```

首先，之前所有的命令只有一个目的，就是编译、安装 redis 可执行文件。因此没有必要建立很多层，这只是一层的事情。因此，这里没有使用很多个 `RUN` 一一对应不同的命令，而是仅仅使用一个 `RUN` 指令，并使用 `&&` 将各个所需命令串联起来。将之前的 7 层，简化为了 1 层。在撰写 Dockerfile 的时候，要经常提醒自己，这并不是在写 Shell 脚本，而是在定义每一层该如何构建。

并且，这里为了格式化还进行了换行。Dockerfile 支持 Shell 类的行尾添加 `\` 的命令换行方式，以及行首 `#` 进行注释的格式。良好的格式，比如换行、缩进、注释等，会让维护、排障更为容易，这是一个比较好的习惯。

此外，还可以看到这一组命令的最后添加了清理工作的命令，删除了为了编译构建所需要的软件，清理了所有下载、展开的文件，并且还清理了 `apt` 缓存文件。这是很重要的一步，我们之前说过，镜像是多层存储，每一层的东西并不会在下一层被删除，会一直跟随着镜像。因此镜像构建时，一定要确保每一层只添加真正需要添加的东西，任何无关的东西都应该清理掉。

很多人初学 Docker 制作出了很臃肿的镜像的原因之一，就是忘记了每一层构建的最后一定要清理掉无关文件。

#### 构建镜像

好了，让我们再回到之前定制的 nginx 镜像的 Dockerfile 来。现在我们明白了这个 Dockerfile 的内容，那么让我们来构建这个镜像吧。

在 `Dockerfile` 文件所在目录执行：

```
$ docker build -t nginx:v3 .
Sending build context to Docker daemon 2.048 kB
Step 1 : FROM nginx
 ---> e43d811ce2f4
Step 2 : RUN echo '<h1>Hello, Docker!</h1>' > /usr/share/nginx/html/index.html
 ---> Running in 9cdc27646c7b
 ---> 44aa4490ce2c
Removing intermediate container 9cdc27646c7b
Successfully built 44aa4490ce2c
```

从命令的输出结果中，我们可以清晰的看到镜像的构建过程。在 `Step 2` 中，如同我们之前所说的那样，`RUN` 指令启动了一个容器 `9cdc27646c7b`，执行了所要求的命令，并最后提交了这一层 `44aa4490ce2c`，随后删除了所用到的这个容器 `9cdc27646c7b`。

这里我们使用了 `docker build` 命令进行镜像构建。其格式为：

```
docker build [选项] <上下文路径/URL/->
```

在这里我们指定了最终镜像的名称 `-t nginx:v3`，构建成功后，我们可以像之前运行 `nginx:v2` 那样来运行这个镜像，其结果会和 `nginx:v2` 一样。

#### 镜像构建上下文（Context）

如果注意，会看到 `docker build` 命令最后有一个 `.`。`.` 表示当前目录，而 `Dockerfile` 就在当前目录，因此不少初学者以为这个路径是在指定 `Dockerfile` 所在路径，这么理解其实是不准确的。如果对应上面的命令格式，你可能会发现，这是在指定 **上下文路径**。那么什么是上下文呢？

首先我们要理解 `docker build` 的工作原理。Docker 在运行时分为 Docker 引擎（也就是服务端守护进程）和客户端工具。Docker 的引擎提供了一组 REST API，被称为 [Docker Remote API](https://docs.docker.com/develop/sdk/)，而如 `docker` 命令这样的客户端工具，则是通过这组 API 与 Docker 引擎交互，从而完成各种功能。因此，虽然表面上我们好像是在本机执行各种 `docker` 功能，但实际上，一切都是使用的远程调用形式在服务端（Docker 引擎）完成。也因为这种 C/S 设计，让我们操作远程服务器的 Docker 引擎变得轻而易举。

当我们进行镜像构建的时候，并非所有定制都会通过 `RUN` 指令完成，经常会需要将一些本地文件复制进镜像，比如通过 `COPY` 指令、`ADD` 指令等。而 `docker build` 命令构建镜像，其实并非在本地构建，而是在服务端，也就是 Docker 引擎中构建的。那么在这种客户端/服务端的架构中，如何才能让服务端获得本地文件呢？

这就引入了上下文的概念。当构建的时候，用户会指定构建镜像上下文的路径，`docker build` 命令得知这个路径后，会将路径下的所有内容打包，然后上传给 Docker 引擎。这样 Docker 引擎收到这个上下文包后，展开就会获得构建镜像所需的一切文件。

如果在 `Dockerfile` 中这么写：

```
COPY ./package.json /app/
```

这并不是要复制执行 `docker build` 命令所在的目录下的 `package.json`，也不是复制 `Dockerfile` 所在目录下的 `package.json`，而是复制 **上下文（context）** 目录下的 `package.json`。

因此，`COPY` 这类指令中的源文件的路径都是*相对路径*。这也是初学者经常会问的为什么 `COPY ../package.json /app` 或者 `COPY /opt/xxxx /app` 无法工作的原因，因为这些路径已经超出了上下文的范围，Docker 引擎无法获得这些位置的文件。如果真的需要那些文件，应该将它们复制到上下文目录中去。

现在就可以理解刚才的命令 `docker build -t nginx:v3 .` 中的这个 `.`，实际上是在指定上下文的目录，`docker build` 命令会将该目录下的内容打包交给 Docker 引擎以帮助构建镜像。

如果观察 `docker build` 输出，我们其实已经看到了这个发送上下文的过程：

```
$ docker build -t nginx:v3 .
Sending build context to Docker daemon 2.048 kB
...
```

理解构建上下文对于镜像构建是很重要的，避免犯一些不应该的错误。比如有些初学者在发现 `COPY /opt/xxxx /app` 不工作后，于是干脆将 `Dockerfile` 放到了硬盘根目录去构建，结果发现 `docker build` 执行后，在发送一个几十 GB 的东西，极为缓慢而且很容易构建失败。那是因为这种做法是在让 `docker build` 打包整个硬盘，这显然是使用错误。

一般来说，应该会将 `Dockerfile` 置于一个空目录下，或者项目根目录下。如果该目录下没有所需文件，那么应该把所需文件复制一份过来。如果目录下有些东西确实不希望构建时传给 Docker 引擎，那么可以用 `.gitignore` 一样的语法写一个 `.dockerignore`，该文件是用于剔除不需要作为上下文传递给 Docker 引擎的。

那么为什么会有人误以为 `.` 是指定 `Dockerfile` 所在目录呢？这是因为在默认情况下，如果不额外指定 `Dockerfile` 的话，会将上下文目录下的名为 `Dockerfile` 的文件作为 Dockerfile。

这只是默认行为，实际上 `Dockerfile` 的文件名并不要求必须为 `Dockerfile`，而且并不要求必须位于上下文目录中，比如可以用 `-f ../Dockerfile.php` 参数指定某个文件作为 `Dockerfile`。

当然，一般大家习惯性的会使用默认的文件名 `Dockerfile`，以及会将其置于镜像构建上下文目录中。

> 更多关于Dockerfile的内容可以移步：[Dockerfile详解](https://vuepress.mirror.docker-practice.com/image/dockerfile/)，不过更多的内容还是大家在实践中逐渐熟悉，这样才能更了解里面的含义。

### 跨平台构建镜像

在日常的工作中，我们常常有需求将一个程序运行在不同架构CPU的设备上，尤其在嵌入式领域，我们常常接触的各种开发板、路由器往往都是使用ARM架构的芯片，而我们日常开发的设备都是在x86平台。我们在x86平台写的程序需要运行在使用ARM芯片的开发板上，这时候就需要跨CPU构建程序。总的来说跨平台构建程序有以下几种方式。

+ 直接在目标硬件编译
  这是最直接的方法，如果我们有目标CPU架构的机器，且机器上装有我们构建和运行程序必备的各种工具，那我们可以直接把代码放到目标设备上构建、运行。这种方法虽然直接，但太过局限，如果我们没有有目标CPU架构的机器怎么办？或者即便有机器，但是构建像文件系统这样的项目，非常耗时且低效，而往往在工作中，大多都是这样庞大的项目，所以需要另一种方式。
+ 使用交叉编译器
  交叉编译器是专门为在给定的系统平台上运行而设计的编译器，作用是可以在一种CPU架构上编译出另一个CPU架构的可执行文件。最普遍的例子，开发人员开发安卓应用的时候几乎都在X86的平台上开发构建，但安卓应用很明显是ARM架构的，这其中就是交叉编译器在起作用，x86的机器上通过交叉编译器将程序编译成ARM或ARM64的程序。
  从性能角度来看，该方法与方法一没什么区别，因为不需要模拟器的参与，几乎没有性能损耗，与物理机的性能一样。我们在使用buildroot构建嵌入式的文件系统时，就是在x86机器上使用交叉编译工具链编译出指定CPU架构的文件系统。但交叉编译不具有通用性，它的复杂度取决于程序使用的语言，如果使用 Golang 的话，那就超级容易了。
+ 模拟目标硬件
  模拟目标硬件最常见的开源模拟器是QEMU，QEMU是纯软件实现的虚拟化模拟器，几乎可以模拟任何硬件设备，我们最熟悉的就是能够模拟一台能够独立运行操作系统的虚拟机，虚拟机认为自己和硬件打交道，但其实是和QEMU模拟出来的硬件打交道，QEMU将这些指令转译给真正的硬件。QEMU支持许多常见的CPU架构，包括ARM、Power-PC和RISC-V等，然而QEMU的性能非常低，因为QEMU完全使用CPU来模拟硬件，所以的指令都要经过QEMU。但其实我们执行应用程序并不需要QEMU模拟整个硬件，还可以再精简。
+ 通过binfmt_misc模拟目标硬件的用户空间
  QEMU 除了可以模拟完整的操作系统之外，还有另外一种模式叫用户态模式（User mod）。该模式下 QEMU 将通过 binfmt_misc 在 Linux 内核中注册一个二进制转换处理程序，并在程序运行时动态翻译二进制文件，根据需要将系统调用从目标 CPU 架构转换为当前系统的 CPU 架构。最终的效果看起来就像在本地运行目标 CPU 架构的二进制文件。
  通过 QEMU 的用户态模式，我们可以创建轻量级的虚拟机（chroot 或容器），然后在虚拟机系统中编译程序，和本地编译一样简单轻松。

## 构建跨架构的Docker镜像

我们可以直接使用QEMU的用户态模式（User mod）来构建跨平台镜像，在该模式下QEMU通过binfmt_misc在Linux内核中注册一个二进制转换处理程序，在程序运行的时候实时动态的翻译二进制文件，根据实际需要将系统调用从目标CPU架构转换为当前系统的CPU架构，这样就能实现在本地运行不同于本地机器架构的二进制程序。这对于容器技术来说是最合适的模式。
首先确认内核开启了binfmt_misc，手动添加

```bash
mount binfmt_misc -t binfmt_misc /proc/sys/fs/binfmt_misc 
echo'rm:M::\x7fELF\x01\x01\x01\x00\x00\x00\x00\x00\x00\x00\x00\x00\x02\x00\x28\x00:\xff\xff\xff\xff\xff\xff\xff\x00\xff\xff\xff\xff\xff\xff\xff\xff\xfe\xff\xff\xff:/usr/bin/qemu-arm-static:' > /proc/sys/fs/binfmt_misc/register
```

为了方便，可以使用qemu-user-static提供的基于docker的一键解决方案：

```
docker run –rm –privileged multiarch/qemu-user-static:register
```

之后就可以构建 aarch64的镜像了。
构建Docker镜像的方法是使用 Dockerfile，Dockerfile是一个文本文件，其中包含很多条指令，每一条指令构建一层，多层构成一个完整的Docker镜像。

```dockerfile
FROM arm64v8/ubuntu:16.04

MAINTAINER qiaoshi qiaoshi003@ke.com

COPY qemu-aarch64-static /usr/bin/qemu-aarch64-static
#执行命令
ADD riemann_cam_rom.tar.gz /usr/local2/ 
RUN cp -nr /usr/local2/usr /  ;\
    cp -nr /usr/local2/bin /bin ;\
    cp -nr /usr/local2/lib /lib ;\
    cp -nr /usr/local2/sbin /sbin ;\
    cp -nr /usr/local2/realsee / ;\
    rm -rf /usr/local2/ \
    && sed -i 's/ports.ubuntu.com/mirrors.ustc.edu.cn/g' /etc/apt/sources.list \
    && sed -i 's/ports.ubuntu.com/mirrors.ustc.edu.cn/g' /etc/apt/sources.list \
    && apt update && apt --no-install-recommends install  git gcc automake  make g++ autoconf dbus curl file pkg-config shellcheck splint cppcheck  libjpeg8 libtiff5 cmake libudev-dev\
        libtiff5-dev libpng-dev  lua-zlib lua-zlib-dev  doxygen openssl libssl-dev valgrind  -y \
    && rm -rf /var/lib/apt/lists/*
#RUN echo "export PATH=/realsee/bin:/realsee/sbin:$PATH" >> /etc/profile
#RUN echo "export LD_LIBRARY_PATH=/realsee/lib" >> /etc/profile
RUN cp -p /lib/lib/libudev.so.1.6.3 /lib/
COPY ["rc.tar.gz","/etc/init.d/"]
COPY sys_web_ls_href /usr/local/bin
COPY indent /usr/bin
COPY docker_start /usr/local/bin
COPY docker_rpkg /usr/local/bin
RUN tar zxvf  /etc/init.d/rc.tar.gz -C /etc/init.d/ && rm /etc/init.d/rc.tar.gz
ADD var.tar.gz /realsee/var/
ADD include.tar.gz /realsee/
ENV PATH=/realsee/bin:/realsee/sbin:$PATH
ENV LD_LIBRARY_PATH=/realsee/lib
ENV PKG_CONFIG=/usr/bin/pkg-config
ENV PKG_CONFIG_PATH=/realsee/lib/pkgconfig/:/usr/lib/aarch64-linux-gnu/pkgconfig
RUN echo ". /etc/init.d/rc.environment " >> /etc/profile && echo "export DESTDIR=/packdir" >> /root/.bashrc && echo "export DESTDIR=/packdir" >> /etc/profile \
     && echo "/realsee/lib" > /etc/ld.so.conf.d/opencv.conf  && ldconfig /etc/ld.so.conf.d  >> /root/.bashrc
RUN mkdir -p /var/run/dbus ; rm -rf /var/run/dbus/pid 
#RUN apt install jq -y
CMD ["sh","-c","source /etc/profile"]

#对外端口
EXPOSE 80
```

定制镜像，首先要选择一个基础镜像，我们使用Ubuntu官方提供的aarch64的ubuntu镜像进行构建。首先需要通过ADD指令将qemu-aarch64-static二进制文件复制到镜像中，因为本机架构和docker镜像架构不同，如果不使用qemu-aarch64-static动态翻译二进制文件，后面的构建层会遇到指令无法执行的问题。之后在镜像内安装一些测试需要的文件，设置一些环境变量，完成Dockerfile的编写。
然后使用如下命令构建镜像：

```
docker build -t realsee-camera-riemann:0.test.528 .
```

将镜像的名字命名为realsee-camera-riemann，版本为0.test.528。

### 储存位置

在操作系统中(Linux)，默认情况下 Docker 容器的存放位置在 /var/lib/docker 目录下面，可以通过命令查看

```bash
docker info | grep "Docker Root Dir"
```

我们使用docker pull 下载的镜像，都会存在这个目录下，当下载的镜像过多，或容器运行过程中产生大量数据导致存储容量不足时，可以修改镜像储存的位置，有以下几种方式修改docker默认储存位置

#### 使用软链接

+ 首先停止docker 进程
+ 然后进行链接

```bash
#stop
$ sudo systemctl stop docker
#move
$ mv /var/lib/docker /data/docker
#ln
$ ln -sf /data/docker /var/lib/docker
```

+ 然后移动整个 /var/lib/docker 目录到空间比较大的目的路径。这时候启动 Docker 时发现存储目录依旧是 /var/lib/docker 目录，但是实际上是存储在数据盘 /data/docker 上了。

#### 指定容器启动参数

+ 在配置文件中指定容器启动的参数 --graph=/var/lib/docker 来指定镜像和容器存放路径。Docker 的配置文件可以设置大部分的后台进程参数，在各个操作系统中的存放位置不一致。在 Ubuntu 中的位置是 /etc/default/docker 文件，在 CentOS 中的位置是 /etc/sysconfig/docker 文件。

```bash
#Cent 7
# 更改储存位置
$ vi /usr/lib/systemd/system/docker.service
ExecStart=/usr/bin/dockerd --graph /new-path/docker

sudo systemctl restart docker 
```

+ 如果 Docker 的版本是 1.12 或以上的，可以修改或新建 daemon.json 文件。修改后会立即生效，不需重启 Docker 服务。

```bash
# 修改配置文件
$ vim /etc/docker/daemon.json
{
    "registry-mirrors":
        ["http://7e61f7f9.m.daocloud.io"],
    "graph": "/new-path/docker"
}
```

####  System 下创建配置文件

+ 在 /etc/systemd/system/docker.service.d 目录下创建一个 Drop-In 文件 docker.conf，默认 docker.service.d 文件夹不存在，必须先创建它。创建 Drop-In 文件的原因，是我们希望 Docker服务使用 docker.conf 文件中提到的特定参数，将默认服务所使用的位于 /lib/systemd/system/docker.service 文件中的参数进行覆盖。

```
# 定义新的存储位置
$ sudo vi /etc/systemd/system/docker.service.d/docker.conf
[Service]
ExecStart=/usr/bin/dockerd --graph="/data/docker" --storage-driver=devicemapper
# 重启
$ sudo systemctl start docker
```

+ /data/docker 就是新的存储位置，而 devicemapper 是当前 Docker 所使用的存储驱动。如果你的存储驱动有所不同，请输入之前第一步查看并记下的值。现在，你可以重新加载服务守护程序，并启动 Docker 服务了，这将改变新的镜像和容器的存储位置。为了确认一切顺利，运行 docker info 命令检查 Docker 的根目录。

## Docker容器

容器是 Docker 又一核心概念。简单的说，容器是独立运行的一个或一组应用，以及它们的运行态环境。

本节将具体介绍如何来管理一个容器，包括创建、启动和停止等。

启动容器有两种方式，一种是基于镜像新建一个容器并启动，另外一个是将在终止状态（`exited`）的容器重新启动。

因为 Docker 的容器实在太轻量级了，很多时候用户都是随时删除和新创建容器。

### 新建并启动容器

所需要的命令主要为 `docker run`。

例如，下面的命令输出一个 “Hello World”，之后终止容器。

```bash
$ docker run ubuntu:18.04 /bin/echo 'Hello world'
Hello world
```

这跟在本地直接执行 `/bin/echo 'hello world'` 几乎感觉不出任何区别。

下面的命令则启动一个 bash 终端，允许用户进行交互。

```bash
$ docker run -t -i ubuntu:18.04 /bin/bash
root@af8bae53bdd3:/#
```

其中，`-t` 选项让Docker分配一个伪终端（pseudo-tty）并绑定到容器的标准输入上， `-i` 则让容器的标准输入保持打开。

在交互模式下，用户可以通过所创建的终端来输入命令，例如

```bash
root@af8bae53bdd3:/# pwd
/
root@af8bae53bdd3:/# ls
bin boot dev etc home lib lib64 media mnt opt proc root run sbin srv sys tmp usr var
```

当利用 `docker run` 来创建容器时，Docker 在后台运行的标准操作包括：

* 检查本地是否存在指定的镜像，不存在就从registry下载
* 利用镜像创建并启动一个容器
* 分配一个文件系统，并在只读的镜像层外面挂载一层可读写层
* 从宿主主机配置的网桥接口中桥接一个虚拟接口到容器中去
* 从地址池配置一个 ip 地址给容器
* 执行用户指定的应用程序
* 执行完毕后容器被终止

### 启动已终止容器

可以利用 `docker container start` 命令，直接将一个已经终止（`exited`）的容器启动运行。

容器的核心为所执行的应用程序，所需要的资源都是应用程序运行所必需的。除此之外，并没有其它的资源。可以在伪终端中利用 `ps` 或 `top` 来查看进程信息。

```bash
root@ba267838cc1b:/# ps
  PID TTY          TIME CMD
    1 ?        00:00:00 bash
   11 ?        00:00:00 ps
```

可见，容器中仅运行了指定的 bash 应用。这种特点使得 Docker 对资源的利用率极高，是货真价实的轻量级虚拟化。


###停止容器

docker stop可以停止运行的容器。理解：容器在docker host中实际上是一个进程，docker stop命令本质上是向该进程发送一个SIGTERM信号。如果想要快速停止容器，可使用docker kill命令，其作用是向容器进程发送SIGKILL信号。

```bash
        $ docker ps
        CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS                  PORTS               NAMES
        bdf593fda8be        ubuntu:15.10        "/bin/bash"         3 minutes ago       Up 3 minutes (Paused)                       cranky_mclaren                  
    
        $ docker stop bdf593fda8be
            bdf593fda8be
        $ docker ps
        CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
        LM-
  
```

*备注：docker ps 列出容器，默认列出只在运行的容器；加-a可以显示所有的容器，包括未运行的（例如异常退出（Exited）状态的容器）。*

```bash
$ docker ps
    CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
    bdf593fda8be        ubuntu:15.10        "/bin/bash"         18 minutes ago      Up 6 minutes                            cranky_mclaren
$ docker ps -a
    CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS                    PORTS               NAMES
    2a545c90e593        ubuntu:15.10        "/bin/echo -d 'Hello…"   1 second ago        Exited (0) 1 second ago                       blissful_leakey
    bdf593fda8be        ubuntu:15.10        "/bin/bash"              18 minutes ago      Up 6 minutes                                  cranky_mclaren
```


###重启容器

对于已经处于停止状态的容器，可以通过docker start重新启动。

```bash
$ docker start bdf593fda8be
bdf593fda8be
$ docker ps
            CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
            bdf593fda8be        ubuntu:15.10        "/bin/bash"         11 minutes ago      Up 2 seconds                            cranky_mclaren
```

docker start会保留容器的第一次启动时的所有参数。docker restart可以重启容器，其作用就是依次执行docker stop和docker start。容器可能因某种错误而停止运行。对于服务类容器，通常希望它能够自动重启。启动容器时设置--restart就可以达到效果。--restart=always意味着无论容器因何种原因退出（包括正常退出），都立即重启；

```bash
$ docker run -it ubuntu:15.10 /bin/echo --restart=always -d "Hello world"
--restart=always -d Hello world

$ docker ps -a
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS                     PORTS               NAMES
ad0723ad8383        ubuntu:15.10        "/bin/echo --restart…"   9 seconds ago       Exited (0) 8 seconds ago                       gracious_chatelet
2a545c90e593        ubuntu:15.10        "/bin/echo -d 'Hello…"   6 minutes ago       Exited (0) 6 minutes ago                       blissful_leakey
bdf593fda8be        ubuntu:15.10        "/bin/bash"              25 minutes ago      Up 13 minutes                                  cranky_mclaren
```

### 后台运行容器

更多的时候，需要让 Docker 在后台运行而不是直接把执行命令的结果输出在当前宿主机下。此时，可以通过添加 `-d` 参数来实现。下面举两个例子来说明一下。

**如果不使用 `-d` 参数运行容器。**

```bash
$ docker run ubuntu:18.04 /bin/sh -c "while true; do echo hello world; sleep 1; done"
hello world
hello world
hello world
hello world
```

容器会把输出的结果 (STDOUT) 打印到宿主机上面

**如果使用了 `-d` 参数运行容器。**

```bash
$ docker run -d ubuntu:18.04 /bin/sh -c "while true; do echo hello world; sleep 1; done"
77b2dc01fe0f3f1265df143181e7b9af5e05279a884f4776ee75350ea9d8017a
```

此时容器会在后台运行并不会把输出的结果 (STDOUT) 打印到宿主机上面(输出结果可以用 `docker logs` 查看)。

**注：** 容器是否会长久运行，是和 `docker run` 指定的命令有关，和 `-d` 参数无关，只要命令不结束，容器也就不会退出。上述命令中，while语句不会让bash退出，因此该容器就不会退出。

使用 `-d` 参数启动后会返回一个唯一的 id，也可以通过 `docker container ls` 命令来查看容器信息。

```
$ docker container ls
CONTAINER ID  IMAGE         COMMAND               CREATED        STATUS       PORTS NAMES
77b2dc01fe0f  ubuntu:18.04  /bin/sh -c 'while tr  2 minutes ago  Up 1 minute        agitated_wright
```

使用-d启动容器后，会回到host终端；此时如果想要获取容器的输出信息，可以通过 `docker container logs` 命令。

```bash
$ docker container logs [container ID or NAMES]
hello world
hello world
hello world
. . .
```

### 进入容器

在使用 `-d` 参数时，容器启动后会进入后台，启动完容器之后会停在host端；某些时候需要进入容器进行操作，包括使用 `docker attach` 命令或 `docker exec` 命令，推荐大家使用 `docker exec` 命令，原因会在下面说明。

#### `attach` 命令

下面示例如何使用 `docker attach` 命令。

```bash
$ docker run -dit ubuntu
243c32535da7d142fb0e6df616a3c3ada0b8ab417937c853a9e1c251f499f550

$ docker container ls
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
243c32535da7        ubuntu:latest       "/bin/bash"         18 seconds ago      Up 17 seconds                           nostalgic_hypatia

$ docker attach 243c
root@243c32535da7:/#
```

*注意：* 如果从这个 stdin 中exit回到host端，会导致容器的停止。

#### `exec` 命令

`docker exec` 后边可以跟多个参数，这里主要说明 `-i` `-t` 参数。

只用 `-i` 参数时，由于没有分配伪终端，界面没有我们熟悉的 Linux 命令提示符，但命令执行结果仍然可以返回。

当 `-i` `-t` 参数一起使用时，则可以看到我们熟悉的 Linux 命令提示符。

```bash
$ docker run -dit ubuntu
69d137adef7a8a689cbcb059e94da5489d3cddd240ff675c640c8d96e84fe1f6

$ docker container ls
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
69d137adef7a        ubuntu:latest       "/bin/bash"         18 seconds ago      Up 17 seconds                           zealous_swirles

$ docker exec -i 69d1 bash
ls
bin
boot
dev
...

$ docker exec -it 69d1 bash
root@69d137adef7a:/#
```

如果从这个 stdin 中 exit回到host端，但不会导致容器的停止。这就是为什么推荐大家使用 `docker exec` 的原因。


#### attach和exec的区别

 attach和exec的区别：
（1）attach直接进入容器启动命令的终端，不会启动新的进程；
（2）exec则是在容器中打开新的终端，并且可以启动新的进程；
（3）如果想直接在终端中查看命令的输出，用attach，其他情况使用exec；

更多参数说明请使用 `docker exec --help` 查看。

###暂停容器

有时我们只是希望让容器暂停工作一段时间，比如要对容器的文件系统打个快照，或者docker host需要使用CPU，可以执行:docker pause CONTAINER [CONTAINER...]，如图所示：
    ```bash
$ docker pause bdf593fda8be
bdf593fda8be
$ docker ps
        CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS                  PORTS               NAMES
        bdf593fda8be        ubuntu:15.10        "/bin/bash"         3 minutes ago       Up 3 minutes (Paused)                       cranky_mclaren                  
    ```

### 删除容器

可以使用 `docker container rm` 来删除一个处于终止状态的容器。例如

```bash
$ docker container rm trusting_newton
trusting_newton
```

如果要删除一个运行中的容器，可以添加 `-f` 参数。Docker 会发送 `SIGKILL` 信号给容器。

#### 清理所有处于终止状态的容器

用 `docker container ls -a` 命令可以查看所有已经创建的包括终止状态的容器，如果数量太多要一个个删除可能会很麻烦，用下面的命令可以清理掉所有处于终止状态的容器。

```bash
$ docker container prune
```

####批量删除所有已经退出的容器

```bash
$ docker rm -v $(docker ps -aq -f status=exited)
```

### 导出容器

如果要导出本地某个容器，可以使用 `docker export` 命令。

```bash
$ docker container ls -a
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS                    PORTS               NAMES
7691a814370e        ubuntu:18.04        "/bin/bash"         36 hours ago        Exited (0) 21 hours ago                       test
$ docker export 7691a814370e > ubuntu.tar
```

这样将导出容器快照到本地文件。

### 导入容器快照

可以使用 `docker import` 从容器快照文件中再导入为镜像，例如

```bash
$ cat ubuntu.tar | docker import - test/ubuntu:v1.0
$ docker image ls
REPOSITORY          TAG                 IMAGE ID            CREATED              VIRTUAL SIZE
test/ubuntu         v1.0                9d37a6082e97        About a minute ago   171.3 MB
```

此外，也可以通过指定 URL 或者某个目录来导入，例如

```bash
$ docker import http://example.com/exampleimage.tgz example/imagerepo
```

*注：用户既可以使用 `docker load` 来导入镜像存储文件到本地镜像库，也可以使用 `docker import` 来导入一个容器快照到本地镜像库。这两者的区别在于容器快照文件将丢弃所有的历史记录和元数据信息（即仅保存容器当时的快照状态），而镜像存储文件将保存完整记录，体积也要大。此外，从容器快照文件导入时可以重新指定标签等元数据信息。*

## 参考文献

- [使用镜像](https://vuepress.mirror.docker-practice.com/image/)
- [操作容器](https://vuepress.mirror.docker-practice.com/container/)