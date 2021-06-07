# 4. Linux用户和组管理

## 4.1、用户与用户组

Linux是多用户多任务的操作系统，需要使用系统资源则需向系统管理员申请账户进入系统。

用户又分*超级用户*和*普通用户*，超级用户即系统管理员root.

每个用户都会属于一个用户组，例如某几个用户需要同样的权限的时候，我们可以把这几个用户归属于同一个用户组，对用户组进行集中管理。

用户与用户组之间的关系又可以分为以下四种：

1. 一对一：一个用户可以存在一个组中，是组中的唯一成员；
2. 一对多：一个用户可以存在多个用户组中，此用户具有这多个组的共同权限；
3. 多对一：多个用户可以存在一个组中，这些用户具有和组相同的权限；
4. 多对多：多个用户可以存在多个组中，也就是以上 3 种关系的扩展。

第一种较常见，因为Linux添加用户的时候会自动创建同名的用户组。

#### 4.1.1、一个用户一个用户组？

Linux 系统中多数用户账户被设为用户名与用户组名相同。用户 jdoe 会被赋予一个名为jdoe 的用户组，且成为该新建用户组的唯一成员。如本例所示，该用户的登录名，用户 id(UID) 和用户组id(GID) 在新建账户时会被添加到` /etc/passwd` 和 `/etc/group` 文件中：

```
$ sudo useradd jdoe
$ grep jdoe /etc/passwd
jdoe:x:1066:1066:Jane Doe:/home/jdoe:/bin/sh
$ grep jdoe /etc/group
jdoe:x:1066:
```

这些文件中的配置使系统得以在文本（jdoe）和数字（1066）这两种用户id 形式之间互相转换—— jdoe 就是 1006，且 1006 就是jdoe 。
分配给每个用户的 UID（用户 id）和 GID（用户组 id）通常是一样的，并且顺序递增。若上例中 Jane Doe 是最近添加的用户，分配给下一个新用户的用户 id 和用户组 id 很可能都是 1067。



#### 4.1.2、GID = UID？

