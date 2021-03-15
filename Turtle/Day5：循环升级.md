**老师**：通过两天的训练，相信大家对于循环已经掌握的非常好了，今天我们要学习的是【循环中的循环】。

**A**：【循环中的循环】？老师，你是在传授武林秘籍吗？

**老师**：你是猴子派来的吗？总想着武林秘籍

**A**：那什么是【循环中的循环】啊？

**老师**：听我细细道来

# 循环中的循环

循环中的循环是编程中常用的一种程序的结构，它也被称为【循环嵌套】，也就是在一个大循环里套着小循环，听起来有点复杂，但其实非常的简单，一起来学习一下吧！

在学习【循环嵌套】前我们先来练习一道题目，这样有助于我们理解【循环嵌套】的含义。

# 🚀挑战1

题目描述：参考下面图示，借助循环，画出四个并排在一起的正方形。

（1）正方形的边长是40

（2）两个正方形之间的距离是20

![](https://uploader.shimo.im/f/9JvrXBlJSw8OuycC.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

⬇️⬇️⬇️ 做完后再看，实在做不出来也可以看，不到万不得已还是别看 ⬇️⬇️⬇️

**解析**：大家说如果要画四个正方形，需要重复画几个一样的正方形？

4个对吧，那如果用图图来画一个正方形需要重复几次【向前走100向右转90度】呢？

也是4次，所以我们来看看，如果要通过图图来画出它需要怎么做：

```python
#画第一个正方形
for i in range(4):      
    t.forward(40)
    t.right(90)
# 画完后要向前移动到到第二个正方形的顶点，
# 需要向前移动60步，移动的时候是抬笔的状态
t.up()
t.forward(60)
t.down()
```
![](https://uploader.shimo.im/f/i9jE9VYQDwoMHGI9.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

如果还要画三次应该怎么办呢？哈哈，对的，继续用同样的语句三遍就行了。

```python
#画第一个正方形
for i in range(4):      
    t.forward(40)
    t.right(90)
t.up()
t.forward(60)
t.down()

#画第二个正方形
for i in range(4):      
    t.forward(40)
    t.right(90)
t.up()
t.forward(60)
t.down()

#画第三个正方形
for i in range(4):      
    t.forward(40)
    t.right(90)
t.up()
t.forward(60)
t.down()

#画第四个正方形
for i in range(4):      
    t.forward(40)
    t.right(90)
t.up()
t.forward(60)
t.down()
```
这个时候你发现了什么，是不是下面段落连续出现了4次，我们学习循环时说过，当重复不断出现时可以使用循环，这里仍然可以的，将这个循环的逻辑写成中文就是：

```python
下面的内容要循环4次：
    for i in range(4):      
        t.forward(40)
        t.right(90)
    t.up()
    t.forward(60)
    t.down()
```
把中文也变成程序，和之前循环是一样的方式，加上`for i in range()`，但是一定要记得，后面这个整体前面有四个空格，还记得之前讲循环的时候说过吧，加四个空格是为了告诉程序，下面的内容归我管，要循环4次，可不要忘了哦：

![](https://uploader.shimo.im/f/hXQivhdLgQK3j7mD.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

是不是觉得好像听懂了，又好像有一点疑惑，怎么办呢？很简单，再来做两道题自然就懂了。

# 🚀挑战2

题目描述：参考下面的图示，借助循环，画出四个并排在一起的三角形。

提示：<br>
➡️可以不用循环嵌套，但是呢，使用循环嵌套又会更简单，算了，用不用看你吧！

➡️如果使用嵌套，你可以参考上面画4个正方形的方法哦，先用中文分析循环次数，再写成程序。

（1）等边三角形的边长是40

（2）两个三角形之间的距离是20

![](https://uploader.shimo.im/f/8T5mc6z8VKs2L9Fg.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

# 🚀挑战3

不得了不得了，已经做到第3题了，少年我看你天赋异禀啊！

题目描述：参考下面的图示，借助循环，画出一个风车

![](https://uploader.shimo.im/f/E0pfiSvFjcIEL3hr.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

👽拓展：在风车的基础上进行创作吧，看看你的风车能比下面的好看吗？

![](https://uploader.shimo.im/f/9inOstKpjljcCLmW.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

![](https://uploader.shimo.im/f/VeaMiDeb7kYMnVjq.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

# 🚀挑战4（选做）

这道题选做哦，如果你觉得前面的已经难不倒你了，那就再来试试这一道题吧！

题目描述：参考下面的图示，借助循环，画出一个飞镖

（1）菱形的两个角分别是60°和120°

![](https://uploader.shimo.im/f/BumBYN0F8YnkfShm.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

