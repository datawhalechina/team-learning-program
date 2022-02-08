# 2、基础
基础部分，我会选择Python的主要部分进行讲解，以及提供深入的练习，以及还有很多指令，大家可以自己选择感兴趣或者要用到的进行补充，这部分学习的思想主要是让大家学会自学编程语言

## （1）实现第一行代码和认识注释
写出你的第一行代码向世界问好

`print('hallo world')`

认识注释，注释是由# 加相关备注，但是不会在代码中运行，可以作为帮助理解的功能

```python
1   # A comment, this is so you can read your program later.
2   # Anything after the # is ignored by python. 
3
4   print("I could have code like this.") # and the comment after 5
6   # You can also use a comment to "disable" or comment out code
7   # print("This won't run.") 
8
9   print("This will run.")
```



## （2）数学运算

### 认识运算符

加减乘除等以及特殊符号

• `+` plus，加号
• `-` minus，减号
• `/` slash，斜杠
• `*` asterisk，星号
• `%` percent，百分号
• `<` less-than，小于号
• `>` greater-than，大于号
• `<=` less-than-equal，小于等于号
• `>=` greater-than-equal，大于等于号



```python
1   print("I will now count my chickens:")
2
3   print("Hens", 25 + 30 / 6)
4   print("Roosters", 100 - 25 * 3 % 4)
5
6   print("Now I will count the eggs:")
7
8   print(3 + 2 + 1 - 5 + 4 % 2 - 1 / 4 + 6)
9
10  print("Is it true that 3 + 2 < 5 - 7?")
11
12  print(3 + 2 < 5 - 7)
13
14  print("What is 3 + 2?", 3 + 2)
15  print("What is 5 - 7?", 5 - 7)
16
17  print("Oh, that's why it's False.")
18
19  print("How about some more.")
20
21  print("Is it greater?", 5 > -2)
22  print("Is it greater or equal?", 5 >= -2)
23  print("Is it less or equal?", 5 <= -2)
```

你应该会看到的结果是

```python
I will now count my chickens: Hens 30.0
Roosters 97
Now I will count the eggs: 6.75
Is it true that 3 + 2 < 5 - 7? False
What is 3 + 2? 5
What is 5 - 7? -2
Oh, that's why it's False. How about some more.
Is it greater? True
Is it greater or equal? True

Is it less or equal? False
```

自我练习

