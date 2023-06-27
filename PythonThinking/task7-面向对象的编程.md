## 5 面向对象的编程

&emsp;&emsp;Python 是一门“面向对象的编程语言”（Object Oriented Programming）。这是指 Python 中有一个叫做“类（class）”的结构，能够让你用一种特定的方式结构化你的软件。通过使用类，你可以让你的程序保持连贯性，使用起来更清晰。至少理论上是这样。

### 5.1 类的例子

```{code-block} python
:linenos:

class Song(object):   #class表示要创建类，Song是类的名称，
    #根据类 Song 创建对象
    #自动执行Song类的 __init__方法
    def __init__(self, lyrics):   #称为构造方法，根据类创建对象时自动执行
        self.lyrics = lyrics

    def sing_me_a_song(self):     #定义sing_me_a_song函数
        for line in self.lyrics:  #采用for循环获取每一句歌词
            print(line)           #打印出来

happy_bday = Song(["Happy birthday to you",
                   "I don't want to get sued",
                   "So I'll stop right there"])

bulls_on_parade = Song(["They rally around tha family",
                        "With pockets full of shells"])

happy_bday.sing_me_a_song()

bulls_on_parade.sing_me_a_song()
```

&emsp;&emsp;上述代码的运行结果：

```shell
Happy birthday to you
I don't want to get sued
So I'll stop right there
They rally around tha family
With pockets full of shells
```

#### 附加练习

##### 练习1

&emsp;&emsp;用这个方法再写一些歌，确保你明白你正在用字符列表来传歌词。

##### 练习2

&emsp;&emsp;把歌词放在一个单独的变量里，然后把这个变量放在类里面来使用。

##### 练习3

&emsp;&emsp;如果你能搞定这些，可以用它来做更多的事情。要是你现在没什么想法也别担心，就试试看会发生什么。然后把它们掰开、揉碎、反复研究。

##### 练习4

&emsp;&emsp;在网上搜搜“面向对象的编程”，然后填满你的大脑。别担心你看不懂，因为几乎一半的东西我也看不懂。

#### 常见问题

&emsp;&emsp;**为什么我在类下面用`__init__`函数或者其他函数的时候要用`self` ？** 如果你不用`self`，那么像`cheese = 'Frank'`这样的代码就会很含糊，计算机不知道你是指实例的`cheese`属性还是一个叫做`cheese`的局部变量。而用`self.cheese = 'Frank'`的话就会很清晰，你是指实例的属性`self.cheese`。

### 5.2 学着去说面向对象

&emsp;&emsp;在这个练习中，我要教你如何去说“面向对象”。我所做的就是给你一些你需要了解的词和定义。然后我会给出一些需要填空的句子让你去理解。最后，你要完成一个大练习，从而在大脑中巩固这些句子。

#### 词汇训练
（注：为了方便理解，定义保留英文原文。）

- 类（class） ：告诉 Python 创建一个新类型的东西（Tell Python to make a new type of thing）。
- 对象（object）两种含义：最基本类型的东西, 任何实例。（the most basic type of thing, and any instance of something.）
- 实例（instance） ：当你告诉 Python 创建一个类的时候你所得到的东西。（What you get when you tell Python to create a class.）
- def ：你如何在类里面定义一个函数。（How you define a function inside a class.）
- self ：在一个类的函数里面，`self`是被访问的实例/对象的一个变量。（Inside the functions in a class, self is a variable for the instance/object being accessed.）
- 继承（inheritance） ：关于一个类能从另一个类那里继承它的特征的概念，很像你和你的父母。（The concept that one class can inherit traits from another class, much like you and your parents.）
- 组合（composition） ：关于一个类可以由其他一些类构成的概念, 很像一辆车包含几个轮子。（The concept that a class can be composed of other classes as parts, much like how a car has wheels.）
- 属性（attribute） ：类所拥有的从组合那里得到的特性，通常是变量。（A property classes have that are from composition and are usually variables.）
- is-a ：一种用来表达某物继承自一种东西的表述, 就像“三文鱼是一种鱼”。（A phrase to say that something inherits from another, as in a “salmon” is a “fish.”）
- has-a ：一种用来表达某物是由一些东西组成或具有某种特性的表述，就像“三文鱼有一个嘴巴”。（A phrase to say that something is composed of other things or has a trait, as in “a salmon has-a mouth.”）

&emsp;&emsp;花点时间为这些术语做一些闪词卡（flash cards）并记住它们，虽然在你完成这个练习之前单纯的记忆没有任何意义，但你必须要先了解这些基础的词汇。

#### 短语训练

&emsp;&emsp;接下来是一些 Python 代码片段以及右边的解释。

