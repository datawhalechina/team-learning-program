# 「Datawhale 开源 408 计划」——《深入理解计算机系统》（Computer Systems: A Programmer's Perspective）
## 前言
本部分为「**Datawhale 开源 408 计划**」首篇，旨在通过计算机科学领域经典丛书：《深入理解计算机系统》（Computer Systems: A Programmer's Perspective，CSAPP）帮助更多的小伙伴理解计算机真实的运行过程与逻辑。Datawhale开源社区将全程支持并提供CSAPP原书习题、lab伴读、课件制作等内容。

本部分为《深入理解计算机系统》原书第一部分内容（前六章）。我们鼓励小伙伴们使用「费曼学习法」：”纸上得来终觉浅，绝知此事要躬行。“课件中提供了相应的ppt教材，您可以尝试给自己讲述相关内容以强化理解；也可以录制自己讲述知识点过程中的视频，作为组队学习的打卡内容。

针对不同基础的同学，我们设置了不同梯度的内容：
1. 对于只想了解「计算机组成原理」、「计算机系统」与「计算机底层」的同学来说，只需学习CSAPP原课件与参考视频即可。
2. 对于学有余力的同学，可以根据我们的参考习题与lab进行分析和实现。
3. 对于想要「应用与进阶」的同学，我们提供了[高性能计算](https://github.com/realYurkOfGitHub/translation-Introduction-to-HPC)（[知乎专栏-高性能计算翻译计划](https://www.zhihu.com/column/c_1448674165109125120)），可根据配套内容进行探索和打卡。

**值得注意的是，「Datawhale 开源 408 计划」旨在为各位同学提供理解计算机相关内容的不同角度，对于需要考研的小伙伴们，本部分仅供参考，具体内容请以教材为主。**

## 任务安排

### Task 01：计算机系统漫游
- 操作系统的作用、虚拟内存和计算机体系内部的通信
- 高性能计算领域的定理、并发和并行的概念及模型

### Task 02：信息的表示和处理
- 计算机中信息存储和表示的模型以及运算
- 整数的表示与编码、计算机中对整数运算的实现
- 计算机中浮点数的表示及运算实现

### Task 03：程序的机器级表示
- 程序的汇编及机器码中字段、寄存器相关的内容、数据的传送
- 计算机中算术及逻辑操作的实现、指令的控制流、数组的分配和访问
- 结构体等异质数据结构及其实现、缓冲区溢出

### Task 04：处理器体系架构
- 指令集体系结构、指令编码
- 指令执行的各阶段

### Task 05：优化程序性能
- 数据局部性与循环展开

### Task 06：存储器层次结构
- 高速缓存的作用、高性能计算中隐式利用缓存编程


## 参考资料
1.汇编指令集

- 汇编语言网站：[http://c.biancheng.net/view/3468.html](http://c.biancheng.net/view/3468.html)（C语言中文网）
- linux汇编语言开发指南：[https://zhuanlan.zhihu.com/p/54853591](https://zhuanlan.zhihu.com/p/54853591)
- 汇编入门手册：[https://www.cnblogs.com/dgwblog/p/11784614.html](https://www.cnblogs.com/dgwblog/p/11784614.html)

2.makefile
- [https://zhuanlan.zhihu.com/p/350297509](https://zhuanlan.zhihu.com/p/350297509)

3.csapp lab
- [https://github.com/Exely/CSAPP-Labs](https://github.com/Exely/CSAPP-Labs)
- [http://csapp.cs.cmu.edu/3e/labs.html](http://csapp.cs.cmu.edu/3e/labs.html)
- [CSAPP.zip](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d66aa998-f8b4-430a-8067-4ff0159cdf63/CSAPP.zip)
- [https://hansimov.gitbook.io/csapp/labs/labs-overview](https://hansimov.gitbook.io/csapp/labs/labs-overview)

4.csapp 课后习题
- csapp 答案：https://github.com/DreamAndDead/CSAPP-3e-Solutions
- [https://github.com/mofaph/csapp/blob/master/exercise/00-topic.txt](https://github.com/mofaph/csapp/blob/master/exercise/00-topic.txt)

5.参考视频
- Bilibili CSAPP-深入理解计算机系统：https://www.bilibili.com/video/BV1cD4y1D7uR?p=1

## 贡献者名单

| 贡献者                                                       | 贡献内容                                         |
| :----------------------------------------------------------- | :----------------------------------------------- |
| 李岳昆   [知乎](https://www.zhihu.com/people/yurk-73),[Github](https://github.com/realYurkOfGitHub) | 提供全书第一部分1-3章内容pdf、高性能计算教材翻译 |
| 易远哲   [Github](https://github.com/Yi-Yuanzhe)             | 提供全书第一部分4-6章内容pdf                     |
| 叶前坤   [Github](https://github.com/PureBuckwheat)          | 参考习题与lab                                |
| chuxiaoyu [博客](http://www.chuxiaoyu.cn)                          | 开源贡献，「Datawhale 开源 408 计划」统筹工作        |

特别鸣谢：蒋志政 [Github](https://github.com/gezelligheid0314) 提供了高性能计算教材部分翻译内容

