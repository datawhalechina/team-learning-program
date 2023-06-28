# 4 FOR、IF以及while

## 4.1 IF语句

### 4.1.1 IF语句的使用

```{code-block} python
:linenos:

people = 20
cats = 30
dogs = 15


if people < cats:
    print("Too many cats! The world is doomed!")

if people > cats:
    print("Not many cats! The world is saved!")

if people < dogs:
    print("The world is drooled on!")     

if people > dogs:
    print("The world is dry!")


dogs += 5

if people >= dogs:
    print("People are greater than or equal to dogs.")

if people <= dogs:
    print("People are less than or equal to dogs.")


if people == dogs:
    print("People are dogs.")
```

&emsp;&emsp;上述代码的运行结果：

```shell
Too many cats! The world is doomed!     
The world is dry!
People are greater than or equal to dogs.
People are less than or equal to dogs.
People are dogs.
```

```{admonition} 附加练习
:class: tip

在练习中，试着猜猜`if`语句是什么以及它是干什么的。在继续进行下个练习之前，试着用自己的话回答以下这些问题：

1. 你认为`if`对它下面的代码起什么作用？
2. 为什么`if`下面的代码要缩进 4 个空格？
3. 如果没有缩进会发生什么？
4. 你能把一些布尔表达式放进`if`语句吗？试试看。
5. 如果你改变`people`、`cats`和`dogs`的初始值会发生什么？
```

#### 常见问题

`+=`是什么意思？ 

**答：** `x += 1`就相当于`x = x + 1`，但是输入的内容更少。你可以把它叫做“累加”（increment by）运算符。之后你还会学到`-=`这样类似的表达。

### 4.1.2. Else 和 IF 使用

&emsp;&emsp;在上个练习中你学到了一些`if`语句，思考了它的含义和作用。在你学习更多内容之前，我会解释一下上个附加练习中的问题。首先确定你做了那些练习。

1. 你认为`if`对它下面的代码起什么作用？
   
&emsp;&emsp;`if`语句在代码中创建了一个“分支”（branch），有点类似于在一本冒险书中，你选择了哪个答案，就翻到对应的一页，如果你选择了不同的答案，就会去到不同的地方。`if`语句就是告诉脚本，如果这个布尔表达式是`True`，那就运行它下面的代码，否则的话就跳过。

2. 为什么`if`下面的代码要缩进 4 个空格？

&emsp;&emsp;通过一行代码结尾的冒号告诉 Python 你在创建一个新的代码块，然后缩进四个空格告诉 Python 这个代码块中都有些什么。这就跟本书前半部分中你学的函数是一样的。

3. 如果没有缩进会发生什么？

&emsp;&emsp;如果没有缩进，你很可能收到一个错误提示。Python 一般会让你在一个带`:`的代码行下面缩进一些内容。

4. 你能把一些布尔表达式放进`if`语句吗？试试看。

&emsp;&emsp;试试吧，你可以的。你可以把它们写得很复杂，不过复杂的东西一般风格都很糟糕。

5. 如果你改变`people`、`cats`和`dogs`的初始值会发生什么？

&emsp;&emsp;因为在比较数字，所以如果改变了数字，不同的`if`语句将会得出不同的判断结果，那么下面某些代码块就有可能运行。回到练习中给这些变量一些不同的数值，然后看看你能否在脑中判断出来哪些代码块会运行。

&emsp;&emsp;把我的答案和你的比较一下，然后确保你真的理解了代码块的概念。这对你进行接下来的练习很重要。把下面的代码输入进去然后运行。

```{code-block} python
:linenos:

people = 30
cars = 40
trucks = 15


if cars > people:
    print("We should take the cars.")
elif cars < people:
    print("We should not take the cars.")
else:
    print("We can't decide.")

if trucks > cars:
    print("That's too many trucks.")
elif trucks < cars:
    print("Maybe we could take the trucks.")
else:
    print("We still can't decide.")

if people > trucks:
    print("Alright, let's just take the trucks.")
else:
    print("Fine, let's stay home then.")
```

