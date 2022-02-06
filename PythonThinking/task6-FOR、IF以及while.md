## 4、FOR、IF以及while
### IF语句
#### IF
```python
1   people = 20
2   cats = 30
3   dogs = 15
4
5
6   if people < cats:
7       print("Too many cats! The world is doomed!")
8
9   if people > cats:
10      print("Not many cats! The world is saved!")
11
12  if people < dogs:
13      print("The world is drooled on!")     
14
15  if people > dogs:
16      print("The world is dry!")
17
18
19  dogs += 5
20
21  if people >= dogs:
22      print("People are greater than or equal to dogs.")
23
24  if people <= dogs:
25      print("People are less than or equal to dogs.")
26
27
28  if people == dogs:
29      print("People are dogs.")
```
运行结果

```python
Too many cats! The world is doomed!     
The world is dry!
People are greater than or equal to dogs.
People are less than or equal to dogs.
People are dogs.
```
#### 附加练习
在附加练习中，试着猜猜 if 语句是什么以及它是干什么的。在继续进行下个练习之前，试着用自己的话回答以下这些问题，

1、你认为 if 对它下面的代码起什么作用？
2、为什么 if 下面的代码要缩进 4 个空格？
3、如果没有缩进会发生什么？
4、你能把一些布尔表达式放进 if 语句吗？试试看。
5、如果你改变 people，cats 和 dogs 的初始值会发生什么？
#### 常见问题
+= 是什么意思？ x += 1 就相当于 x = x + 1 ，但是输入的内容更少。你可以把它叫做“累加”（increment by）运算符。之后你还会学到 -= 这样类似的表达。
#### Else 和 if
在上个练习中你学到了一些 if 语句，思考了它的含义和作用。在你学习更多内容之前，我会解释一下上个附加练习中的问题。首先确定你做了那些练习。

1. 你认为 if 对它下面的代码起什么作用？

if 语句在代码中创建了一个“分支”（branch），有点类似于在一本冒险书中，你选择了哪个答案，就翻到对应的一页，如果你选择了不同的答案，就会去到不同的地方。if 语句就是告诉脚本，如果这个布尔表达式是 True，那就运行它下面的代码，否则的话就跳过。

2. 为什么 if 下面的代码要缩进 4 个空格？

通过一行代码结尾的冒号告诉 Python 你在创建一个新的代码块，然后缩进四个空格告诉 Python 这个代码块中都有些什么。这就跟本书前半部分中你学的函数是一样的。

3. 如果没有缩进会发生什么？

如果没有缩进，你很可能收到一个错误提示。Python 一般会让你在一个带 ： 的代码行下面缩进一些内容。

4. 你能把一些布尔表达式放进 if 语句吗？试试看。

试试吧，你可以的。你可以把它们写得很复杂，不过复杂的东西一般风格都很糟糕。

5. 如果你改变 people，cats 和 dogs 的初始值会发生什么？

因为你在比较数字，所以如果你改变了数字，不同的 if 语句将会得出不同的判断结果，那么下面某些代码块就有可能运行。回到练习中给这些变量一些不同的数值，然后看看你能否在脑中判断出来哪些代码块会运行。

把我的答案和你的比较一下，然后确保你真的理解了代码块的概念。这对你进行接下来的练习很重要。把下面的代码输入进去然后运行。

```python
1   people = 30
2   cars = 40
3   trucks = 15
4
5
6   if cars > people:
7       print("We should take the cars.")
8   elif cars < people:
9       print("We should not take the cars.")
10  else:
11      print("We can't decide.")
12
13  if trucks > cars:
14      print("That's too many trucks.")
15  elif trucks < cars:
16      print("Maybe we could take the trucks.")
17  else:
18      print("We still can't decide.")
19
20  if people > trucks:
21      print("Alright, let's just take the trucks.")
22  else:
23      print("Fine, let's stay home then.")
```

运行结果

```python
We should take the cars.
Maybe we could take the trucks.
Alright, let's just take the trucks.
```
##### 附加练习
1、试着猜猜 elif 和 else 的作用是什么。
2、改变 cars，people，和 trucks 的数值，然后追溯每一个 if 语句，看看什么会被打印出来。
3、试试一些更复杂的布尔表达式，比如cars > people 或者 trucks < cars。
4、在每一行上面加上注释。