1. 在每一行上面，用 `#` 写一句注释，向自己解释这行代码的作用。
3. 找一些你需要计算的东西，然后写一个新的 `.py` 文件。
4. 用浮点数重新写一下`，让它更精确一些，比如 20.0 就是一个浮点数。

## （3）字符串和文本

字符如何引用

```python
1   cars = 100
2   space_in_a_car = 4.0
3   drivers = 30
4   passengers = 90
5   cars_not_driven = cars - drivers
6   cars_driven = drivers
7   carpool_capacity = cars_driven * space_in_a_car
8   average_passengers_per_car = passengers / cars_driven     
9
10
11  print("There are", cars, "cars available.")
12  print("There are only", drivers, "drivers available.")
13  print("There will be", cars_not_driven, "empty cars today.")
14  print("We can transport", carpool_capacity, "people today.")
15  print("We have", passengers, "to carpool today.")
16  print("We need to put about", average_passengers_per_car,
17        "in each car.")
```

自己的信息引用

```python
1   my_name = 'Zed A. Shaw'
2   my_age = 35 # not a lie
3   my_height = 74 # inches
4   my_weight = 180 # lbs
5   my_eyes = 'Blue'
6   my_teeth = 'White'
7   my_hair = 'Brown'
8
9   print(f"Let's talk about {my_name}.")
10  print(f"He's {my_height} inches tall.")
11  print(f"He's {my_weight} pounds heavy.")
12  print("Actually that's not too heavy.")
13  print(f"He's got {my_eyes} eyes and {my_hair} hair.")
14  print(f"His teeth are usually {my_teeth} depending on the coffee.")
15
16  # this line is tricky, try to get it exactly right
17  total = my_age + my_height + my_weight
18  print(f"If I add {my_age}, {my_height}, and {my_weight} I get {total}.")
```

#### 附加练习
尝试改成自己的信息

### 输入一整段字符串、变量和格式
程序员都喜欢使用简短的缩写来节省时间，但是那些缩写在你看来会十分晦涩难懂。所以我们得尽早开始学习阅读和书写这些东西。
```python
1   types_of_people = 10
2   x = f"There are {types_of_people} types of people."
3
4   binary = "binary"
5   do_not = "don't"
6   y = f"Those who know {binary} and those who {do_not}."
7
8   print(x)
9   print(y)
10
11  print(f"I said: {x}")
12  print(f"I also said: '{y}'")
13
14  hilarious = False
15  joke_evaluation = "Isn't that joke so funny?! {}"
16
17  print(joke_evaluation.format(hilarious))
18
19  w = "This is the left side of..."
20  e = "a string with a right side."
21
22  print(w + e)
```
#### 运行结果

```python
There are 10 types of people.
Those who know binary and those who don't.
I said: There are 10 types of people.
I also said: 'Those who know binary and those who don't.'
Isn't that joke so funny?! False
This is the left side of...a string with a right side.
```
#### 附加练习
1、复习一遍这个程序，并在每一行上面写上注释来解释它。
2、找到所有把字符串放在字符串里面的地方，一共有 4 处。
3、你确定有 4 处吗？你怎么知道？也许我爱撒谎呢。
4、解释一下为什么把 w 和 e 两个字符串用 + 连起来能够弄成一个更长的字符串。
### 把代码打乱
你现在已经可以把代码打乱了。把它当成一个游戏，用一种最聪明或者最简单的方式把代码打乱。打乱之后，你需要修复它们。如果你跟你的朋友一起学习，你们可以相互打乱对方的代码，然后再试着修复它。把你的代码发给你的队友，让他们打乱，然后你再试着找出它们的错误，并修复它。记住，如果你已经写了一遍这些代码了，你可以再写一次。如果你打乱得太彻底了，就试着重新写一遍。

#### 常见问题
为什么你在一些字符串外面放的是单引号，而其他的不是？大多数是因为格式。但是如果一个字符串已经用了双引号，我就会在这个字符串里面用单引号，看看第 6 行和第 15 行你就知道了。

如果你觉得一个笑话很好笑，可以写 hilarious = True 吗？ 可以的，你会在后面学习到这些布尔值。



## （4）列表
List（列表） 是 Python 中使用最频繁的数据类型。

列表可以完成大多数集合类的数据结构实现。它支持字符，数字，字符串甚至可以包含列表（即嵌套）。

列表用 [ ] 标识，是 python 最通用的复合数据类型。

列表中值的切割也可以用到变量 [头下标:尾下标] ，就可以截取相应的列表，从左到右索引默认 0 开始，从右到左索引默认 -1 开始，下标可以为空表示取到头或尾。
```python
list = [ 'runoob', 786 , 2.23, 'john', 70.2 ]
tinylist = [123, 'john']
 
print(list)                # 输出完整列表
print(list[0])            # 输出列表的第一个元素
print(list[1:3])           # 输出第二个至第三个元素 
print(list[2:])            # 输出从第三个开始至列表末尾的所有元素
print(tinylist * 2)        # 输出列表两次
print(list + tinylist)     # 打印组合的列表
```
输出结果

```python
['runoob', 786, 2.23, 'john', 70.2]
runoob
[786, 2.23]
[2.23, 'john', 70.2]
[123, 'john', 123, 'john']
['runoob', 786, 2.23, 'john', 70.2, 123, 'john']
```

#### 附加练习
搜索列表的相关知识，看还有什么操作






