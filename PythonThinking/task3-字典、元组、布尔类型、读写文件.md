# 2 基础（续）

## 2.5 字典

&emsp;&emsp;现在你要学习 Python 中的另一种数据结构——字典（Dictionary）。字典（也叫 dict）是一种和列表类似的数据存储方式。但是不同于列表只能用数字获取数据，字典可以用任何东西来获取。你可以把字典当成是一个存储和组织数据的数据库。

&emsp;&emsp;让我们比较一下列表和字典的作用。你看，列表可以让你做这些事情：

```shell
>>> things = ['a', 'b', 'c', 'd']
>>> print(things[1])
b
>>> things[1] = 'z'
>>> print(things[1])
z
>>> things
['a', 'z', 'c', 'd']
```

&emsp;&emsp;你可以用数字来索引列表，找到列表里面有些什么。到现在你应该能够理解这一点。但是你还要确保自己明白，你只能用数字来取出列表中的元素。

&emsp;&emsp;相比之下，字典能让你用几乎所有的东西，而不只是数字。是的，字典能够把一个东西和另一个东西关联起来，不管它们是什么类型。我们来看看：

```shell
>>> stuff = {'name': 'Zed', 'age': 39, 'height': 6 * 12 + 2}
>>> print(stuff['name'])
Zed
>>> print(stuff['age'])
39
>>> print(stuff['height'])
74
>>> stuff['city'] = "SF"
>>> print(stuff['city'])
SF
```

&emsp;&emsp;你会看到我们用了字符串（而不是数字）来从 stuff 字典中取出了我们想要的东西。我们也可以用字符串来给字典添加新的东西。而且，也可以不用字符串，我们可以这样做：

```shell
>>> stuff[1] = "Wow"
>>> stuff[2] = "Neato"
>>> print(stuff[1])
Wow
>>> print(stuff[2])
Neato
```

&emsp;&emsp;在这一段代码中我用了数字，所以你看，我在打印字典的时候既可以用数字也可以用字符串来作为键。我可以用任何东西。好吧，大多数东西，不过你现在就假装能够用任何东西吧。

&emsp;&emsp;当然，如果一个字典只能放东西那就太蠢了。下面是如何用`del`关键词来删除其中的东西：

```shell
>>> del stuff['city']
>>> del stuff[1]
>>> del stuff[2]
>>> stuff
{'name': 'Zed', 'age': 39, 'height': 74}
```
### 2.5.1 一个字典示例

&emsp;&emsp;接下来我们要做一个练习，你必须非常仔细，我要求你将这个练习写下来，然后试着弄懂它做了些什么。当你把东西放进字典、随意取出、以及做其他操作的时候记得做一下笔记。

&emsp;&emsp;注意一下这个例子是如何把美国的州名和它们的缩写以及州的缩写和城市映射（mapping）起来的，记住，“映射”或者说“关联”（associate）是字典的核心理念。

```{code-block} python
:linenos:

# create a mapping of state to abbreviation
states = {
    'Oregon': 'OR',
    'Florida': 'FL',
    'California': 'CA',
    'New York': 'NY',
    'Michigan': 'MI'
}

# create a basic set of states and some cities in them
cities = {
   'CA': 'San Francisco',
   'MI': 'Detroit',
   'FL': 'Jacksonville'
}

# add some more cities
cities['NY'] = 'New York'
cities['OR'] = 'Portland'

# print out some cities
print('-' * 10)
print("NY State has: ", cities['NY'])
print("OR State has: ", cities['OR'])

# print some states
print('-' * 10)
print("Michigan's abbreviation is: ", states['Michigan'])
print("Florida's abbreviation is: ", states['Florida'])

# do it by using the state then cities dict
print('-' * 10)
print("Michigan has: ", cities[states['Michigan']])
print("Florida has: ", cities[states['Florida']])

# print every state abbreviation
print('-' * 10)
for state, abbrev in list(states.items()):
    print(f"{state} is abbreviated {abbrev}")

# print every city in state
print('-' * 10)
for abbrev, city in list(cities.items()):
    print(f"{abbrev} has the city {city}")

# now do both at the same time
print('-' * 10)
for state, abbrev in list(states.items()):
    print(f"{state} state is abbreviated {abbrev}")
    print(f"and has city {cities[abbrev]}")

print('-' * 10)
# safely get a abbreviation by state that might not be there
state = states.get('Texas')

if not state:
    print("Sorry, no Texas.")

# get a city with a default value
city = cities.get('TX', 'Does Not Exist')
print(f"The city for the state 'TX' is: {city}")
```

```{admonition} 附加练习
:class: tip

1. 完成一个小练习，尝试自己写一个中国省份与省份缩写对应的字典代码
```

## 2.6 元组

&emsp;&emsp;元组是另一个数据类型，类似于 List（列表）。 元组用`()`标识。内部元素用逗号隔开。但是元组不能二次赋值，相当于只读列表。