##### 常见问题
如果多个 elif 块都是 True 会发生什么？ Python 从顶部开始，然后运行第一个是 True 的代码块，也就是说，它只会运行第一个。


#### IF嵌套使用
前面主要学习了调用函数、打印东西，但是这些基本都是直线运行下来的。你的脚本从上面开始运行，然后到底部结束。如果你用了一个函数，你可以随后再运行它，但是仍然不会有分叉需要你做决定的情况。现在你学习了 if，else，以及 elif，你就可以让脚本来做决定了。

在上个脚本中你写出了一个简单的问问题的测试集。在这个练习中你将问用户一些问题，并基于他们的回答做决定。写下这个脚本，然后多玩几遍，把它弄明白。

```python
1   print("""You enter a dark room with two doors.
2   Do you go through door #1 or door #2?""")
3
4   door = input("> ")
5
6   if door == "1":
7       print("There's a giant bear here eating a cheese cake.")
8       print("What do you do?")
9       print("1. Take the cake.")
10      print("2. Scream at the bear.")     
11
12      bear = input("> ")
13
14      if bear == "1":
15          print("The bear eats your face off. Good job!")
16      elif bear == "2":
17          print("The bear eats your legs off. Good job!")
18      else:
19          print(f"Well, doing {bear} is probably better.")
20          print("Bear runs away.")
21
22  elif door == "2":
23      print("You stare into the endless abyss at Cthulhu's retina.")
24      print("1. Blueberries.")
25      print("2. Yellow jacket clothespins.")
26      print("3. Understanding revolvers yelling melodies.")
27
28      insanity = input("> ")
29
30      if insanity == "1" or insanity == "2":
31          print("Your body survives powered by a mind of jello.")
32          print("Good job!")
33      else:
34          print("The insanity rots your eyes into a pool of muck.")
35          print("Good job!")
36
37  else:
38      print("You stumble around and fall on a knife and die. Good job!")
```
这里很关键的一点是你现在在 if 语句里面又放了一个 if 语句。这在创建“嵌套”（nested）决定的时候非常有用，每一个分支指向另一个选择。

确保你理解了在 if 语句中嵌套 if 语句的理念。你可以通过做附加练习来真正掌握它。
##### 你会看到
这是我玩这个冒险小游戏的结果，我可能玩得没那么好。

```python
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
##### 附加练习
给这个游戏加一些新内容，同时改变用户可以做的决定。尽可能地扩展这个游戏，直到它变得很搞笑。
写一个完全不同的新游戏。可能你不喜欢我的这个，你可以做一个你自己的。

##### 常见问题
我能用一系列的 if 语句来代替 elif 吗？在某些情况下可以，但是取决于每个 if/else 是怎么写的。如果这样的话还意味着 Python 将会检查每一个 if-else 组合，而不是像 if-elif-else 组合那样只会检查第一个是 false 的。你可以多试几次，感受一下区别。

我如何表示一个数字的区间？有两种方式：一种是 0 < x < 10 或者 1 <= x < 10 这种传统表示方法，另一种是 x 的区间是 (1, 10)。

如果我想在 if-elif-else 代码块中放更多的选择怎么办？为每种可能的选择增加更多的 elif 块。
### FOR语句

```python
1   the_count = [1, 2, 3, 4, 5]
2   fruits = ['apples', 'oranges', 'pears', 'apricots']
3   change = [1, 'pennies', 2, 'dimes', 3, 'quarters']
4
5   # this first kind of for-loop goes through a list
6   for number in the_count:
7       print(f"This is count {number}")
8
9   # same as above
10  for fruit in fruits:
11      print(f"A fruit of type: {fruit}")
12
13  # also we can go through mixed lists too
14  # notice we have to use {} since we don't know what's in it
15  for i in change:
16      print(f"I got {i}")
17
18  # we can also build lists, first start with an empty one
19  elements = []
20
21  # then use the range function to do 0 to 5 counts
22  for i in range(0, 6):
23      print(f"Adding {i} to the list.")
24  # append is a function that lists understand
25      elements.append(i)
26
27  # now we can print them out too
28  for i in elements:
29      print(f"Element was: {i}")
```
##### 运行结果

```python
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
##### 附加练习
看看你是如何使用 range 的。查阅上面的 range 函数并理解掌握。
你能在第 22 行不使用 for-loop，而是直接把 range(0, 6) 赋给 elements 吗？
找到 Python 文档关于列表的部分，然后读一读。看看除了 append，你还能对列表做哪些操作？
##### 常见问题
如何创建一个二维列表？可以用这种列表中的列表：[[1,2,3],[4,5,6]]

