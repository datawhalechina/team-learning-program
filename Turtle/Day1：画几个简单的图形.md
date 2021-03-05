通过前面的学习，相信你已经能够参考【图图】的说明来进行绘图创造了，那么从现在开始，我们将正式开始各种趣味图形绘画。

![图片](https://uploader.shimo.im/f/z5w4Mp2Q2dvxv2kL.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

🔑 在【冲鸭】之前，请记住一个【锦囊妙计】，后面的画图过程中会涉及到各种各样的功能，有时我们可能会忘记指令怎么写，所以在每次训练的开头会为大家附上一个【图图】功能查询表，忘了就去看看吧。

|向前走<br>|t.forward(100)<br>|
|:----|:----|:----|:----|
|向后走<br>|t.back(100)<br>|
|向左转<br>|t.left(90)<br>|
|向右转<br>|t.right(90)<br>|


万事俱备，只欠冲锋，好啦，正式开始我们图形挑战之旅吧！！！

# 🚀挑战1：

这是一道送分题，别说你不会，赶紧的～

题目描述：请参考下面图片中的内容，从左向右画出一条长度为100的直线吧。

![图片](https://uploader.shimo.im/f/MmUDB3ej2w5xtdWB.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

# 🚀挑战2：

送分题又来了！

题目描述：请参考下面图片中的内容，从左向右画出一个度数为90的角吧。

（边的长度自己决定就好啦，可以也设置成100）

![图片](https://uploader.shimo.im/f/9rXYtOPvhuGaBXNV.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

# 📺示例1:

下面，我们将一起来画几个简单的图形，第一个图形是一个三条边长度都为100的三角形，想一想该怎么画出来，如果想不出来，可以试试在草稿纸上画一画，要注意每次【图图】画完一条线后旋转的角度哦。

![图片](https://uploader.shimo.im/f/y88d2B5n0SbNBcBU.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

下面是三角形的答案，一定要自己尝试后再来看哦：

**讲解**：三角形一共有三条边，【图图】向前100后，完成第一条边的绘制，然后需要向右旋转120度，接着又向前100，完成第二条边后再向右旋转120度，向前100，完成最后一条边的绘制

```plain
import turtle as t 
 
t.TurtleScreen._RUNNING = True 
t.shape(name='turtle') 
#连续画三条边
t.forward(100) 
t.right(120)  
t.forward(100) 
t.right(120) 
t.forward(100) 
t.right(120) 
t.done()
```
# 🚀挑战3:

通过学习后你已经能画出三角形了，那么，再用同样的方式来试试正方形吧

![图片](https://uploader.shimo.im/f/8kVczix7Ad8vYuWM.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

# 🚀挑战4:

看来你已经能绘制简单图形了，那么现在来尝试一下复杂一点的“十字”图吧，方法和前面的图形差不多哦，但是注意每转动一次后移动的距离：

![图片](https://uploader.shimo.im/f/JcOy5RnSxjJbrFic.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

真棒，这么快就完成了第一天的打卡内容了，是不是觉得还挺简单，哈哈，别着急，后面会逐渐提高难度，小心哦～

![图片](https://uploader.shimo.im/f/ou0HQfieY21z7jXg.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)




