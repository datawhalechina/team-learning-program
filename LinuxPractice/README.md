
# Datawhale Linux组队学习

## Part1 内容介绍

在给大家分享知识的过程中，发现很多同学在学习竞赛都存在较多的问题：

- Linux不会操作，不知道文件目录创建、命令行等细节
- Linux不知道如何运行代码，保存模型
- Pytorch不知道如何从头写代码
- Pytorch不知道定义模型和运行模型

而上述问题都是一个竞赛选手、一个算法工程师所必备的。因此我们将从本月组织一次竞赛训练营活动，希望能够帮助大家入门数据竞赛。在活动中我们将布置具体竞赛任务，然后参与的同学们不断闯关完成，竟可能的帮助大家入门。



## Part2  Linux基础使用


### 学习内容

Linux，全称GNU/Linux，是一种免费使用和自由传播的类UNIX操作系统，其内核由林纳斯·本纳第克特·托瓦兹于1991年10月5日首次发布，它主要受到Minix和Unix思想的启发，是一个基于POSIX的多用户、多任务、支持多线程和多CPU的操作系统。



Linux有上百种不同的发行版，如基于社区开发的debian、archlinux，和基于商业开发的Red Hat Enterprise Linux、SUSE、Oracle Linux等。在全球超级计算机TOP500强操作系统排行榜中，Linux的占比最近十几年长期保持在85%以上，且一直呈现快速上升趋势。根据2016年的排行榜，Linux的占比已经高达98.80%。其实在各种大、中小型企业的服务器应用领域，**在企业内部服务器99%的情况下都是Linux系统，如果你想成为一个合格的软件工程师&算法工程师，Linux是你必备的技能**。


### 打卡汇总


任务名称 | 难度 | 所需技能
---|---|---
使用命令行登录指定的Linux环境 | 低 | ssh
在目录下创建文件夹、删除文件夹 | 低 | rm、mkdir
在目录下下载文件、阅读文件 | 低 | wget、nano
在目录下使用vi或vim编辑文件| 中 | vi、vim
在目录下创建py文件，并进行运行 | 中 | python
在目录下创建py目录，并进行import导入 | 中 | python
在Linux系统中后台运行应用程序，并打印日志 | 中 | nohup
使用grep和awk从文件中筛选字符串 | 高 | grep、awk
在目录下创建zip和tar压缩文件，并进行解压|中|zip、tar
使用find和locate定位文件|低|find、locate

### 学习资料
- https://bilibili.com/video/BV1yr4y1C7RC
- https://bilibili.com/video/BV1Zr4y1F7sQ
- https://bilibili.com/video/BV1S64y1v7UG

### 打卡要求

注：

- 需要使用指定的Linux系统完成打卡任务
- 需要完成所有的任务细节才算完成一个任务

**任务1：使用命令行登录指定的Linux环境**

任务要点：ssh登录、密码输入、环境配置

- 步骤1：配置本地登录环境
    - 如果是window系统，安装任意一款ssh工具
        - https://blog.csdn.net/puss0/article/details/103390947
        - https://www.runoob.com/linux/linux-remote-login.html
    - 如果是Mac或Linux系统，则不需要，可以直接使用ssh
- 步骤2：使用如下信息登录系统
    - 用户名：coggle，密码：coggle，IP：139.198.15.157
    - 如果登录失败，请微信联系coggle小助手