列表（lists）和数组（arrays）难道不是一个东西吗？这取决于语言以及实现方法。在传统术语中，列表和数组的实现方式不同。在 Ruby 中都叫做 arrays，在 python 中都叫做 lists。所以我们就把这些叫做列表吧。

为什么 for-loop 可以用一个没有被定义的变量？变量在 for-loop 开始的时候就被定义了，它被初始化到了每一次 loop 迭代时的当前元素中。

为什么 range(1, 3) 中的 i 只循环了两次而不是三次？ range() 函数只处理从第一个到最后一个数，但不包括最后一个数，所以它在 2 就结束了。这是这类循环的通用做法。

element.append() 的作用是什么？它只是把东西追加到列表的末尾。打开 Python shell 然后创建一个新列表。任何时候当你遇到类似的用法，试着多玩几次，去体会它们的作用。


### while语句
现在我们来看一个新的循环： while-loop。只要一个布尔表达式是 True，while-loop 就会一直执行它下面的代码块。

等等，你应该能理解这些术语吧？如果我们写一行以 : 结尾的代码，它就会告诉 Python 开始一个新的代码块。我们用这种方式来结构化你的程序，以便 Python 明白你的意图。如果你还没有掌握这块内容，先回去复习一下，再做一些 if 语句、函数以及 for-loop，直到你掌握为止。

之后我们会做一些练习来训练你的大脑读取这些结构，就像我们训练你掌握布尔表达式一样。

回到 while-loop，它所做的只是像 if 语句一样的测试，但是它不是只运行一次代码块，而是在 while 是对的地方回到顶部再重复，直到表达式为 False。

但是 while-loop 有个问题：有时候它们停不下来。如果你的目的是让程序一直运行直到宇宙的终结，那这样的确很屌。但大多数情况下，你肯定是需要你的循环最终能停下来的。

##### 为了避免这些问题，你得遵守一些规则：

1、保守使用 while-loop，通常用 for-loop 更好一些。
2、检查一下你的 while 语句，确保布尔测试最终会在某个点结果为 False。
3、当遇到问题的时候，把你的 while-loop 开头和结尾的测试变量打印出来，看看它们在做什么。
在这个练习中，你要通过以下三个检查来学习 while-loop：

```python
1   i = 0
2   numbers = []
3
4   while i < 6:
5       print(f"At the top i is {i}")
6       numbers.append(i)
7
8       i = i + 1
9       print("Numbers now: ", numbers)
10      print(f"At the bottom i is {i}")
11
12
13  print("The numbers: ")
14
15  for num in numbers:
16      print(num)
```

##### 运行结果

