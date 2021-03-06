本篇主要介绍 Python 的安装与环境配置方法。Python作为一门通用型的编程语言，可以通过很多方法完成安装，同时，也可根据实际需求搭建不同类型的开发环境。由于 Jupyter 是最通用的开发环境，同时，Jupyter 本身也是 Notebook 形式的开发环境，非常适合初学者上手使用。因此，本次内容将主要采用 Jupyter Notebook/Jupyter Lab 来进行演示，本篇也将详细介绍如何通过通用科学计算平台 Anaconda 来进行 Python和Jupyter 的安装。


# 1 Anaconda下载与安装

Anaconda 是一个工具包，将 Python 和许多与科学计算相关的库捆绑在一起，形成了一个方便的科学计算环境，你安装了 Ananconda 就相当于安装了 Python 外加这些库，省去了自己下载和安装各种库的麻烦，方便初学者专注于学习 Python。

![Anaconda](https://img-blog.csdnimg.cn/20210116220515354.png)

此处，我们采用 Anaconda 进行 Python 安装和开发环境搭建。


## 1.1 下载Anaconda

访问Anaconda官网（https://www.anaconda.com），在下拉菜单中的 Products 里选择 Individual Edition，个人版，同时也是免费版。此版本中不涉及付费内容，可供个人用户使用。

![下载Anaconda](https://img-blog.csdnimg.cn/202101162206290.png)

进入页面后，点击Download，会自动跳转到操作系统选择的界面。

![下载Anaconda](https://img-blog.csdnimg.cn/20210116220743451.png)

此时，可根据自身操作系统进行选择和下载。

![选择安装的OS](https://img-blog.csdnimg.cn/20210116220927323.png)

当然，windows 64的用户也可直接通过下述网盘连接进行下载，该版本为 Anaconda3-2020.11-Windows-x86_64。

- 链接：https://pan.baidu.com/s/1IEasB0epWpPRhgYdgSCHaA 
- 提取码：i6zj

## 1.2 安装Anaconda

下载完成后，即可开始安装。双击安装文件，进入欢迎界面，点击 Next。

![安装Anaconda](https://img-blog.csdnimg.cn/20210116221119969.png)

点击同意，进入到下一步。

![安装Anaconda](https://img-blog.csdnimg.cn/20210116221214469.png)

选择软件使用权限，是指针对当前登录用户还是所有用户，二者都行，无特殊要求。

![安装Anaconda](https://img-blog.csdnimg.cn/20210116221311317.png)

选择安装位置，完成安装。

![安装Anaconda](https://img-blog.csdnimg.cn/20210116221406406.png)

如果出现此页面，需要勾选配置环境变量选项。

![安装Anaconda](https://img-blog.csdnimg.cn/20210116221521364.png)

无需安装VS Code，直接跳过即可。

![安装Anaconda](https://img-blog.csdnimg.cn/20210304211327560.png)

安装完成后，在开始菜单栏，或者软件安装位置，找到 Anaconda Navigator 并打开。

![Anaconda Navigator](https://img-blog.csdnimg.cn/20210116222105775.png)

进入到如下界面。

![Anaconda Navigator](https://img-blog.csdnimg.cn/20210116223127877.png)

我们能看到 Anaconda 中集成了非常多数据科学计算相关的功能，并且，在安装过程中，也完成了 Python 的安装和环境变量的设置，以及 Jupyter和PyCharm 的安装。其中 Jupyter 是本次学习将用到的代码编辑工具，而 PyCharm 则是一款集成开发环境（IDE），本次学习并不涉及。


## 1.3 启动Jupyter

我们能够看到，在 Anaconda 中有两个 Jupyter 组件，一个是**Notebook**，一个是**JupyterLab**。其中，Lab 是 Notebook 的升级版，用户交互界面更加友好，并且拥有许多额外辅助功能，例如代码框分屏、文件管理系统等，但相比 Notebook，Lab 并不支持第三方插件，因此如果是想使用 Jupyter 丰富的插件，则只能选择 Notebook。不过二者在实际编程的功能使用上没有区别，本次学习推荐使用 JupyterLab。

点击 JupyterLab，启动相关服务，系统会自动打开浏览器并进入到 JupyterLab 界面。

![JupyterLab](https://img-blog.csdnimg.cn/20210116222942554.png)

能够成功弹出浏览器窗口，则说明安装成功。如果浏览器关闭，再次点击 Anaconda 中 Jupyter Lab 组件中的 Launch 即可再次打开 Jupyter 界面。

![JupterLab](https://img-blog.csdnimg.cn/20210116223349824.png)

或者在浏览器里直接输入 http://localhost:8890/lab。


---
# 2 Jupyter基本操作

接下来，简单介绍 Jupyter 的基本操作。

## 2.1 简单代码编写尝试

在 JupyterLab 主界面中，左边是文件目录，右边是编程界面，首次登陆时，点击 Python3 即可创建一个新的编程文件。

![新建Python3文件](https://img-blog.csdnimg.cn/20210116223814229.png)

如下所示：

![新建Python3文件](https://img-blog.csdnimg.cn/20210116223946535.png)

同时，在左侧文件目录，也会出现一个新的`ipynb`文件，也就是正在编辑的代码文件。

> ipynb 文件是 ipython Notebook 的简写，Jupyter 脱胎于 ipython 编辑器，因此 Jupyter 文件仍然保留了 ipynb 的文件类型命名方式。

接下来，简单尝试在右侧代码框中输入 Python 代码。点击右侧代码框（cell）中输入`a = 1`。



![Cell](https://img-blog.csdnimg.cn/20210116224321371.png)

也就是令`a = 1`，然后`shift+enter`执行该代码。执行完成后，会自动新生成一个 cell，接下来的代码就可以在新生成的 cell 中执行。在新生成的 cell 中，输入`a`能够看到，返回结果就是`a`的赋值。

![输出a](https://img-blog.csdnimg.cn/20210116224500349.png)

至此，我们就完成了一次简单的 Python 代码编写和运行。

## 2.2 Notebook式编辑环境

将代码写入一个个**cell**，代码文件由一个个cell组成，书写代码时就像一行一行在记笔记，就是所谓的 Notebook 式的代码编辑环境。Notebook 式代码编辑环境其实也是 REPL（Read Eval Print Loop）环境的一种，即交互式编译。简单来说，交互式编译就是指允许用户逐行输入代码、逐行查看结果，从而逐行进行调试。这无疑是大幅降低了代码编写的难度，这也是建议 Python 初学者使用 Jupyter 的原因。

## 2.3 Jupyter的基本操作

由于后续 Jupyter 将作为主力代码编辑器，因此我们有必要深入了解 Jupyter 的一些常用功能。当然，Jupyter 本身也是一个独立的软件，具体软件的功能介绍可以查看 [Jupyter](https://jupyter.org/) 官网（https://jupyter.org），里面有 Jupyter 所有功能的完整介绍。

![Jupyter](https://img-blog.csdnimg.cn/20210116224919888.png)

此处先介绍实际学习过程中常用的功能。

### （1）cell类型选择

在 Jupyter 中，每个 cell 除了代码以外，还可以使用 Markdown 语法输入文本内容，以及尚未确定格式的草稿。
- 选定一个 cell 后，选择 code 则是代码内容；
- 选择 Markdown 则是使用 Markdown 语法输入文本内容；
- 选择 Raw 则是草稿内容，不会输出任何结果。

![Cell格式](https://img-blog.csdnimg.cn/20210116225405249.png)

例如，使用Markdown语法打印标题：

![Markdown格式](https://img-blog.csdnimg.cn/20210116225630969.png)

同样，是`shift+enter`执行 Markdown 语法。

![Markdown格式](https://img-blog.csdnimg.cn/20210116225735316.png)

可以看出，jupyter 还是个不错的笔记工具，同时，也非常适合编写数据分析报告。

### （2）cell不同模式及快捷键

cell 有两种不同模式，选中 cell 时是 **command（命令）** 模式，而单击 cell 内，出现光标闪烁时，则是进入了 cell 内容的 **edit（编辑）** 模式，在编辑模式下，可以进行内容输入，而在命令模式下，则可使用一些 cell 快捷键对其进行操作。


快捷键 | 操作| 快捷键 | 操作
:---:|---|:---:|---
a | 在上方插入一个cell | b | 在下方插入一个cell
x | 剪切该cell | c | 复制该cell
v | 在cell下方粘贴复制的cell | m | 转为markdown模式
y | 转为code模式 | r | 转为raw模式
z | 撤销操作 | 双击d | 删除该cell


### （3）JupyterLab 文件管理系统

相比 Notebook，JupyterLab 拥有非常便捷的文件管理系统，我们前面已经尝试，当创建一个新的`ipy`文件时，左侧文件栏将出现对应文件。JupyterLab 左侧就是其文件管理界面，在其中，我们可以进行文件创建、文件夹创建、文件上传等操作。

![文件管理系统](https://img-blog.csdnimg.cn/20210116230936963.png)


### （4）JupyterLab 文件系统主目录及修改方式

那么，我们创建的`ipy`文件存在哪呢？<br>
在 Anaconda 中，一般系统会默认 Jupyter 的主目录就是系统的文档目录。但文档目录在 C 盘下，如果是首次安装 Jupyter，并希望单独设置一个文件夹作为默认主目录，可以按照如下步骤进行操作：


**在 Anaconda 中打开 CMD.exe Prompt，进入命令行界面**

![CMD.exe Launch](https://img-blog.csdnimg.cn/20210116231238985.png)

当然，此处也可以`win+r`，然后输入`cmd`进入命令行。

![win+r](https://img-blog.csdnimg.cn/20210116231553772.png)

![console](https://img-blog.csdnimg.cn/20210116231701231.png)


**生成 Jupyter 配置文件**

在命令行中，输入

```python
jupyter notebook --generate-config
```

![生成Jupyter配置文件](https://img-blog.csdnimg.cn/20210116232216845.png)

注意上述配置文件的保存路径。若已有配置文件，再次输入命令将可选择是否覆盖原配置文件。当然，覆盖原配置文件将导致原配置失效。

![生成Jupyter配置文件](https://img-blog.csdnimg.cn/20210116232619111.png)

**修改主目录配置**

接下来，按照命令行中提示的配置文件路径，找到配置文件。

![config文件](https://img-blog.csdnimg.cn/20210116232843233.png)

可以用文本编辑器打开，能够看到所有的 Jupyter 可选配置。

![配置文件内容](https://img-blog.csdnimg.cn/20210116233019653.png)

`ctrl+f`进入搜索栏，搜索`c.NotebookApp.notebook_dir`

![查找内容](https://img-blog.csdnimg.cn/20210117001217743.png)

将对应位置的#号删除，使其配置生效，并在等号后面输入新的主目录文件夹位置（自行选择文件位置），保存退出，并在**重启Jupyter后生效**。


**查看新的主目录**

进入对应文件夹位置，查看文件夹内文件和 JupyterLab 内显示文件是否一致。

![新的主目录](https://img-blog.csdnimg.cn/20210117001556793.png)

![新的主目录](https://img-blog.csdnimg.cn/20210117001703673.png)

至此，新的主目录文件设置成功。当然，任何对主目录文件的操作都会同步至 JupyterLab 的文件栏页。

> 不难发现，Jupyter文件系统主目录就类似于其他编程语言的操作空间概念。

### （5）停止`ipy`进程

由于 Python 代码在运行过程中，对象都存储在内存中，因此，为了合理控制内存，在必要的情况下需要手动终止 Jupyter 进程。此时可以使用左侧栏的 KERNEL SESSIONS 功能，进行操作。

![关闭进程](https://img-blog.csdnimg.cn/20210117001949969.png)

点击 SHUT DOWN 即可关闭对话。