&emsp;&emsp;上述代码的运行结果：

```shell
We should take the cars.
Maybe we could take the trucks.
Alright, let's just take the trucks.
```

```{admonition} 附加练习
:class: tip

1. 试着猜猜`elif`和`else`的作用是什么。
2. 改变`cars`、`people`和`trucks`的数值，然后追溯每一个`if`语句，看看什么会被打印出来。
3. 试试一些更复杂的布尔表达式，比如`cars > people`或者`trucks < cars`。
4. 在每一行上面加上注释。
```

#### 常见问题

如果多个`elif`块都是`True`会发生什么？ 

**答：** Python 从顶部开始，然后运行第一个是`True`的代码块，也就是说，它只会运行第一个。

### 4.1.3 IF嵌套使用

&emsp;&emsp;前面主要学习了调用函数、打印东西，但是这些基本都是直线运行下来的。你的脚本从上面开始运行，然后到底部结束。如果你用了一个函数，你可以随后再运行它，但是仍然不会有分叉需要你做决定的情况。现在你学习了`if`、`else`以及`elif`，你就可以让脚本来做决定了。

&emsp;&emsp;在上个脚本中，你写出了一个简单的问问题的测试集。在这个练习中，你将问用户一些问题，并基于他们的回答做决定。写下这个脚本，然后多玩几遍，把它弄明白。

```{code-block} python
:linenos:

print("""You enter a dark room with two doors.
    Do you go through door #1 or door #2?""")

door = input("> ")

if door == "1":
    print("There's a giant bear here eating a cheese cake.")
    print("What do you do?")
    print("1. Take the cake.")
    print("2. Scream at the bear.")     

    bear = input("> ")

    if bear == "1":
        print("The bear eats your face off. Good job!")
    elif bear == "2":
        print("The bear eats your legs off. Good job!")
    else:
        print(f"Well, doing {bear} is probably better.")
        print("Bear runs away.")

elif door == "2":
    print("You stare into the endless abyss at Cthulhu's retina.")
    print("1. Blueberries.")
    print("2. Yellow jacket clothespins.")
    print("3. Understanding revolvers yelling melodies.")

    insanity = input("> ")

    if insanity == "1" or insanity == "2":
        print("Your body survives powered by a mind of jello.")
        print("Good job!")
    else:
        print("The insanity rots your eyes into a pool of muck.")
        print("Good job!")

else:
    print("You stumble around and fall on a knife and die. Good job!")
```

&emsp;&emsp;这里很关键的一点是在`if`语句里面又放了一个`if`语句。这在创建“嵌套”（nested）的时候非常有用，每一个分支指向另一个选择。

&emsp;&emsp;确保你理解了在`if`语句中嵌套`if`语句的理念。可以通过做附加练习来真正掌握它。

#### 你会看到

&emsp;&emsp;这是我玩这个冒险小游戏的结果，我可能玩得没那么好。

```shell
You enter a dark room with two doors.     
Do you go through door #1 or door #2?
>   1
There's a giant bear here eating a cheese cake.
What do you do?
1.  Take the cake.
2.  Scream at the bear.
>   2
The bear eats your legs off. Good job!
```

```{admonition} 附加练习
:class: tip

给这个游戏加一些新内容，同时改变用户可以做的决定。尽可能地扩展这个游戏，直到它变得很搞笑。写一个完全不同的新游戏。可能你不喜欢我的这个，你可以做一个你自己的。
```

#### 常见问题

1. 我能用一系列的`if`语句来代替`elif`吗？ 

&emsp;&emsp;**答：** 在某些情况下可以，但是取决于每个`if/else`是怎么写的。如果这样的话还意味着 Python 将会检查每一个`if-else`组合，而不是像`if-elif-else`组合那样只会检查第一个是`false`的。你可以多试几次，感受一下区别。

