# 1 基础 课后练习及补充

## 1.1 数据类型相关练习

### 1.1.1 数据类型转换

&emsp;&emsp;运行下面单元格，了解`int()`，`float()`，`str()`，`type()`等的用法



```{code-block} python
:linenos:

# str -> int
X = int('ABCD', 16)
print(X)
type(X)
```

```{code-block} python
:linenos:

# int -> float
a = 520
b = float(a)
b
```

```{code-block} python
:linenos:

# float -> str
a = 5.99
b = str(a)
b
```

&emsp;&emsp;接下来，请尝试将字符串`'520'`转化为小数，可以使用`float()`
```{code-block} python
:linenos:

a = '520'
b = 
```

&emsp;&emsp;运行下面单元格，了解字符串的切片操作
```{code-block} python
:linenos:

a_string = 'Hello' + ' ' + "Women Who Code!"
print(a_string)
print("str[0]  :" + a_string[0])
print("str[2:5]:" + a_string[2:5]) # Python speak: slicing
print("str[2:] :" + a_string[2:])
```

## 1.2 列表操作

&emsp;&emsp;列表是个框，什么都可以往里装

```{code-block} python
:linenos:

lis = [ "WWCode", 786 , 2.23, 'singapore', 70.2 ]
print(lis[0:3])
type(lis)
```

&emsp;&emsp;列表的索引，先猜猜下面这个单元格能得到什么，再运行

```{code-block} python
:linenos:

lis[0][2:]
```

&emsp;&emsp;如果我想从lis里得到'sing'应该怎么做呢

&emsp;&emsp;列表的元素是可以修改的
```{code-block} python
:linenos:

lis[2] = 3.3
lis
```
&emsp;&emsp;请尝试将列表最后一个元素更改为50

&emsp;&emsp;列表有一个非常好用的操作，叫list comprehension，可以运行下面单元格感受一下

```{code-block} python
:linenos:

symbols = '$¢£¥€¤'
codes = [ord(symbol) for symbol in symbols]
codes
```

&emsp;&emsp;`ord()`的作用是返回符号的unicode编码



## 1.3 元组操作

```{code-block} python
:linenos:

t1 = ( "WWCode", 100000 , 0.5 ) # org name, members, proportion of engineers
t2 = 'Singapore', 1160.5
t_singleton = ('We',) # singleton
t_empty = ()
print(type(t1)); print(type(t2))
print(t_singleton);
type(t_empty)
```

&emsp;&emsp;元组元素不可修改，尝试运行下列代码
```{code-block} python
:linenos:

t1[2] = 20
```

## 1.4 字典操作

```{code-block} python
:linenos:

dict1 = {'name':'Women Who Code Singapore', 
        'org':'WWCode', 
        'city':'Singapore',
        'members':1260}
print(dict1['org'])
type(dict1)
```

&emsp;&emsp;字典元素可以更改

&emsp;&emsp;增加元素

```python
# 增加元素
dict1['rank'] = 10
```



```python
# 获取元素
dict1['city']
```



```python
# 获取不知是否存在的元素
dict1.get('org','不存在')
```



```python
dict1.get('ord','不存在')
```



## 1.5 Sets 集合  

* Sets are a methematical concept, they are a lot like dictionaries with keys but no corresponding values. 
* 跟数学的概念很相似，类似于字典的键，但没有对应的值
* Sets are enclosed by curly braces, elements seperated by comma, '{','}'.  
* 用花括弧
* Sets do not support indexing or slicing, and do not have inherent order.  
* 不支持下标应用和切片





```python
wwcode_asia_networks = {'Bangalore','Beijing','Chennai','Delhi','Gujarat','Hong Kong','Kuala Lumpur','Manila','Pune','Rajasthan','Shanghai','Singapore','Taipei','Tel-Aviv','Tokyo'}
type(wwcode_asia_networks)
```



```python
print(wwcode_asia_networks)
```



```python
wwcode_asia_networks[1]
```



## 1.6 运算和布尔运算


```{code-block} python
:linenos:

x = 1 + 2 # Addition
y = 3 - 4 # Subtraction
z = 5 * 6 # Multiplication
a = z / y # Division
b = z % x # Modulus
c = y ** x # Exponent
d = c // x # Floor Division
print("x:" + str(x) + " y:" + str(y) + " z:" + str(z) + 
      " a:" + str(a) + " b:" + str(b) + " c:" + str(c) + " d:" + str(d))
```

