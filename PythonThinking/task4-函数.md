## 2.9 函数

&emsp;&emsp;这是一个很大的标题，接下来我要给你介绍一下函数。每一个程序员都要一遍一遍地用到函数，思考它们的作用以及如何使用它们，但是我会给你一些最简单的解释，让你能够快速上手。

&emsp;&emsp;函数一般就是做以下这些事情：

1. 它们为一些代码起名字，就像变量为字符串和数字起名字一样。
2. 它们像脚本获取`argv`一样获取参数（arguments）。
3. 通过 1 和 2 的操作，让你做一些你自己的“小脚本”或者“微命令”。

### 2.9.1 函数初体验

&emsp;&emsp;你可以通过在 Python 中使用`def`来创建一个函数。我会让你创建 4 个不同的函数，它们就像你的脚本一样运行，之后我还会展示每一个之间是如何关联的。

```{code-block} python
:linenos:
# this one is like your scripts with argv
def print_two(*args):
    arg1, arg2 = args
    print(f"arg1: {arg1}, arg2: {arg2}")

# ok, that *args is actually pointless, we can just do this
def print_two_again(arg1, arg2):
    print(f"arg1: {arg1}, arg2: {arg2}")

# this just takes one argument
def print_one(arg1):
    print(f"arg1: {arg1}")

# this one takes no arguments
def print_none():
    print("I got nothin'.")


print_two("Zed","Shaw")
print_two_again("Zed","Shaw")
print_one("First!")
print_none()
```

&emsp;&emsp;让我们把第一个函数拆解一下，`print_two` 这是你从创建脚本中已经学到的最熟悉的东西：

1. 首先，我们告诉 Python 想要用`def`（即 define）来创建一个函数。
2. 在`def`的同一行，我们给了函数一个名字，本例中是`print_two`，但是也可以起名叫“peanuts”（花生），名字没关系，不过最好简短一些，并且能够说明这个函数的作用。
3. 然后我们告诉它，我们想要`*args`，它很像参数`args`，只不过是为函数设的，必须放在括号里面才能工作。
4. 然后我们以`:`结束这一行，另起一行开始缩进。
5. 在`:`之后缩进四个空格的所有行都是关于`print_two`这个函数名的。我们第一个缩进的行就是用来解包这个参数（argument），跟之前的脚本一样。
6. 要表明它是如何工作的，我们把这些参数打印了出来，就像我们在脚本中所做的一样。

&emsp;&emsp;`print_two`的问题是它不是创建一个函数最简单的方法。在 python 里面，我们可以跳过整个解包参数的过程，只用我们需要的`()`里面的名字即可，这也正是`print_two_again`所做的事情。之后我们用一个参数创建`print_one`这个函数。最后创建了一个没有参数的函数`print_none`。

| 警告！                                                       |
| :----------------------------------------------------------- |
| &emsp;&emsp;这很重要。如果你现在不太明白，别急着灰心，我们会再做几个跟函数相关的练习来进一步学习。现在当我说“函数”的时候，你就把它想象成一个“迷你脚本”，跟着做就行了。 |

#### 你会看到

&emsp;&emsp;如果你运行了上述代码，就会看到：

```
arg1: Zed, arg2: Shaw
arg1: Zed, arg2: Shaw
arg1: First!
I got nothin'.
```

&emsp;&emsp;现在你已经看到了函数是如何工作的。注意使用函数的方式就像你使用`exists`、`open`等其他一些“命令”一样。其实我一直在跟你卖关子，因为在 Python 里面，这些“命令”就是“函数”。这意味着你可以创建你自己的命令然后在你的脚本中使用。

#### 附加练习

创建一个如下的函数 checklist （核查表）用于后面的练习。把这些内容写在索引卡上，一直保留到你完成所有剩余练习的时候或者当你感觉你不再需要这些索引卡的时候：

##### 练习1

&emsp;&emsp;你是否用 `def` 来创建函数了？

##### 练习2

&emsp;&emsp;你的函数名是只包含字符和 `_` （下划线）吗？

##### 练习3

&emsp;&emsp;你在函数名后面放 `(` （左圆括号）了吗？

##### 练习4

&emsp;&emsp;你在左圆括号后面放参数（argument）了吗？参数之间是以逗号隔开的吗？）

##### 练习5

&emsp;&emsp;你的每个参数都是唯一的吗（即没有重名）？

##### 练习6

&emsp;&emsp;你在参数后面放 `)` （右圆括号）和 `:` （冒号）了吗？

##### 练习7

&emsp;&emsp;你在与这个函数相关的代码行前面加上四个空格的缩进了吗？（不能多，也不能少）