UID 和 GID 可能不一致。例如，如果你用 `groupadd` [命令](https://www.linuxcool.com/)添加一个用户组而不指定用户组 id，系统会分配下一个可用的用户组 id（在本例中为 1067）。下一个添加到系统中的用户其 UID 会是 1067 而 GID 则为 1068。

你可以避免这个问题，方法是添加用户组的时候指定一个较小的用户组 id 而不是接受默认值。在下面的[命令](https://www.linuxcool.com/)中我们添加一个用户组并提供一个 GID，这个 GID 小于用于用户账户的 GID 取值范围。

```
$ sudo groupadd -g 500 devops
```

创建账户时你可以指定一个共享用户组，如果这样对你更合适的话。例如你可能想把新来的开发人员加入同一个 DevOps 用户组而不是一人一个用户组。

```
$ sudo useradd -g staff bennyg
$ grep bennyg /etc/passwd
bennyg:x:1064:50::/home/bennyg:/bin/sh
```

在Linux中，操作系统**根据UID来判断用！根据UID来判断用户！** 而不是用户名！**只要id为0就是管理员，哪怕有多个id为0 的账号**。

系统在新建账号时，会根据账号类型，自动分配递增账号的UID与GID （用户身份编号，组编号）,也可自行分配。**通常情况下，应当保证UID与GID唯一且不重复。**



#### 4.1.3、用户组

用户组实际上有两种：主要用户组*primary group*和次要用户组*secondary group*。

主要用户组是保存在 `/etc/passwd` 文件中的用户组，该用户组在账户创建时配置。当用户创建一个文件时，用户的主要用户组与此文件关联。

```
$ whoami
jdoe
$ grep jdoe /etc/passwd
jdoe:x:1066:1066:John Doe:/home/jdoe:/bin/bash
          ^
          |
          +-------- 主要用户组
$ touch newfile
$ ls -l newfile
-rw-rw-r-- 1 jdoe jdoe 0 Jul 16 15:22 newfile
                ^
                |
                +-------- 主要用户组
```

用户一旦拥有账户之后被加入的那些用户组是次要用户组。次要用户组成员关系在 `/etc/group` 文件中显示。

```
$ grep devops /etc/group
devops:x:500:shs,jadep
       ^
       |
       +-------- shs 和 jadep 的次要用户组
```

`/etc/group` 文件给用户组分配组名称（例如 500 = devops）并记录次要用户组成员。

****

## 4.2 用户与组的配置文件

​	在Linux中，**万物皆文件**，所以用户与组也以配置文件的形式保存在系统中，以下为用户和组的主要配置文件：

> - `/etc/passwd`：用户及其属性信息（名称、UID、主组ID等）
> - `/etc/group`：组及其属性信息
> - `/etc/shadow`：用户密码及其相关属性
> - `/etc/gshadow`：组密码及其相关属性



1. passwd文件格式：

```
Father:x:887:887:Father:/data/Father:/sbin/bash
```

含义：登录用户名:密码:UID:GID:全名或注释:用户主目录:用户默认使用shell



2. group文件格式：

```
Father:x:887:
```

含义：群组名称:群组密码(通常不需要设定，密码被记录在`/etc/gshadow`):GID:附加组(以逗号","分割，该用户没有附加组，因此为空)



3. shadow文件格式：

```
Father:$6$ZwGNKTt0$i31E8.EpmX6nIMR6R9k8kx3i/Zt8HpiUiiiuzx1.S1DJPHvrbiRV5DFI1APyU0WaGA4yfPiYi.d/M7eGQRAJ81:17902:0:99999:7:::
```

含义：登录用户名:密码(通常使用shad512加密):从1970年1月1日起计算到现在为止密码最近一次被更改的时间:密码再过几天就可以被修改(0表示随时可以修改):密码几天后必须变更(99999表示永不过期):密码过期前多久提示用户:密码过期多久后账户将被锁定:多少天后账户将失效(从1970-1-1算起)



4. gshadow文件格式：

```
Father:!::
```

含义：群组名称:群组密码:组管理员列表:当前用户的附加组



#### 4.2.1、用户管理

想要知道, 系统中有哪些用户, 可以查看这个文件: `/etc/passwd`

```
root:x:0:0:root:/root:/bin/bash daemon:x:1:1:daemon:/usr/sbin:/bin/sh bin:x:2:2:bin:/bin:/bin/sh sys:x:3:3:sys:/dev:/bin/sh sync:x:4:65534:sync:/bin:/bin/sync games:x:5:60:games:/usr/games:/bin/sh man:x:6:12:man:/var/cache/man:/bin/sh lp:x:7:7:lp:/var/spool/lpd:/bin/sh mail:x:8:8:mail:/var/mail:/bin/sh news:x:9:9:news:/var/spool/news:/bin/sh uucp:x:10:10:uucp:/var/spool/uucp:/bin/sh proxy:x:13:13:proxy:/bin:/bin/sh www-data:x:33:33:www-data:/var/www:/bin/sh
```

文件格式:

```
Name:password:ID:group ID:comment:home directory:login shell 用户名:密码:ID:用户组ID:注释:家目录:登录使用的shell
```

你会发现密码已经用'x'替换掉了, 基于安全考虑, 密码的密文是存储在文件`/etc/shadow`中, 而且一般只有超级用户可以访问。



#####  1. 查询账号

```
id [opentions] [User]
```

相关参数：

- `-u`：显示UID
- `-g`：显示GID
- `-G`：显示用户所属的组ID
- `-n`：显示名称

##### 2. 用户口令的管理

用户管理中一项重要内容是**用户口令的管理**。用户账号刚创建时没有口令，但被系统锁定，无法使用(在passwd文件中，密码显示为`!!`或`!`表示用户被锁定，是无法登陆的)，必须为其指定口令后才能使用，即使是指定空口令。

指定和修改用户口令的Shell命令是`passwd`。超级用户可以为自己和其他用户指定口令，普通用户只能用它修改自己的口令。

命令格式：

```
passwd 选项 用户名
```

可使用的选项：

- `-l`：锁定口令，即禁用账号
- `-u`：口令解锁
- `-d`：使账号无口令
- `-f`：强迫用户下次登录时修改口令



为账户指定空口令时，执行下列实行的命令：

```
# passwd -d sam
```

此命令将用户sam的口令删除，这样用户sam下次登录时，系统就不再询问口令。



`passwd`口令还可以用`-l`(lock)锁定某一用户，使其不能登录：

```
# passwd -l sam
```



#### 4.2.2 用户组管理

想要知道, 系统有哪些用户组, 可以查看`/etc/group`

```
root:x:0: daemon:x:1: fax:x:21: voice:x:22: cdrom:x:24:anthony floppy:x:25:anthony tape:x:26: dip:x:30:anthony www-data:x:33: sasl:x:45: plugdev:x:46:anthony staff:x:50: sambashare:x:107: anthony:x:1000: smmta:x:108: smmsp:x:109: dkim-filter:x:110:
```

文件格式:

```
Group name:password:group ID:user list 用户组名:密码:用户组ID:用户列表
```

用户组密码现在基本已经不用了, 'x'就是表示没有密码。



##### 1. 更改查看组成员

```
# groupmems [opentions] [action]
```

参数：

- `-g`：更改为指定组(只有root可以使用)
- `-a`：指定用户加入组
- `-d`：从组中删除该用户
- `-p`：从组中清楚所有成员
- `-l`：显示组成员列表

****

## 4.3、创建用户和用户组

### 4.3.1、用户
##### 1. 添加新用户账号
我们可以通过'useradd'命令添加新用户:

```
# useradd 选项 用户名
```

参数说明：

- 选项

  - `-c comment` 指定一段注释性描述。

  - `-d 目录` 指定用户主目录，如果此目录不存在，则同时使用-m选项，可以创建主目录。

  - `-g 用户组` 指定用户所属的用户组。

  - `-G 用户组`，用户组 指定用户所属的附加组。

  - `-s Shell文件` 指定用户的登录Shell。

  - `-u 用户号` 指定用户的用户号，如果同时有-o选项，则可以重复使用其他用户的标识号。

  - 用户名:

    指定新账号的登录名，上方命名为username。

```
# useradd username
```

默认情况, 这个命令会为新用户在`/home`目录下创建一个与新用户同名(username)的目录, 作为新用户的根目录; 而且这个用户没有过期时间限制, 隶属于默认的用户组, 使用Bash作为登录shell。

**实例**：

```
# useradd -d /home/sam -m -sam
```

此命令创建了一个用户sam，其中`-d`和`-m`选项用来为登录名sam产生一个主目录`/home/sam`



> **哪里也不如自己的家目录**
>
> 添加新账户时一个重要的细节是 useradd 命令并不一定为新用户添加一个家目录`/home`家目录。若你只有某些时候想为用户添加家目录，你可以在 useradd 命令中加入 -m 选项（可以把它想象成“安家”选项）。
>
> ```
> $ sudo useradd -m -g devops -c "John Doe" jdoe2
> ```
>
> 此命令中的选项如下：
> -m 创建家目录并在其中生成初始文件
> -g 指定用户归属的用户组
> -c 添加账户描述信息（通常是用户的姓名）
> 若你希望总是创建家目录，你可以编辑`/etc/login.defs` 文件来更改默认工作方式。更改或添加 CREATE_HOME 变量并将其设置为 yes：
>
> ```
> $ grep CREATE_HOME /etc/login.defs
> CREATE_HOME     yes
> ```
>
> 另一种方法是用自己的账户设置别名从而让 useradd 一直带有 -m 选项。
>
> ```
> $ alias useradd=’useradd -m’
> ```
>
> 确保将该别名添加到你的`~/.bashrc` 文件或类似的启动文件中以使其永久生效。



##### 2. 修改用户账号

修改用户账号就是根据实际情况更改用户的有关属性，如用户号、主目录、用户组、登录Shell等。

修改已有用户的信息使用`usermod`命令：

```
usermod 选项 用户名
```

常用的选项包括`-c, -d, -m, -g, -G, -s, -u`以及`-o`等，这些选项的意义与`useradd`命令中的选项一样，可以为用户指定新的资源值。

另外，有些系统可以使用选项：`-l` 新用户名。

```
# usermod --login old_login_name new_login_name
```

简单地说, usermod命令通过修改系统中, 与用户相关的配置文件`(/etc/password, /etc/shadow/ …)`, 改变用户信息, 如登陆名, 用户组, 登陆密码...

具体情况请查看`”man usermod”`

**实例**：

```
# usermod -s /bin/ksh -d /home/z -g developer sam
```

此命令将用户sam的登录Shell修改为ksh，主目录改为`/home/z`，用户组改为developer。



##### 3. 添加用户至用户组

如果你想添加一个已有用户至别的用户组，你可以仿照下面的命令操作：

```
$ sudo usermod -a -G devops jdoe
```

你也可以指定逗号分隔的用户组列表来添加一个用户至多个用户组：

```
$ sudo usermod -a -G devops,mgrs jdoe
```

参数 `-a` 意思是“添加”，`-G` 指定用户组列表。

你可以编辑`/etc/group` 文件将用户名从用户组成员名单中删除，从而将用户从用户组中移除。`usermod` 命令或许也有个选项用于从用户组中删除某个成员。

```
fish:x:16:nemo,dory,shark
        |
        V
fish:x:16:nemo,dory
```



##### 3. 删除用户账号

如果一个用户的账号不再使用，可以从系统中删除。删除用户账号就是要将`/etc/passwd`等系统文件中的该用户记录删除，必要时还删除用户的主目录。

删除一个已有的用户账号使用`userdel`命令，其格式如下：

```
userdel 选项 用户名
```

常用的选项是 `-r`，它的作用是把用户的主目录一起删除。

例如：

```
# userdel -r sam
```

此命令删除用户sam在系统文件中（主要是`/etc/passwd, /etc/shadow, /etc/group`等）的记录，同时删除用户的主目录。

### 4.3.2、用户组
##### 1. 添加新用户组

```
# groupadd 选项 用户组
```

可以使用的选项有：

- `-g GID` 指定新用户组的组标识号（GID）。
- `-o` 一般与`-g`选项同时使用，表示新用户组的GID可以与系统已有用户组的GID相同。

**实例**：

```
# groupadd groupname
```

此命令向系统中增加了一个新组groupname，新组的组标识号是在当前已有的最大组织标识号的基础上加1。通过命令`"groupadd"`添加用户组, 相当于在`/etc/group`文件中新增一条记录. `“groupadd groupname”` 是添加用户组最简单的方法, 该命令其他参数, 可以查看`"man groupadd"`



##### 2. 修改用户组

```
# groupmod 选项 用户组
```

常用的选项有：

- `-g GID` 为用户组指定新的组标识号。
- `-o` 与`-g`选项同时使用，用户组的新GID可以与系统已有用户组的GID相同。
- `-n 新用户组` 将用户组的名字改为新名字

**实例1**：

```
# groupmod --new-name old_groupname new_groupname
```

与`”usermod”`类似, 但操作对象是用户组(group). 具体使用方法可以查看`"man groupmod"`

**实例2**：

```
# groupmod -g 102 group2
```

此命令将组group2的组标识号修改为102。



##### 3. 删除用户组

```
# groupdel groupname
```

此命令从系统中删除groupname，删除用户组及相关文件。



##### 4. 用户在用户组间切换

用户可以在登录后，使用命令`newgrp`切换到其他用户组，这个命令的参数就是目的用户组。例如：

```
$ newgrp root
```

这条命令将当前用户切换到root用户组，前提条件是root用户组确实是该用户的主组或附加组。类似于用户账号的管理，用户组的管理也可以通过集成的系统管理工具来完成。

****

## 参考资料

[1] https://www.linuxprobe.com/linux-group.html

[2] https://www.linuxprobe.com/common-user-group.html

[3] https://www.runoob.com/linux/linux-user-manage.html

[4] https://www.cnblogs.com/ddz-linux/p/10467106.html

[5] http://c.biancheng.net/linux_tutorial/60/