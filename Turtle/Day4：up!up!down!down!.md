A：老师，你是在欺负我英语不好吗？整个英文标题

老师：对啊，你怎么知道我在欺负你

A：... ...

# 1、抬笔与落笔

## （1）简介

今天我们一起来学习一个新技能【抬笔】与【落笔】。

前面我们画了这么多图形，但是大家有没有发现，我们从来没有抬过笔，这是一件非常不科学的事，就好比，你笔尖从没离开过画纸，但却写下了下面这四个字(不能写连笔)：

![图片](https://uploader.shimo.im/f/DNOyGRVpuS8hqtz1.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

所以，在常规的绘画和写字中，我们其实是需要【抬笔】和【落笔】这两个动作的，同样的，图图也有这两个指令：

## （2）格式

```plain
t.up()     # 抬笔
t.down()   # 落笔
```
把这两句放到完整的程序中，先前进100步，然后抬笔，前进50步，然后落笔，然后再前进100步，

想一想可能画出的图形是什么样子，然后再运行一下这段代码看一看吧：

```plain
import turtle as t
t.TurtleScreen._RUNNING = True
t.shape(name='turtle')
t.forward(100)
t.up()             #抬笔
t.forward(50)
t.down()           #落笔
t.forward(100)
t.done()
```
# 🚀挑战1：

题目描述：画出一条虚线

（1）实线每一段长为10

（2）间断距离为5

提示：使用循环会更方便哦

![图片](https://uploader.shimo.im/f/JhWBeyJdBiGQwufS.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

# 🚀挑战2：

题目描述：绘制8条平行线

（1）每条线的长度为100

（2）相邻两条线同一端之间的距离为20

（备注：平行线倾斜程度自己设定就好，可以用文字写出走的步骤，看看循环的部分在哪里）

![图片](https://uploader.shimo.im/f/pazI1NHqBi2XkqtY.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

# 🚀挑战3：

不知不觉，又来到了最后一题，哦不对，是倒数第二题，这一题和上面一题难度差不多，耐心一点就能画出来。

题目描述：请画出四个平行的小旗子

（1）旗子高40

（2）旗面宽

![图片](https://uploader.shimo.im/f/pmNf7tLQE3GyNH7U.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

# 🚀挑战4：

不知不觉，终于来到了最后一题，你还记得之前画过的的圆吗？这次有了抬笔功能的配合，我们就可以画出一个很常见的汽车标志啦：

（1）两个圆中心之间的距离是85

（2）想一想每画完一个圆后【图图】要向前移动多少呢?

![图片](https://uploader.shimo.im/f/wBVgm9UwCbUncGru.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

画一个圆的关键步骤参考：

```plain
for i in range(360):
    t.forward(1)
    t.right(1)
```


