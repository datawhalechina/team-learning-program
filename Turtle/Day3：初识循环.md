今天，正儿八经给大家普及下新知识。

⛽️知识加油站——循环

循环在我们生活中非常普遍，比如我们乘坐旋转木马，旋转木马会一直循环，又比如说月球会一直不停绕着太阳旋转：

![图片](https://uploader.shimo.im/f/v20NLdEY5vmO9o9s.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

而在编程中循环也是一个非常重要知识，它可以让程序一直重复执行，比如下面这个程序，虽然只输入了一句话，但是通过设置循环次数为5，就能打印5次“我喜欢看海贼王”：

```plain
for i in range(5):
    print('我喜欢看海贼王')
```
## 循环的格式

想使用循环非常简单，只需要按照下面的格式设置就可以了：

```plain
# 括号里填写你想循环的次数，写上冒号后，换行，空4个空格后就可以写循环的内容
for i in range(次数)：
    循环的内容
```
📺练习1：

使用循环画一个正方形。

![图片](https://uploader.shimo.im/f/3yj46wvFLN6I4S7T.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

一起来分析下，要画一个正方形，需要下面这些步骤：

```plain
向前100
旋转90度
向前100
旋转90度
向前100
旋转90度
向前100
旋转90度
```
可以看成，我们要让计算机重复执行4次下面的内容：

```plain
下面的内容要重复执行4次：
向前100
向右旋转90度
```
这样就就可以写成循环了：

```plain
for i in range(4):
    t.forward(100)
    t.right(90)
```
完整代码：

```plain
import turtle as t 
 
t.TurtleScreen._RUNNING = True 
t.shape(name='turtle') 
#连续画四条边
for i in range(4):
    t.forward(100)
    t.right(90)
t.done()
```
🚀挑战1：

题目描述：通过循环画出一个边长为100的等边三角形

备注：等边三角形就是三条边的长度相等、三个角角度都为60度的三角形

![图片](https://uploader.shimo.im/f/vvvLj67CO4lNmlDr.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

🚀挑战2：

题目描述：通过循环画出一个正六边形

备注：正六边形每个角的度数(大小)都一样，6个角总共720度

提示：需要先计算正六边形的每个角大小，再得出图图每次需要转多少度，这是小学三年级除法的难度，你敢说不会？ㄟ(▔︵▔ㄟ)

![图片](https://uploader.shimo.im/f/O5R5DJFoGg2DP58d.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

🚀挑战3:

能完成上面两道题，说明你还是很厉害的，那么咱们就提高一点难度吧，这道题你还能做对，那可就非常厉害了。

题目描述：使用循环，画出一个五角星：(可以像上面那样，先写出文字步骤来分析一下哦～)

👽拓展：如果画好了，还可以给五角星加上颜色

![图片](https://uploader.shimo.im/f/xpbOnEmE6z9VJeHo.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

🚀挑战4:

光阴似箭，日月如梭，一转眼，你，竟然，做到了最后一道题，你，这么，厉害，爸妈知道吗？如果不知道就赶紧告诉他们吧。

下面是第三天的终极挑战：画一个圆

![图片](https://uploader.shimo.im/f/EIGelDQKcdrDrkos.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

提示：画圆其实很简单，虽然你看着是一个圆，但其实，他可以看成是很多短短的边组成的，那么怎么来画圆呢？你可以看一看下面这个视频：

[https://baike.baidu.com/item/%E5%89%B2%E5%9C%86%E6%9C%AF/595781?secondId=25702197](https://baike.baidu.com/item/%E5%89%B2%E5%9C%86%E6%9C%AF/595781?secondId=25702197)

![图片](https://uploader.shimo.im/f/wbE6BsDlqZagEc21.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

看完视频是不是发现啦，古代人通过在圆内画多边形来近似测量圆一周的长度，多边形边越多，形状上就越接近圆，那么基于这个方法，我们可以用图图来帮我们画一个圆，怎么来画呢，我们知道圆有360°，也就是说，如果要画圆，【图图】需要围绕中心走360次，每一次走一步，每走一步向右转1度，画出一个有360边的多边形就可以了。

下面就是画圆的关键，如果你不能理解，那就记住这个有趣的画圆公式吧：

```plain
import turtle as t 
 
t.TurtleScreen._RUNNING = True 
t.shape(name='turtle') 
#请删除这一行，然后写上循环360次的程序吧
    t.forward(1)     #向前走一步
    t.right(1)       #向右转一度
    
t.done()
```
如果你已经画好了圆，再试一试修改forward里面的数字，看看会发生什么变化～

# 

