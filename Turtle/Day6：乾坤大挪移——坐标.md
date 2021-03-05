# 1、坐标

地瓜：土豆土豆，我是地瓜，我现在已经被敌人包围了，请求支援

土豆：地瓜地瓜，我是土豆，请立刻告诉我你的坐标

![图片](https://uploader.shimo.im/f/DhnYpoXEg77ElkYm.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

什么是坐标呢？读完上面这个对话你肯定有点感觉了吧，坐标就是一个物体在空间中的位置，就比如上面飞机在天空中的位置，你需要告诉友军他的位置，友军才能去营救。

又比如说下面这个图，就是一个平面空间，我们在上面画了两根轴来帮助大家记录坐标，这两根轴就叫做坐标轴，两根轴交叉的就是坐标为(0，0)的地方，那么怎么来读取坐标呢？其实非常简单，看【图图】对应坐标轴上的数字就可以了。

![图片](https://uploader.shimo.im/f/LfrVWDN6OYr0pq3K.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

就比如这样，图图的位置分别对应横轴的1，对应纵轴的3，所以他的坐标是(1,3)：

![图片](https://uploader.shimo.im/f/nzm1q3NzzoM7nfT6.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

## 🚀挑战1：

题目描述：请你写出图图在在下图中的位置：

（对应横轴和纵轴来看哦~）

![图片](https://uploader.shimo.im/f/6fneCP2r8lbCmYYO.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

# 2、goto功能

那么，怎么才能帮图图到指定的坐标呢？这就要使用到一个有趣的功能goto，它可以帮助我们去到指定的坐标位置，但是在使用它之前需要记住两个重要秘诀：

### 秘诀1:如果使用goto功能时没有抬笔，那么图图会向着指定的位置画过去

这个秘诀说的什么意思呢？我们一起来看看：

（1）goto语句的用法

```plain
t.goto(x, y)   # x,y就是对应的坐标
```
如果我使用下面这一段语句，你猜猜会发生什么：

```plain
t.goto(80,80)
```
出现了下面的图像，原因是因为我们让图图去坐标是(80,80)的位置，所以图图就直接移动过去了，不过使用goto语句，图图的朝向是不会改变的哦：

![图片](https://uploader.shimo.im/f/axsrooNYe9fh63Tm.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

那么，如果我想让图图**瞬间移动**过去要怎么办呢，这时候秘诀2了

### 秘诀2:要让图图瞬间移动到某个坐标，而不留下痕迹，就要使用抬笔和落笔功能

```plain
t.up()
t.goto(x,y)    # 比如t.goto(80,80)
t.down
```
## 🚀挑战2:

请你将图图**瞬间移动**到坐标为(100,100)的地方，如下图：

(提示：下图中的红色圆圈只是示意图图的一开始的位置，忽略就可以)

![图片](https://uploader.shimo.im/f/wlX5I63zyyaerDvy.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

# 3、乾坤大挪移

有小伙伴儿会问：“乾坤大挪移是什么？”，很简单，就是让图图进行各种移动，各种来考你的题目，hiahiahiahia~

## 🚀挑战3:

还记得小时候用过的钉板吗？我们通过钉板制作各种各样的图形，比如下面这种。

那么现在请使用goto语句，参考下面的坐标画出对应的图形：

（备注：如果画出来的图形很小，可以把坐标都放大10倍哦，比如把(1,1)改成(10,10)，把(5,1)改成(50,10)）

![图片](https://uploader.shimo.im/f/ycPBUneY6Y07aVXG.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

## 🚀挑战4:

看来你已经简单了解了坐标和使用坐标的方式，那么，再来考考你吧。

题目描述：请先将图图移动到坐标为(100,100)的位置，然后画出一个边长为50的五角星

（备注：五角星的每个角都是36°，其他角的参数参考下图2）

![图片](https://uploader.shimo.im/f/seYOykeuVza9t1nj.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)

![图片](https://uploader.shimo.im/f/rOfsXoJcBrUmCx93.png!thumbnail?fileGuid=886kd3qYgXXTyTTW)