![](https://img-blog.csdnimg.cn/06e512b972e440e7882deb33125f8cc8.png)


**任务2：在目录下创建文件夹、删除文件夹**

任务要点：创建文件夹、创建文件、删除文件、删除文件夹

- 步骤1：学习Linux的目录结构（https://www.runoob.com/linux/linux-system-contents.html）
- 步骤2：学习Linux的文件和目录管理（https://www.runoob.com/linux/linux-file-content-manage.html）
- 步骤3：
    - 在/home/coggle目录下，新建一个以你英文昵称（中间不要有空格哦）的文件夹A
    - 在文件夹A内部创建一个以coggle命令的文件夹B
- 步骤4：在B文件夹内创建一个空txt文件
- 步骤5：删除步骤4创建的文件
- 步骤6：删除文件夹B，然后删除文件夹A


**任务3：在目录下下载文件、阅读文件**

任务要点：下载文件、移动文件、阅读文件

- 步骤1：
    - 在home目录下，新建一个以你英文昵称（中间不要有空格哦）的文件夹A
    - 在文件夹A内部创建一个以coggle命令的文件夹B
- 步骤2：使用wget命令下载https://mirror.coggle.club/dataset/affairs.txt，到文件夹B
    - wget教程：https://www.cnblogs.com/pretty-ru/p/10936023.html
- 步骤3：使用head、cat、tail命令阅读下载的文件。
    - 阅读文件基础教程：https://www.cnblogs.com/jixp/p/10833801.html
- 步骤4：在命令行使用ipython进入python3环境，并使用pandas读取下载的文件。

**任务4：在目录下使用vi或vim编辑文件**

任务要点：vi和vim使用

- 步骤1：学习Nano的使用，https://blog.csdn.net/junxieshiguan/article/details/84104912
- 步骤2：学习Vim的使用，https://www.runoob.com/linux/linux-vim.html
- 步骤3：分别使用Nano和Vim创建py文件，并输入以下内容，并运行

```python
#!/usr/bin/env python3
print('Hello World!')
```

**任务5：在目录下创建py文件，并进行运行**

任务要点：python的os和sys系统接口，文件接口

- 步骤1：学习python下os模块处理文件和目录的函数，https://www.runoob.com/python/os-file-methods.html
- 步骤2：学习python下sys模块和传参函数，https://www.runoob.com/python3/python3-module.html
- 步骤3：在home/coggle目录下，在你英文昵称（中间不要有空格哦）的文件夹中，新建一个test5.py文件，改程序可以使用os、sys模块完成以下功能：
    - 功能1：打印命令行参数Python
    - 功能2：使用os模块打印/usr/bin/路径下所有以m开头的文件。

```python
命令行输入：python3 test5.py 参数1 参数2

程序输出：
test5.py
参数1
参数2
```

**任务6：在目录下创建py目录，并进行import导入**

任务要点：python代码模块化

- 步骤1：学习python模块化，https://www.runoob.com/python3/python3-module.html
- 步骤2：在/home/coggle目录下在你英文昵称（中间不要有空格哦）的文件夹中创建affairs文件夹。
- 步骤3：编写test6.py和affairs.py完成以下功能：
    - 功能1：affairs.py代码完成https://mirror.coggle.club/dataset/affairs.txt文件的读取，这里可以直接pd.read_csv('https://mirror.coggle.club/dataset/affairs.txt')来完成。这一部分建议写为函数。
    - 功能2：test6.py可以导入affairs.py代码
    - 功能3：test6.py可以进行命令行解析，输出affairs.txt具体的第几行内容。

```Python
/home/coggle/    
    你英文昵称命名的文件夹/        
        test6.py        
        affairs/            
        affairs.py
```
实现要求：

```Python
在/home/coggle/你英文昵称命名的文件夹/目录下，可以执行：

python3 test6.py 10
没有bug，并完成第十行内容的输出。
```

**任务7：在Linux系统中后台运行应用程序，并打印日志**

任务要点：程序后台运行，进程管理

- 步骤1：在/home/coggle目录下在你英文昵称（中间不要有空格哦）的文件夹中创建一个sleep.py文件，该文件需要完成以下功能：
    - 程序一直运行
    - 每10秒输出当前时间
- 步骤2：学习 & 和 nohup后台执行的方法
    - https://blog.csdn.net/a736933735/article/details/89577557
    - http://ipcmen.com/jobs
- 步骤3：学习tmux的使用，将步骤1的程序进行后台运行，并将输出结果写入到txt文件。

**任务8：使用grep和awk从文件中筛选字符串**

任务要点：字符筛选

- 步骤1：下载周杰伦歌词文本，并进行解压。https://mirror.coggle.club/dataset/jaychou_lyrics.txt.zip
- 步骤2：利用grep命令完成以下操作，并输出到屏幕
    - https://blog.csdn.net/baidu_41388533/article/details/107610827
    - https://www.runoob.com/linux/linux-comm-grep.html
    - 统计歌词中 包含【超人】的歌词
    - 统计歌词中 包含【外婆】但不包含【期待】的歌词
    - 统计歌词中 以【我】开头的歌词
    - 统计歌词中 以【我】结尾的歌词
- 步骤3：利用sed命令完成以下操作，并输出到屏幕https://www.cnblogs.com/JohnLiang/p/6202962.html
    - 将歌词中 第2行 至 第40行 删除
    - 将歌词中 所有【我】替换成【你】

**任务9：在目录下创建zip和tar压缩文件，并进行解压**

任务要点：文件压缩https://www.cnblogs.com/wxlf/p/8117602.html

- 步骤1：在/home/coggle目录下在你英文昵称（中间不要有空格哦）的文件夹中，下载https://mirror.coggle.club/dataset/jaychou_lyrics.txt.zip
- 步骤2：使用zip 压缩/home/coggle目录下在你英文昵称（中间不要有空格哦）的文件夹
- 步骤3：将 /home/coggle目录下在你英文昵称（中间不要有空格哦）的文件夹，打包为tar格式。
- 步骤4：将 /home/coggle目录下在你英文昵称（中间不要有空格哦）的文件夹，打包为tar.gz格式。


**任务10：使用find和locate定位文件**

任务要点：文件搜索
 
- https://www.runoob.com/linux/linux-comm-find.html
- https://www.cnblogs.com/linjiqin/p/11678012.html
- 步骤1：使用find统计文件系统中以py为后缀名的文件个数
- 步骤2：使用find寻找/home/文件夹下文件内容包含coggle的文件
- 步骤3：时候用locate寻找到python3.preinst文件


## Part3 提问&回答

问：具体的活动是怎么安排的？

> 有任务，自己先尝试，然后之后会视频演示和讨论。

问：环境和配置是什么？

> Linux上进行学习，python3环境