##### 练习8

&emsp;&emsp;你是通过另起一行不缩进来结束你的函数的吗？

---
Tips：
当你运行（使用或者调用）一个函数时，检查以下事项：

1. 你是通过输入函数名称来运行/调用/使用一个函数的吗？
2. 你运行的时候有在名称后面加 `(` 吗？
3. 你有把你想要的值放在圆括号里并用逗号隔开了吗？
4. 你是以 `)` 来结束调用这个函数的吗？

---

&emsp;&emsp;在接下来的课程中用这两个`checklist`，直到你不再需要它们为止。最后，再强调一下，我说的“运行”（run）、“调用”（call）、“使用”（use）都是一个意思。

#### 常见问题

1. **函数名称有哪些要求？** 跟变量名一样，任何不以数字开头的字母、数字、下划线组合都可以。

2. **`\*args` 中的 `\*` 是什么作用？** 这是告诉 Python 取所有的参数给函数，然后把它们放在`args`里放成一列，很像你之前学的`argv`，只不过这个是为函数设置的。这种不常用，除非有特殊需要。

&emsp;&emsp;**这部分好无聊好烦人啊。** 这就对了，这说明你已经开始一边输入代码一边思考它的作用了。如果想让它不这么无聊，按照我的要求一字不差地输入进去，然后再故意打乱它们，看看你能不能修复好。

### 2.9.2 函数和变量

&emsp;&emsp;函数是一个信息量巨大的东西，但是别担心，老老实实做练习，仔仔细细核对`checklist`，你最终会掌握它的。

&emsp;&emsp;有个小点你可能没注意到，我们会在之后进行强化：你函数里面的变量跟你脚本里面的变量没有关联。通过下面这个练习思考一下这个问题：

```{code-block} python
:linenos:

def cheese_and_crackers(cheese_count, boxes_of_crackers):
    print(f"You have {cheese_count} cheeses!")
    print(f"You have {boxes_of_crackers} boxes of crackers!"
    print("Man that's enough for a party!")
    print("Get a blanket.\n")


print("We can just give the function numbers directly:")
cheese_and_crackers(20, 30)


print("OR, we can use variables from our script:")
amount_of_cheese = 10
amount_of_crackers = 50

cheese_and_crackers(amount_of_cheese, amount_of_crackers)


print("We can even do math inside too:")
cheese_and_crackers(10 + 20, 5 + 6)

print("And we can combine the two, variables and math:")
cheese_and_crackers(amount_of_cheese + 100, amount_of_crackers + 1000)
```

&emsp;&emsp;这个练习展示了我们可以给函数`cheese_and_crackers`赋值的几种不同的方式，可以直接给它数字，或者变量，亦或是数学运算，甚至是数学运算和变量的结合。

&emsp;&emsp;从某种程度上说，函数的参数有点类似于我们给变量赋值时的`=`符号 。事实上，如果你可以用`=`来定义一个东西，你就可以把它作为参数赋给函数。

#### 你会看到

&emsp;&emsp;你应该研究一下这个脚本的输出结果，把它和你之前的脚本输出结果对比一下。

```{code-block} python
:linenos:

We can just give the function numbers directly:
You have 20 cheeses!
You have 30 boxes of crackers!
Man that's enough for a party!
Get a blanket.

OR, we can use variables from our script:
You have 10 cheeses!
You have 50 boxes of crackers!
Man that's enough for a party!
Get a blanket.

We can even do math inside too:
You have 30 cheeses!
You have 11 boxes of crackers!
Man that's enough for a party!
Get a blanket.

And we can combine the two, variables and math:
You have 110 cheeses!
You have 1050 boxes of crackers!
Man that's enough for a party!
Get a blanket.
```

#### 附加练习

##### 练习1

&emsp;&emsp;回顾一遍这个脚本，然后在每一行上方加上注释，解释它的作用。

##### 练习2

&emsp;&emsp;从下到上阅读每一行，说出所有重要的字符。

##### 练习3

&emsp;&emsp;写至少一个自己设计的函数，然后用 10 种不同的方式运行它。

#### 常见问题

1. **运行一个函数怎么可能有 10 种不同的方式？** 爱信不信，理论上讲，任何函数都有无数种调用方式。看看你对于函数、变量以及用户输入的创造力有多强。

&emsp;&emsp;**有没有什么方法能分析函数是如何运行的，以帮助我更好地理解它？** 有很多方法，但是你先试试给每行加注释这种方式。其他方法包括大声把代码读出来，或者把代码打印出来然后在上面画图，来展示它是怎么运行的。