2. 我如何表示一个数字的区间？ 

&emsp;&emsp;**答：** 有两种方式：一种是`0 < x < 10`或者`1 <= x < 10`这种传统表示方法，另一种是`x`的区间是`(1, 10)`。

3. 如果我想在`if-elif-else`代码块中放更多的选择怎么办？ 

&emsp;&emsp;**答：** 为每种可能的选择增加更多的`elif`块。

## 4.2 FOR语句

```{code-block} python
:linenos:

the_count = [1, 2, 3, 4, 5]
fruits = ['apples', 'oranges', 'pears', 'apricots']
change = [1, 'pennies', 2, 'dimes', 3, 'quarters']

# this first kind of for-loop goes through a list
for number in the_count:
    print(f"This is count {number}")

# same as above
for fruit in fruits:
    print(f"A fruit of type: {fruit}")

# also we can go through mixed lists too
# notice we have to use {} since we don't know what's in it
for i in change:
    print(f"I got {i}")

# we can also build lists, first start with an empty one
elements = []

# then use the range function to do 0 to 5 counts
for i in range(0, 6):
    print(f"Adding {i} to the list.")
    # append is a function that lists understand
    elements.append(i)

# now we can print them out too
for i in elements:
    print(f"Element was: {i}")
```

&emsp;&emsp;上述代码的运行结果：

```shell
This is count 1
This is count 2
This is count 3
This is count 4
This is count 5
A fruit of type: apples
A fruit of type: oranges
A fruit of type: pears
A fruit of type: apricots
I got 1
I got pennies
I got 2
I got dimes
I got 3
I got quarters
Adding 0 to the list.
Adding 1 to the list.      
Adding 2 to the list.
Adding 3 to the list.    
Adding 4 to the list.
Adding 5 to the list.     
Element was: 0
Element was: 1
Element was: 2
Element was: 3
Element was: 4
Element was: 5
```

```{admonition} 附加练习
:class: tip

1. 看看你是如何使用`range`的。查阅上面的`range`函数并理解掌握。
2. 你能在第 22 行不使用`for-loop`，而是直接把`range(0, 6)`赋给`elements`吗？
3. 找到 Python 文档关于列表的部分，然后读一读。看看除了`append`，你还能对列表做哪些操作？
```

#### 常见问题

1. 如何创建一个二维列表？

&emsp;&emsp;**答：** 可以用这种列表中的列表：`[[1,2,3],[4,5,6]]`

2. 列表（lists）和数组（arrays）难道不是一个东西吗？ 

&emsp;&emsp;**答：** 这取决于语言以及实现方法。在传统术语中，列表和数组的实现方式不同。在 Ruby 中都叫做`arrays`，在 python 中都叫做`lists`。所以我们就把这些叫做列表吧。

3. 为什么`for-loop`可以用一个没有被定义的变量？ 

&emsp;&emsp;**答：** 变量在`for-loop`开始的时候就被定义了，它被初始化到了每一次`loop`迭代时的当前元素中。

4. 为什么`range(1, 3)`中的`i`只循环了两次而不是三次？  

&emsp;&emsp;**答：** `range()`函数只处理从第一个到最后一个数，但不包括最后一个数，所以它在 2 就结束了。这是这类循环的通用做法。

5. `element.append()`的作用是什么？ 

&emsp;&emsp;**答：** 它只是把东西追加到列表的末尾。打开 Python shell 然后创建一个新列表。任何时候当你遇到类似的用法，试着多玩几次，去体会它们的作用。

## 4.3 while语句

&emsp;&emsp;现在我们来看一个新的循环：`while-loop`。只要一个布尔表达式是`True`，`while-loop`就会一直执行它下面的代码块。