```python
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


##### 附加练习
1、把这个 while-loop 转换成一个你可以调用的函数，然后用一个变量替代 i < 6 里面的 6。
2、用这个函数重新写一下这个脚本，试试不同的数值。
3、再增加一个变量给这个函数的参数，然后改变第 8 行的 +1，让它增加的值与之前不同。
4、用这个函数重新写这个脚本，看看会产生什么样的效果。
5、用 for-loop 和 range 写这个脚本。你还需要中间的增加值吗？如果不去掉这个增加值会发生什么？
任何时候你在运行程序的时候它失控了，只用按下 CTRL-C ，程序就会终止。


##### 常见问题
for-loop 和 while-loop 的区别是什么？ for-loop 只能迭代（循环）一些东西的集合，而 while-loop 能够迭代（循环）任何类型的东西。不过，while-loop 很难用对，而你通常能够用 for-loop 完成很多事情。

循环好难，我应该如何理解它们？人们不理解循环的主要原因是他们跟不上代码的运行。当一个循环运行的时候，它会过一遍代码块，到结尾之后再跳到顶部。为了直观表现这个过程，你可以用 print 打印出循环的整个过程，把 print 行写在循环的前面、顶部、中间、结尾。研究一下输出的内容，试着理解它是如何运行的。

### 分支和函数
目前为止你已经了解了 if 语句，函数以及列表。现在是时候深入学习一下了。照例输入如下代码，看看你能否明白程序在做什么。

```python
1   from sys import exit
2
3   def gold_room():
4       print("This room is full of gold. How much do you take?")
5
6       choice = input("> ")
7       if "0" in choice or "1" in choice:
8           how_much = int(choice)
9       else:
10          dead("Man, learn to type a number.")
11
12      if how_much < 50:
13          print("Nice, you're not greedy, you win!")
14          exit(0)
15      else:
16          dead("You greedy bastard!")
17
18
19  def bear_room():
20      print("There is a bear here.")
21      print("The bear has a bunch of honey.")
22      print("The fat bear is in front of another door.")
23      print("How are you going to move the bear?")
24      bear_moved = False
25
26      while True:
27          choice = input("> ")
28
29          if choice == "take honey":
30              dead("The bear looks at you then slaps your face")
31          elif choice == "taunt bear" and not bear_moved:
32              print("The bear has moved from the door.")
33              print("You can go through it now.")
34              bear_moved = True
35          elif choice == "taunt bear" and bear_moved:
36              dead("The bear gets pissed off and chews your leg.")
37          elif choice == "open door" and bear_moved:
38              gold_room()
39          else:
40              print("I got no idea what that means.")
41
42
43  def cthulhu_room():
44      print("Here you see the great evil Cthulhu.")
45      print("He, it, whatever stares at you and you go insane.")
46      print("Do you flee for your life or eat your head?")
47
48      choice = input("> ")
49
50      if "flee" in choice:
51          start()
52      elif "head" in choice:
53          dead("Well that was tasty!")
54      else:
55          cthulhu_room()
56
57
58  def dead(why):
59      print(why, "Good job!")
60      exit(0)
61
62  def start():
63      print("You are in a dark room.")
64      print("There is a door to your right and left.")
65      print("Which one do you take?")
66
67      choice = input("> ")
68
69      if choice == "left":
70          bear_room()
71      elif choice == "right":
72          cthulhu_room()
73      else:
74          dead("You stumble around the room until you starve.")
75
76
77  start()
```

##### 运行结果

```python
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

##### 附加练习
1、画一个这个游戏的流程图，并指出它是如何运转的。
2、修正你的错误，包括拼写和语法错误。
3、为你不理解的函数写上注释。
4、为游戏增加一些功能，同时使代码更加简化。
5、这个 gold_room 让你输入数字的方式有点奇怪。这样做有哪些 bug ？你能改善我的代码吗？可以查查看 int() 的相关知识。

##### 常见问题
救命! 这个程序是怎么工作的！？ 当你遇到不理解的代码时，不要着急，只要在每行代码下面写下注释，弄清楚这一行是做什么的，就很容易明白。确保你的注释和代码一样简洁。 然后要么画图，要么写一段话来描述代码是如何运行的。这样你就会理解其背后的原理。

为什么你要用 while True？ 这样可以构建一个无限循环。

exit(0) 是干什么用的？ 在很多操作系统中，一个程序可以用 exit(0) 来结束，其中传入的数字代表是否有错误。如果你用 exit(1) 代表有 1 个错误， exit(0) 则代表程序正常退出。它不同于通常的布尔逻辑（0==False），因为你可以用不同的数字来表示不同的错误结果。你可以用 exit(100) 来表示与 exit(2) 或者 exit(1) 不同的错误结果。

为什么 input() 有时会被写成 input('> ')？ input 的参数是一个字符串，所以要在获取用户输入的内容前面加一个提示符。这里 > 也可以换成想要提示用户的文字。