2. **如果我想问用户关于`cheese`和`crackers`的数字呢？** 你需要用`int()`来把通过`input()`获取的内容转化成数值。

3. **在函数中创建`amount_of_cheese`这个变量会改变`cheese_count`这个变量吗？** 不会的，这些变量是相互独立并存在于函数之外的。它们之后会传递给函数，而且是“暂时版”，只是为了让函数运行。当函数退出之后，这些暂时的变量就会消失，其他一切正常运行。接着往下学，你会慢慢明白的。

4. **像`amount_of_cheese`这样的全局变量（`global variables`）跟函数变量同名的话是不是不太好？** 是的，如果这样的话，你就不知道说的到底是哪个变量了。不过有时候可能不得不用同样的名字，或者可能不小心同名了，不管怎么样，尽量避免这种情况。

5. **一个函数里包含的参数有数量限制吗？** 这取决于 Python 的版本以及你的电脑，但是这个数量其实相当大。实践中一个函数包含 5 个参数为宜，再多就比较难用了。

6. **你能在一个函数里面调用一个函数吗？** 可以，在之后的练习里会创建一个小游戏，到时候就会用到这个。

### 2.9.3 函数和文件

&emsp;&emsp;记住你的函数`checklist`，然后在做这个练习的时候注意函数是如何和文件一起工作并发挥一些作用的。

```{code-block} python
:linenos:

from sys import argv

script, input_file = argv

def print_all(f):
    print(f.read())

def rewind(f):
    f.seek(0)

def print_a_line(line_count, f):
    print(line_count, f.readline())

current_file = open(input_file)

print("First let's print the whole file:\n")

print_all(current_file)

print("Now let's rewind, kind of like a tape.")

rewind(current_file)

print("Let's print three lines:")

current_line = 1
print_a_line(current_line, current_file)

current_line = current_line + 1
print_a_line(current_line, current_file)

current_line = current_line + 1
print_a_line(current_line, current_file)
```

&emsp;&emsp;着重注意我们是如何在每次运行`print_a_line`的时候把当前行的数字传递出去的。

#### 你会看到

```shell
First let's print the whole file:

This is line 1
This is line 2
This is line 3

Now let's rewind, kind of like a tape.      
Let's print three lines:
1   This is line 1

2   This is line 2

3   This is line 3
```

#### 附加练习

##### 练习1

&emsp;&emsp;在每一行上方添加注释解释它的作用。

##### 练习2

&emsp;&emsp;每次`print_a_line`运行的时候，都在传入一个`current_line`变量。写出每一次调用函数的时候`current_line`等于什么，然后找出它是如何变成`print_a_line`里面的`line_count`的。

##### 练习3

&emsp;&emsp;找出每一个用到函数的地方，然后检查它的`def`确保你给出了正确的参数。

##### 练习4

&emsp;&emsp;在网上搜搜`seek`这个函数的作用。试着输入`pydoc file`，看看你能否从这里看明白。然后试着输入`pydoc file.seek`，再看看`seek`是用来干嘛的。

##### 练习5

&emsp;&emsp;搜一下简化符号`+=`，然后用`+=`重新写这个脚本。

#### 常见问题

1. **在`print_all`和其他函数里的`f`是什么东西？** `f`是一个变量，就像你在练习 18 中函数的变量一样，只不过这次它是一个文件。文件在 Python 里面有点类似于一个老式电脑里面的磁带驱动器，或者一个 DVD 播放机。它有一个“读取头”（read head），你可以在文件里`seek`（寻找）这个读取头所在的位置，然后在那里工作。每次你做`f.seek(0)`的时候，都会从移动到文件最开始，每次执行`f.readline()`的时候，都在从文件里读取一行内容，并且把读取头移动到`\n`后面，也就是每行结束的地方。我会在后面给你做更详细的解释。

2. **为什么`seek(0)`没有把`current_line`设置为0？** 首先，`seek()`函数处理的是字节（bytes），不是行。`seek(0)` 这个代码把文件移动到0字节（也就是第一个字节处）。其次，`current_line`只是一个变量并且跟这个文件没有任何实际联系。我们是在手动累加它。

3. **什么是`+=`？** 你知道在英语里我们可以把 “it is” 写成 “it's” ，或者把 “you are” 写成“you're” ，这叫缩写（contraction）。而`+=`就像`=`和`+`两种运算的缩写。也就是`x = x + y`就等同于`x += y`。

4. **`readline()`是怎么知道每一行在哪儿的？** `readline()`里面的代码能够扫描文件的每个字节，当它发现一个`\n`字符，它就会停止扫描这个文件，然后回到它发现的地方。文件`f`就负责在每次调用`readline()`之后维持文件的当前位置，以此来保证它能阅读到每一行。

