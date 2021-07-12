
# 如何在Windows中安装Python？

## 1. Python的安装

官网下载：https://www.python.org/downloads/windows/

点开上面的链接，会发现有很多版本。

![](https://img-blog.csdnimg.cn/20210710194331220.png)


首先看版本，64-bit是64位版本，32-bit是32位版本，你需要下载跟你电脑系统一致的版本。
怎么看自己的电脑是什么版本？

<b>右键计算机-属性</b>

![](https://img-blog.csdnimg.cn/20210710194519958.png)

<b>查看一下系统类型</b>

![](https://img-blog.csdnimg.cn/20210710194548804.png)

<b>然后再看文件类型</b>

![](https://img-blog.csdnimg.cn/20210710194331220.png)

- 嵌入式程序包（Embeddedable）是压缩包版本，即便携版，解压可用。
- 安装程序（Installer）是可执行的安装版本，即离线版，下载到本地后可以直接安装。

建议使用离线安装版（Installer），这样软件会帮你设置系统变量，否则需要自己添加，对新手来说当然越傻瓜化越好。

下载完后打开

![](https://img-blog.csdnimg.cn/20210710210948149.png)


出现这个界面的话，说明你的电脑已经安装过Python了，直接关掉窗口，跳到IDLE部分。

第一次安装的话，是这个界面。

![](https://img-blog.csdnimg.cn/2021071021104484.png)

![](https://img-blog.csdnimg.cn/20210710211130802.png)

![](https://img-blog.csdnimg.cn/20210710211207376.png)

![](https://img-blog.csdnimg.cn/20210710211243327.png)

安装成功！

测试一下，能否调用，同时按下win+R（win就是开始菜单的那个键）

![](https://img-blog.csdnimg.cn/20210710211437673.png)

进入命令行，输入python，出现这样的界面则表示成功安装

![](https://img-blog.csdnimg.cn/20210710211515143.png)

这样我们第一步已经完成了。

输入`print('Hello World!')`，写下你的第一句Python代码。

![](https://img-blog.csdnimg.cn/20210710211555218.png)

Welcome To Python's World！！

欢迎来到Python的世界里，不过我们不会在这个黑框框里面写代码的，而是使用Python自带的编辑器IDLE。

输入`exit()`后，回车即退出Python环境。另外，我们还要测试一下pip是否安装完毕，它是用来安装第三方库的神器，我们以后会接触到。

退出Python环境后，我们直接在这里面输入pip，然后回车。

![](https://img-blog.csdnimg.cn/20210710211743555.png)

这样就是安装完毕的意思了。

## 2. IDLE的使用

IDLE是在Python安装时自动安装的一个集成开发环境（IDE），事实上，这也是我目前见过的最最轻量的集成开发环境了。

刚运行时会以交互模式进入，界面如下图所示：


![](https://img-blog.csdnimg.cn/2021071021255168.png)


```python
for x in range(5):
    print(x)
```

先来简单说明一下这个程序是什么意思，它的功能很简单就是在屏幕上打印出从0到4这5个数字，每打印完一个数字就换一行，所以它的输出结果如下：

![](https://img-blog.csdnimg.cn/20210710214841795.png)

你可以在交互模式下直接输入这两行，按下两次回车就可以看到这5个数字了。

<b>在这里我说一下怎么打开IDLE自带的文本编辑器</b>。

没错！跟其他软件差不多，选择左上角的“File”键，然后选择New File，新建一个文件，也可以直接快捷键Ctrl+N。

要打开一个文件，在File里选择Open，然后选择要打开的文件即可，快捷键Ctrl+O。

新建文件后，把两行程序输入到文件中，然后看菜单栏，如图：

![](https://img-blog.csdnimg.cn/20210710213406109.png)

写完了之后要保存，跟office软件一样，保存选项就在File里面，直接去找就可以了，或是快捷键Ctrl+S。

写完代码之后我们就要知道怎么运行了，找到菜单栏中的Run按钮，选择`run module`，就可以在一开始看到的交互模式中显示运行结果了。

<b>写完代码能运行还不行，我们还得会调试，下面说一说怎么调试</b>：

选中要设置断点的一行，单击鼠标右键，选择`set breakpoint`，就会看到设置断点的那一行被黄色高亮。

![](https://img-blog.csdnimg.cn/20210710214209265.png)

然后，回到 IDLE Shell（就是刚打开时的那个交互模式），选择`Debug --> debuggeer`，启动调试器，这时会弹出一个调试窗口。

![](https://img-blog.csdnimg.cn/20210710214118298.png)

调试器启动后，`run --> run module`运行代码，但不同的是，这次程序会停止在设置断点的那一行，而不是全部运行完毕，在调试窗口中选择“Go”就可以使程序继续向下执行一行。在执行过程中，程序中所有的变量的值都会显示在下面的会话框中。

![](https://img-blog.csdnimg.cn/20210710214622939.png)

这样一步一步调试，观察每一个变量就可以找到程序中的逻辑错误了。

## 3. 写在最后

如果第一次打开IDLE时，发现交互模式的字体和字号大小有辣眼睛的话，可以在`Option --> Configure IDLE`中更改。

![](https://img-blog.csdnimg.cn/20210710214732226.png)