运算符|	描述|	实例
-| :-: | -
=	|简单的赋值运算符	|c = a + b 将 a + b 的运算结果赋值为 c
+=	|加法赋值运算符	|c += a 等效于 c = c + a
-=	|减法赋值运算符	|c -= a 等效于 c = c - a
*=	|乘法赋值运算符	|c *= a 等效于 c = c * a
/=	|除法赋值运算符	|c /= a 等效于 c = c / a
%=	|取模赋值运算符	|c %= a 等效于 c = c % a
**=	|幂赋值运算符	|c \*\*= a 等效于 c = c \*\* a
//=	|取整除赋值运算符	|c //= a 等效于 c = c // a

&emsp;&emsp;布尔运算和比较运算

```{code-block} python
:linenos:

print(a == b) # equals
print(a != b) # not equals
print(a > b)  # greater than
print(a < b)  # lesser than
print(a >= b) # greater than or equal
print(a <= b) # lesser than or equal
```

&emsp;&emsp;Logical Operators 逻辑运算符     
&emsp;&emsp;and or not

```python
not True
```



```python
not 0
```



```python
not 4
```



```python
3 < 4 < 5
```



```python
3 < 4 < 2
```



```python
3 < 4 and 4 < 5
```



```python
3 < 5 or 8 > 5
```

```{code-block} python
:linenos:

a_string = "Women Who Code"
print("Women" in a_string)
print("Men" not in a_string)
print(len("Women Who Code") is len(a_string))
print(len("Hello World!") is not len(a_string))
```


```python
# 判断闰年怎么判断呢
year = 2100  # eval(input('输入年份（四位数）'))
# 大家来试试
```

&emsp;&emsp;is 和  is not 运算符 与==以及!=的区别

&emsp;&emsp;is 用于判断两个变量引用对象是否为同一个， == 用于判断引用变量的值是否相等。

```{code-block} python
:linenos:

x = 5
y = 5
print(x == y)
print(x is y)
print(id(x))
print(id(y))
```

```{code-block} python
:linenos:

help(id)
```

```{code-block} python
:linenos:

id(1)
```

```{code-block} python
:linenos:

id("abc")
```

```{code-block} python
:linenos:

id([1, 2, 3])
```

```{code-block} python
:linenos:

x = "abcabcabcabcabcabcabcabcabcabc"
y = "abcabcabcabcabcabcabcabcabcabc"
print(x == y)
print(x is y)
print(id(x))
print(id(y))
```

```{code-block} python
:linenos:

# 数组比较
x = [1, 2, 3]
y = [1, 2, 3]
print(x == y)
print(x is y)
print(id(x))
print(id(y))
```

```{code-block} python
:linenos:

# 元组比较
x = (1, 2, 3)
y = (1, 2, 3)
print(x == y)
print(x is y)
print(id(x))
print(id(y))
```

```{code-block} python
:linenos:

# 字典比较
x = {"id": 1, "name": "Tom", "age": 18}
y = {"id": 1, "name": "Tom", "age": 18}
print(x == y)
print(x is y)
print(id(x))
print(id(y))
```

```{code-block} python
:linenos:

# 集合比较
x = set([1, 2, 3])
y = set([1, 2, 3])
print(x == y)
print(x is y)
print(id(x))
print(id(y))
```

```{code-block} python
:linenos:

# 赋值后比较
x = [1, 2, 3]
y = x
print(x == y)
print(x is y)
print(id(x))
print(id(y))
```

空值比较
```{code-block} python
:linenos:

none_type = None
none_type is None
```

## 1.A 附录 一些可能会用到的知识
&emsp;&emsp;这部分内容在使用python的过程中可能会用得上，可以逐个运行单元格感受一下~

### 1.A.1 Reserve Words保留字
&emsp;&emsp;The following identifiers are used as reserved words of the language, and cannot be used as ordinary identifiers.
&emsp;&emsp;一些关键字是系统自带的保留字，即不能用作为标识符

```{code-block} python
:linenos:

import keyword
keyword.kwlist
```

### 1.A.2 build-in functions内置函数
&emsp;&emsp;这些函数不能作为变量名，可以作为函数直接调用，如：`print()` `input()`   
`dir(\__builtins__)`可以看到内置函数列表

```{code-block} python
:linenos:

dir(__builtins__)
```

```{code-block} python
:linenos:

aa = 3
aaaa = 5
dir()  #python内置函数；不带参数时，返回当前范围内的变量、方法和定义的类型列表
        #带参数是，返回参数的属性、方法列表
```

```{code-block} python
:linenos:

help(dir)
```

```{code-block} python
:linenos:

help(print)   # 内置函数，查看函数或模块用途的详细说明
```

&emsp;&emsp;Hit the **STOP** square button in the button ribbons/bar on top to continue to next cell.

```{code-block} python
:linenos:

#help(str)
import copy
help(copy.copy)
```



本篇整理：肖桐