&emsp;&emsp;等等，你应该能理解这些术语吧？如果我们写一行以`:`结尾的代码，它就会告诉 Python 开始一个新的代码块。我们用这种方式来结构化你的程序，以便 Python 明白你的意图。如果你还没有掌握这块内容，先回去复习一下，再做一些`if`语句、函数以及`for-loop`，直到你掌握为止。

&emsp;&emsp;之后我们会做一些练习来训练你的大脑读取这些结构，就像我们训练你掌握布尔表达式一样。

&emsp;&emsp;回到`while-loop`，它所做的只是像`if`语句一样的测试，但是它不是只运行一次代码块，而是在`while`是`True`的地方回到顶部再重复，直到表达式为`False`。

&emsp;&emsp;但是`while-loop`有个问题：有时候它们停不下来。如果你的目的是让程序一直运行直到宇宙的终结，那这样的确很棒。但大多数情况下，你肯定是需要循环最终能停下来的。

> **为了避免这些问题，你得遵守一些规则：**
> 1. 保守使用`while-loop`，通常用`for-loop`更好一些。
> 2. 检查一下你的`while`语句，确保布尔测试最终会在某个点结果为`False`。
> 3. 当遇到问题的时候，把你的`while-loop`开头和结尾的测试变量打印出来，看看它们在做什么。

&emsp;&emsp;在这个练习中，你要通过以下三个检查来学习`while-loop`：

```{code-block} python
:linenos:

i = 0
numbers = []

while i < 6:
    print(f"At the top i is {i}")
    numbers.append(i)

    i = i + 1
    print("Numbers now: ", numbers)
    print(f"At the bottom i is {i}")


print("The numbers: ")

for num in numbers:
    print(num)
```

&emsp;&emsp;上述代码的运行结果：

```shell
At the top i is 0      
Numbers now: [0]
At the bottom i is 1
At the top i is 1    
Numbers now:    [0, 1]
At the bottom i is 2
At the top i is 2
Numbers now:    [0, 1, 2]
At the bottom i is 3
At the top i is 3
Numbers now:    [0, 1, 2, 3]
At the bottom i is 4
At the top i is 4
Numbers now:    [0, 1, 2, 3, 4]
At the bottom i is 5
At the top i is 5
Numbers now:    [0, 1, 2, 3, 4, 5]
At the bottom i is 6
The numbers:
0
1
2
3
4
5
```

```{admonition} 附加练习
:class: tip

1. 把这个`while-loop`转换成一个你可以调用的函数，然后用一个变量替代`i < 6`里面的`6`。
2. 用这个函数重新写一下这个脚本，试试不同的数值。
3. 再增加一个变量给这个函数的参数，然后改变第 8 行的`+1`，让它增加的值与之前不同。
4. 用这个函数重新写这个脚本，看看会产生什么样的效果。
5. 用`for-loop`和`range`写这个脚本。你还需要中间的增加值吗？如果不去掉这个增加值会发生什么？

注：任何时候你在运行程序的时候它失控了，只用按下 CTRL-C ，程序就会终止。
```

#### 常见问题

1. `for-loop`和`while-loop`的区别是什么？

&emsp;&emsp;**答：**  `for-loop`只能迭代（循环）一些东西的集合，而`while-loop`能够迭代（循环）任何类型的东西。不过，`while-loop`很难用对，而你通常能够用`for-loop`完成很多事情。

2. 循环好难，我应该如何理解它们？ 

&emsp;&emsp;**答：** 人们不理解循环的主要原因是他们跟不上代码的运行。当一个循环运行的时候，它会过一遍代码块，到结尾之后再跳到顶部。为了直观表现这个过程，你可以用`print`打印出循环的整个过程，把`print`行写在循环的前面、顶部、中间、结尾。研究一下输出的内容，试着理解它是如何运行的。

## 4.4 分支和函数

&emsp;&emsp;目前为止你已经了解了`if`语句，函数以及列表。现在是时候深入学习一下了。照例输入如下代码，看看你能否明白程序在做什么。