```python
tuple = ( 'runoob', 786 , 2.23, 'john', 70.2 )
tinytuple = (123, 'john')
 
print(tuple)               # 输出完整元组
print(tuple[0])             # 输出元组的第一个元素
print(tuple[1:3])           # 输出第二个至第四个（不包含）的元素 
print(tuple[2:])            # 输出从第三个开始至列表末尾的所有元素
print(tinytuple * 2)        # 输出元组两次
print(tuple + tinytuple)    # 打印组合的元组
```

&emsp;&emsp;以下是元组无效的，因为元组是不允许更新的。而列表是允许更新的：

```python
tuple = ( 'runoob', 786 , 2.23, 'john', 70.2 )
list = [ 'runoob', 786 , 2.23, 'john', 70.2 ]
tuple[2] = 1000    # 元组中是非法应用
list[2] = 1000     # 列表中是合法应用
```

## 2.7 布尔类型

&emsp;&emsp;这个部分相信大家已经很熟悉，在高中和大学数学中经常出现的。在python表述的语法是：

- and
- or
- not
- `!=` （不等于）
- `==` （等于）
- `>=` （大于等于）
- `<=` （小于等于）
- True
- False

&emsp;&emsp;在这个练习中，你将试着在 Python 中运用逻辑表示。给以下每一个逻辑问题写下你认为的答案，要么是 True，要么是 False。等你把答案写下来，再在终端里运行 Python，输入每个逻辑问题，来确认你的答案是否正确。

```{code-block} python
:linenos:

True and True
False and True
1 == 1 and 2 == 1
"test" == "test"
1 == 1 or 2 != 1
True and 1 == 1
False and 0 != 0
True or 1 == 1
"test" == "testing"
1 != 0 and 2 == 1
"test" != "testing"
"test" == 1
not (True and False)
not (1 == 1 and 0 != 1)
not (10 == 1 or 1000 == 1000)
not (1 != 10 or 3 == 4)
not ("testing" == "testing" and "Zed" == "Cool Guy")
1 == 1 and (not ("testing" == 1 or 1 == 0))
"chunky" == "bacon" and (not (3 == 4 or 3 == 3))
3 == 3 and (not ("testing" == "testing" or "Python" == "Fun"))
```

&emsp;&emsp;在你尝试给出所有答案后，这是你可能会在 Python 运行后看到的运行结果：

```shell
Python 2.5.1 (r251:54863, Feb 6 2009, 19:02:12)
[GCC 4.0.1 ( Apple Inc . build 5465)] on darwin
Type "help" , "copyright" , "credits" or "license" for more information
>>> True and True
True
>>> 1 == 1 and 2 == 2
True
```

## 2.8 读写文件

### 2.8.1 读写文件的基本操作

- **close** - 关闭文件，就像编辑器中的 “文件->另存为”一样。
- **read** - 读取文件内容。你可以把读取结果赋给一个变量。
- **readline** - 只读取文本文件的一行内容。
- **truncate** - 清空文件。清空的时候要当心。
- **write('stuff')** - 给文件写入一些“东西”。
- **seek(0)** - 把读/写的位置移到文件最开头。

&emsp;&emsp;这些都是你需要知道的一些非常重要的命令。其中一些要用到参数，但是我们暂且不去重点关注。你只需要记住`write`命令需要你提供一个你要写入的文件的字符串参数。

&emsp;&emsp;让我们用这些命令做一个小小的编辑器：

```python
#!/usr/bin/python
# -*- coding:utf-8 -*-
file = open('d:/b.txt',mode = 'w')    #  open('路径和文件名,模式= 'XX')这个格式是打开一个文件，如果没有会自动创建；路径可以修改为自己需要的路径，线上的编辑器需要改为线上的路径名
file.write('你好，\n 世界。')
file.close()

target = open('d:/b.txt',mode = 'w')

print("Truncating the file. Goodbye!")
target.truncate()                   # 这行命令会把之前的输入都清空

print("Now I'm going to ask you for three lines.")

line1 = input("line 1: ") #  运行后输入 Mary had a little lamb
line2 = input("line 2: ") #  运行后输入 Its fleece was white as snow
line3 = input("line 3: ") #  运行后输入 It was also tasty

print("I'm going to write these to the file.")

target.write(line1)
target.write("\n")
target.write(line2)
target.write("\n")
target.write(line3)
target.write("\n")

print("And finally, we close it.")
target.close()
```

&emsp;&emsp;这真是一个很大的文件，可能是你输入过的最大的文件了。所以慢一点，写完检查一下，然后再运行。你也可以写一点运行一点，比如先运行 1-8 行，然后再多运行 5 行，然后再多几行，直到所有的都完成和运行了。

&emsp;&emsp;事实上你应该看到两样东西，首先是你新脚本的输出结果：

```text
Truncating the file. Goodbye!
Now I'm going to ask you for three lines.
line 1: Mary had a little lamb
line 2: Its fleece was white as snow
line 3: It was also tasty
I'm going to write these to the file.
And finally, we close it.
```

&emsp;&emsp;现在，用编辑器打开你创建的文件（比如我的是 test.txt），检查一下是不是对的。

