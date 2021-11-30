# Windows 系统下安装 macOS
[TOC]
由于 iOS 开发必须使用 XCode，而 XCode 又必须运行在 macOS 上，因此为了让使用 Windows 系统的朋友们也能进行 iOS 开发，我们将在 Windows 上通过虚拟机安装 macOS。

## 安装 VMware

操作系统版本：Windows 11 家庭中文版 21H2 22000.194

VMware 版本：16.0.0 build-16894299

macOS 版本：MacOS Catalina-10.15.6



我们将选用 VMware 来安装 macOS，首先下载并安装 VMware Workstation，建议前往官网下载：

https://www.vmware.com/cn/products/workstation-pro/workstation-pro-evaluation.html

安装完毕后需要输入许可密钥，在此提供三个许可密钥：

```
ZF3R0-FHED2-M80TY-8QYGC-NPKYF
YF390-0HF8P-M81RQ-2DXQE-M2UT6
ZF71R-DMX85-08DQY-8YMNC-PPHV8
```

## 安装 VMware macOS 解锁补丁

由于在 Windows 和 Linux 上的 VMware 对安装 macOS 进行了屏蔽，因此需要通过对 VMware 进行“插桩”来解除屏蔽。首先下载解锁补丁：

https://github.com/paolo-projects/unlocker/releases

在运行补丁前需要关闭 VMware 相关的服务：

![server](.\figures\server.png)

将几个 VMware 开头的服务停止。

然后解压下载的 `unlocker.zip` ，以管理员身份运行 `win-install.cmd`，等待补丁自动下载安装完成：

![unlocker](.\figures\unlocker.png)

## 安装 macOS

打开 VMware，新建虚拟机：

![newvm](.\figures\newvm.png)

选择稍后安装操作系统：

![chooseiso](.\figures\chooseiso.png)

如果上一步破解成功，这里会出现 macOS 的选项，选择 macOS 10.15：

![choosemacos](.\figures\choosemacos.png)

剩下的名称、安装位置和磁盘容量根据个人需求选择。

创建完毕后，右击创建的虚拟机 - 设置 - CD/DVD - 连接 - 使用ISO映像文件，选择下载的 cdr 文件：

![chooseiso2](.\figures\chooseiso2.png)

选择完毕后即可开启虚拟机启动安装程序：

![chooselang](.\figures\chooselang.png)

选择磁盘工具：

![disktool](.\figures\disktool.png)

选择并抹掉盘符：

![cleardisk](.\figures\cleardisk.png)

完成之后关闭，并选择安装 macOS：

![chooseinstall](.\figures\chooseinstall.png)

剩下的步骤根据提示完成即可：

![image-20211008200903134](.\figures\installed.png)



直接安装完毕后，会发现存在很多问题，如分辨率较低、鼠标移动缓慢等，此时需要安装 VMware Tool。

首先，将 Install macOS Catalina 推出：

![exitiso](.\figures\exitiso.png)

修改使用的映像文件，改成 VMware 安装路径下的 darwin15.iso：

![darwin15](.\figures\darwin15.png)

然后选择 VMware Tools：

![installwmwaretools](.\figures\installwmwaretools.png)

随后根据提示依次选择继续 -> 安装，并在安全性与隐私窗口选择允许：

![accept](.\accept.png)

随后等待安装完毕，重新启动即可正常使用。随后，在 App Store 中下载 Xcode 即可进行开发：

![installxcode](.\figures\installxcode.png)