5. **为什么文件中的行之间会有空行？** `readline()`函数返回文件中每行最后的`\n` 。又在`print`函数的结尾加上一个`end = " "`来避免给每行加上两个`\n`。

### 2.9.4 函数可以返回一些东西

&emsp;&emsp;你已经使用了`=`来命名变量，并给变量赋予数值或字符串。接下来我会教你如何用`=`和一个新的 Python 字符`return`来把函数中的变量设置为一个值。有一点需要密切注意，但是先输入如下代码：

```{code-block} python
:linenos:

def add(a, b):
    print(f"ADDING {a} + {b}")
    return a + b

def subtract(a, b):
    print(f"SUBTRACTING {a} - {b}")
    return a - b

def multiply(a, b):
    print(f"MULTIPLYING {a} * {b}")
    return a * b

def divide(a, b):
    print(f"DIVIDING {a} / {b}")
    return a / b


print("Let's do some math with just functions!")

age = add(30, 5)
height = subtract(78, 4)
weight = multiply(90, 2)
iq = divide(100, 2)

print(f"Age: {age}, Height: {height}, Weight: {weight}, IQ: {iq}")


# A puzzle for the extra credit, type it in anyway.
print("Here is a puzzle.")

what = add(age, subtract(height, multiply(weight, divide(iq, 2))))

print("That becomes: ", what, "Can you do it by hand?")
```

&emsp;&emsp;我们现在要做我们自己的加减乘除数学运算了。我说的要密切注意的是`add`函数里面的`return a + b`，这步做的是这些事情：

1. 我们的函数是以两个参数被调用的：`a`和`b`。
2. 我们把函数所做的事情打印出来，在本例中是“ADDING”。
3. 然后我们让 Python 做一些反向的事情：我们返回`a + b`的和。你可以这样描述：我用`a`加上`b`，然后返回它们的结果。
4. Python 把这两个数加起来。然后当函数终止的时候，运行了这个函数的任何一行都能够将`a + b`的结果赋予一个变量。

&emsp;&emsp;和本项目的其他内容比起来，这块你确实应该把节奏放慢一些，把代码打乱，然后试着琢磨一下每一步都发生了什么。

#### 你会看到

```shell
Let's do some math with just functions!  
ADDING 30 + 5
SUBTRACTING 78 - 4
MULTIPLYING 90 * 2
DIVIDING 100 / 2
Age: 35, Height: 74, Weight: 180, IQ: 50.0
Here is a puzzle.
DIVIDING 50.0 / 2
MULTIPLYING 180 * 25.0
SUBTRACTING 74 - 4500.0
ADDING 35 + -4426.0
That becomes:   -4391.0
Can you do it by hand?
```

#### 附加练习

##### 练习1

&emsp;&emsp;如果你还不能真正理解`return`是干什么的，试着写几个你自己的函数，并且让它们返回一些值。你可以让它`return`任何东西，只要你把它们放在`=`右边即可。

##### 练习2

&emsp;&emsp;脚本的最后是一个难题。我在用一个函数的返回值作为另一个函数的参数，这是在一个链（chain）里面进行的，这样就用函数创建了一个公式。它看起来确实很难，但是如果你运行这个脚本，你就可以看到结果。你要做的就是试着弄明白创建同样操作的平常的函数是什么样的。

##### 练习3

&emsp;&emsp;一旦你有了可以解出这个难题的公式，试着对函数的某些部分做做改动，看看会发生什么。有意改动一些数让它产生一些不同的值。

##### 练习4

&emsp;&emsp;做相反的操作。写一个简单的公式，然后用同一种方式通过函数来计算它。

&emsp;&emsp;这个练习可能真的很让你头大，但是放松，慢点学，把它当成是一个小游戏。正是解决这样的难题让编程如此有趣，所以之后我还会再给你一些小问题让你解决。

#### 常见问题

1. **为什么 python 是“从后往前”（backward打印公式或者函数的？** 它其实不是从后往前，它是从里到外（inside out）。当你开始把代码打乱成分开的公式和函数时，你会看到它是如何工作的。试着理解我说的 “inside out” 而不是 “backward” 。

2. **我如何使用`input()`来输入我自己的值？** 还记得`int(input())`吗？这样做的问题是你不能输入浮点数，所以试着用`float(input())`来代替。

3. **你说的“写一个公式”是什么意思？** 先试试`24 + 34 / 100 - 1023`吧，变成使用函数来计算。然后自己想出一个类似的数学公式，要用变量让它看起来更像一个公式。