```{code-block} python
:linenos:

from sys import exit

def gold_room():
    print("This room is full of gold. How much do you take?")

    choice = input("> ")
    if "0" in choice or "1" in choice:
        how_much = int(choice)
    else:
        dead("Man, learn to type a number.")

    if how_much < 50:
        print("Nice, you're not greedy, you win!")
        exit(0)
    else:
        dead("You greedy bastard!")


def bear_room():
    print("There is a bear here.")
    print("The bear has a bunch of honey.")
    print("The fat bear is in front of another door.")
    print("How are you going to move the bear?")
    bear_moved = False

    while True:
        choice = input("> ")

        if choice == "take honey":
            dead("The bear looks at you then slaps your face")
        elif choice == "taunt bear" and not bear_moved:
            print("The bear has moved from the door.")
            print("You can go through it now.")
            bear_moved = True
        elif choice == "taunt bear" and bear_moved:
            dead("The bear gets pissed off and chews your leg.")
        elif choice == "open door" and bear_moved:
            gold_room()
        else:
            print("I got no idea what that means.")


def cthulhu_room():
    print("Here you see the great evil Cthulhu.")
    print("He, it, whatever stares at you and you go insane.")
    print("Do you flee for your life or eat your head?")

    choice = input("> ")

    if "flee" in choice:
        start()
    elif "head" in choice:
        dead("Well that was tasty!")
    else:
        cthulhu_room()


def dead(why):
    print(why, "Good job!")
    exit(0)

def start():
    print("You are in a dark room.")
    print("There is a door to your right and left.")
    print("Which one do you take?")

    choice = input("> ")

    if choice == "left":
        bear_room()
    elif choice == "right":
        cthulhu_room()
    else:
        dead("You stumble around the room until you starve.")


start()
```

&emsp;&emsp;上述代码的运行结果：

```shell
You are in a dark room.
There is a door to your right and left. Which one do you take?
>   left
There is a bear here.
The bear has a bunch of honey.
The fat bear is in front of another door. How are you going to move the bear?
>   taunt bear
The bear has moved from the door. You can go through it now.
>   open door
This room is full of gold.  How much do you take?
>   1000
You greedy bastard! Good job!
```

```{admonition} 附加练习
:class: tip

1. 画一个这个游戏的流程图，并指出它是如何运转的。
2. 修正你的错误，包括拼写和语法错误。
3. 为你不理解的函数写上注释。
4. 为游戏增加一些功能，同时使代码更加简化。
5. 这个`gold_room`让你输入数字的方式有点奇怪。这样做有哪些`bug`？你能改善我的代码吗？可以查查看`int()`的相关知识。
```

#### 常见问题

1. 救命! 这个程序是怎么工作的！？ 

&emsp;&emsp;**答：** 当你遇到不理解的代码时，不要着急，只要在每行代码下面写下注释，弄清楚这一行是做什么的，就很容易明白。确保你的注释和代码一样简洁。 然后要么画图，要么写一段话来描述代码是如何运行的。这样你就会理解其背后的原理。

2. 为什么你要用`while True`？ 

&emsp;&emsp;**答：** 这样可以构建一个无限循环。

3. `exit(0)`是干什么用的？ 

&emsp;&emsp;**答：** 在很多操作系统中，一个程序可以用`exit(0)`来结束，其中传入的数字代表是否有错误。如果你用`exit(1)`代表有1个错误，`exit(0)`则代表程序正常退出。它不同于通常的布尔逻辑（`0==False`），因为你可以用不同的数字来表示不同的错误结果。你可以用`exit(100)`来表示与`exit(2)`或者`exit(1)`不同的错误结果。

4. 为什么`input()`有时会被写成`input('> ')`？  

&emsp;&emsp;**答：** `input`的参数是一个字符串，所以要在获取用户输入的内容前面加一个提示符。这里`>`也可以换成想要提示用户的文字。