| 代码片段                               | 代码解释                                                     |
| -------------------------------------- | ------------------------------------------------------------ |
| class X(Y)                             | 创建一个名为 X 并继承自 Y 的类。<br/>(“Make a class named X that is-a Y.”) |
| class X(object): def __init__(self, J) | 类 X 有一个带有 self 和 J 参数的 __init__ 函数。<br/>(“class X has-a __init__ that takes self and J parameters.”) |
| class X(object): def M(self, J)        | 类 X 有一个带有 self 和 J 参数的 M 函数。<br/>(“class X has-a function named M that takes self and J parameters.”) |
| foo = X()                              | 设 foo 为类 X 的一个实例。<br/>(“Set foo to an instance of class X.”) |
| foo.M(J)                               | 从 foo 那里获取 M 函数，并用 self 和 J 参数来调用它。<br/>(“From foo, get the M function, and call it with parameters self, J.”) |
| foo.K = Q                              | 从 foo 那里获取 K 属性，并设它为 Q。<br/>(“From foo, get the K attribute, and set it to Q.”) |

&emsp;&emsp;在上述每一句中，当你看到`X`、`Y`、`M`、`J`、`K`、`Q`以及`foo`, 你可以把它们当做空格，比如，我还可以把这些句子写成：

1. “Make a class named ??? that is-a Y.”  
（创建一个名为 ??? 的类，它继承自 Y。）

2. “class ??? has-a __init__ that takes self and ??? parameters.”  
（类 ??? 有一个带了 self 和 ??? 参数的 __init__。）

3. “class ??? has-a function named ??? that takes self and ??? parameters.”  
（类 ??? 有一个名为 ??? 的函数，这个函数带有 self 和 ??? 两个参数。）

4. “Set foo to an instance of class ???.”   
（设 foo 为类 ??? 的一个实例。）

5. “From foo, get the ??? function, and call it with self=??? and parameters ???.”  
（从 foo 那里获取 ??? 函数，并用 self=??? 以及参数 ??? 来调用它。）

6. “From foo, get the ??? attribute, and set it to ???.”  
（从 foo 那里获取 ??? 属性，把它设为 ???。）

&emsp;&emsp;同样地，把这些短语写到一些闪词卡上，然后记一记。把 Python 代码片段放在正面，解释的句子放在背面，你必须每次都正确说出每一个短语的意思。不是说得类似就行，而是要一模一样。

#### 综合训练

&emsp;&emsp;最后一项准备工作是把词汇训练和短语训练结合在一起，以下是训练内容：

1. 做一个短语卡然后练习记忆。
2. 把它翻过来，读句子，如果在句子中看到词汇训练中的词汇，就找到相应的词汇卡片。
3. 练习记忆这些词汇卡片。
4. 坚持练习，要是你感到有些累，就休息一下再继续。

#### 读更多代码

&emsp;&emsp;你现在需要继续读更多的代码，并在这些代码中复习你之前学过的短语。试着找到尽可能多的包含类的文件，然后跟着如下要求去做：

1. 给出每个类的名字，以及其他的类从它那里继承了什么。
2. 在每个类下面，列出它所拥有的函数以及它们的参数。
3. 列出所有它用`self`使用的属性。
4. 对于每个属性，给出它继承自哪个类。

&emsp;&emsp;这些练习的目的是过一遍真实的代码，并试着把你学过的短语和它们的用法匹配和关联起来。如果你做足了训练，你会开始看到这些匹配模式（match patterns）呼之欲出，而不再是一些你不明白的空格或字符。

### 5.3 编写一个属于自己的小游戏

&emsp;&emsp;以下是利用面向对象的编程方法写的宝可梦简单文字游戏，可以按照这个模板续写，或者根据自己的爱好更换角色和游戏方法。

```python
class Pokemon:
    def __init__(self,name,attributes,warnum):
        self.name = name
        self.attributes = attributes
        self.warnum = warnum
    
    def grass(self):
        self.warnum = self.warnum - 100
        
    def rock(self):
        self.warnum = self.warnum - 200
    
    def train(self):
        self.warnum = self.warnum + 200
        
    def detail(self):
        temp = "姓名:%s,属性:%s,战斗力:%s"%(self.name,self.attributes,self.warnum)
        print(temp)
        
#开始游戏，创建角色
Pikachu = Pokemon('皮卡丘','电',1000)
Charizard = Pokemon('喷火龙','火',2000)
Bulbasaur = Pokemon('妙蛙种子','草',700)
Squirtle = Pokemon('杰尼龟','水',1300)


#开始第一次战斗
Pikachu.grass()            #皮卡丘在草丛中战斗
Charizard.rock()          #喷火龙在岩石中战斗
Bulbasaur.grass()          #妙蛙种子在草丛中战斗
Squirtle.rock()            #杰尼龟在岩石中战斗


print("所有神奇宝贝第一次战斗后详细情况")
Pikachu.detail()
Charizard.detail()
Bulbasaur.detail()
Squirtle.detail()

#开始训练
Pikachu.train()
Charizard.train()
Bulbasaur.train()
Squirtle.train()
print("--------------------------------")
print("所有神奇宝贝训练后详细情况")
Pikachu.detail()
Charizard.detail()
Bulbasaur.detail()
Squirtle.detail()
```