```{admonition} 附加练习
:class: tip

1. 如果你理解不了这个练习，回过头去按照给每行加注释的方法再过一遍，注释能帮助你理解每一行的意思，至少让你知道你不理解的地方在哪里，然后动手去查找答案。
2. 写一个类似于上述读取文件的练习代码，使用`read`和`argv`来读取你刚刚创建的文件。
3. 这个练习中有太多的重复，试着用一个`target.write()`命令来打印 line1、line2、line3，你可以使用字符串、格式字符串和转义字符。
4. 弄明白为什么我们要用一个`w`作为一个额外的参数来打开。提示：通过明确说明你想要写入一个文件，来安全地打开它。
5. 如果你用`w`模式打开文件，那你还需要`target.truncate()`吗？读一读 Python 的 open 函数文件，来搞明白这个问题。
```

### 2.8.2 常见问题

1. `truncate()` 对于 `w` 参数来说是必须的吗？
   
&emsp;&emsp;**答：** 详见附加练习5。

2. `w` 到底是什么意思？
   
&emsp;&emsp;**答：** 它真的只是一个有字符的字符串，来表示文件的一种模式。如果你用了`w` ，就代表用`write`模式打开这个文件。此外还有`r`表示 read 模式，`a`表示增补模式，后面还可能加一些修饰符（modifiers）。

3. 我能对文件使用哪些修饰符？ 
   
&emsp;&emsp;**答：** 目前最重要的一个就是 `+` ，你可以用`w+`、`r+`以及`a+`。这样会让文件以读和写的模式打开，取决于你用的是那个符号以及文件所在的位置等。

4. 如果只输入 `open(filename)` 是不是就用 `r` （读）模式打开？ 

&emsp;&emsp;**答：** 是的，那是`open()`函数的默认值。

### 2.8.3 读写文件方法与经验总结

```python
my_text = "今天又是一周的开始，打起精神认真赚钱不含参"

with open("path", "w", encoding="utf-8") as f:
    f.write(my_text)
```

1. 写入数据的时候最好是指定一下文件的编码方式，不然下次读取的时候可能就读取不了（出现乱码）。经常遇到的问题是中文的保存，会以各种各样的编码方式保存。但其实在读取的时候选择对应的编码方式仍旧可以打开但直接保存为`utf-8`一劳永逸。特别是使用pandas将含有中文数据的DataFrame对象保存为csv文件的时候，有一个具体的编码方式需要指定。
2. 其次如果是爬取的图片类型的数据，此时的写入方式就需要更改为二进制方式保存，也是之前困扰过的问题。
3. 如果爬取的文本中含有`emoji`而且准备写入MySQL中，需要更改MySQL数据库的编码方式为`utf-8mb4`格式，不然会出现问题。MySQL数据库的版本也有要求具体5.10以上应该都可以。
4. 数据的存写最好是使用`with open() as f`这种形式，上下文管理器会自动帮你读取完数据后关闭文件。不然在报错后寻找问题的原因真的很麻烦，一个小问题可能要找很久也找不到（真实经历）。
5. 如果遇到一个不确定是什么编码方式的文档，最好的方式是以`txt`方式打开该文件，可以看到文件的编码方式，此时也可以另存为的时候将编码方式改为自己需要的合适的编码方式。
6. 大文件的读写很浪费时间，而且对于内存的负担也很大。此时可以对数据进行分页读取比较合适，内存毕竟有限。`pickle`和`json`可以将数据存储为二进制文件和通用的json格式文件。可能需要注意的地方就是`dump`和`dunps`以及`load`和`loads`的区别。这个真的是一直没注意，每次都随机试，反正只最多需要试两下就可以，多试几次就知道了。`pickle`保存的`.pkl`文件内存小，读取快。`json`文件的引号也需要注意一下。还有就是`json`文件`load`的时候如果数据有问题就直接写正则对数据做拆分，避免所有数据都被丢失。之前遇到过`json`文件保存明明可以，但是读取就是失败的问题。后来发现是有一些字符编码有问题，会提示具体报错的代码位置，可以自行定位并一点一点的排除。网上有一个解决思路是使用一个参数，对解码进行相关的设置就可以了，但并不是万能的，至少没解决我之前遇到的问题。
7. 数据的写入读取方式：只读、只写、覆盖写等这些容易忽略的问题，幸幸苦苦将数据保存后，出现了问题，然后发现结果不对劲。回头一看才发现是数据的写入有问题，这样就很消磨热情以及浪费时间。

#### 解决问题的思路

&emsp;&emsp;我不是计算机专业的，周围认识的人也没有学编程的。因此每当我遇到问题的时候就借助`baidu`、`csdn`、博客园这些。现在最主要还是谷歌要用的多，百度最差，其次`csdn`总是跳来跳去也找不到答案，博客园如果有答案的话内容感觉还可以，在`csdn`上挑来挑去，最终没找到解决办法很烦人，不过也好用。谷歌报错代码挺方便的，容易检索出来比较相似的问题。

&emsp;&emsp;更多的时候自己多试几下就能发现问题的原因，记得也更清楚一些。而且，每当一个困扰很久的问题解决后的那种成就感真的很爽，有一种如释负重的感觉。






