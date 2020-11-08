本文结合各种实际的例子详细讲解了`Python`5个内建高阶函数的使用，能够帮助理解Python的数据结构和提高数据处理的效率，这5个函数分别是：

- map
- reduce
- filter
- sorted/sort
- zip

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0js8cz74j31ra0u01kx.jpg)

## 一、map

### 1.1 语法

`map`函数的基本语法是`map(func, seq)`，其含义指的是：对后面可迭代序列中的每个元素执行前面的函数`func`的功能，最终获取到一个新的序列。注意：

- `Python2` 中直接返回的是一个列表
- `Python3` 中返回的是一个可迭代器，**如果想返回列表，可以使用list()进行处理**


```python
help(map)  # 查看帮助信息
```

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0jxhlc7aj31100r2gp9.jpg)



### 1.2 demo

通过举例说明`map`函数的使用方法

1. 使用Python内置函数

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0k0yt70pj30py0aotah.jpg)

2. 使用自定义函数

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0k67ousyj30rg0cuabw.jpg)

3. 使用匿名函数lambda

使用匿名函数的时候可以有多个参数

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0k6wz0gkj30zs0hc41h.jpg)

## 二、reduce

### 2.1 语法

`reduce`函数的定义：

```python
reduce(function, sequence [, initial] ) -> value
```

`reduce`依次从`sequence`中取一个元素，和上一次调用`function`的结果做参数，再次调用`function`。


> 第一次调用function时，如果提供initial参数，会以sequence中的第一个元素和initial作为参数调用function，否则会以序列sequence的第一个数



### 2.2 使用

`Python3`中已将`reduce`函数移到`functools`模块中，需要先进行导入：


```python
from functools import reduce   # 导入
```


```python
help(reduce)  # 查看帮助文档
```

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0kbj0xctj312q0eu416.jpg)

上面的例子我们通过一个图形来解释说明：



![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0oo5ac3zj30fm0j8dgb.jpg)



### 2.3 demo

1. 使用自定义函数

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0meyl9v2j30rs07odgn.jpg)

2. 使用匿名函数lambda

![image-20201024185550970](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0mg1mhsxj30oe098dgr.jpg)

3. 一个复杂的例子

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0mh76t0fj30pu04w74p.jpg)

具体过程为：

```python
1. 1*2+1=3
2. 3*3+1=10  # 第一个3为上面的结果3，第2个原始数据中的3
3. 10*4+1=41
```



4. 带有初始值的例子

初始化值和序列中的第一个值执行func函数，将得到的结果作为下次的起始值

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0mi9sdj0j30xi070dh2.jpg)



```python
# 具体过程解释为

1. 6+1=7  
2. 7+2=9
3. 9+3=12
4. 12+4=16
5. 16+5=21
```



## 三、filter

### 3.1 语法

`filter()`函数用于过滤序列，过滤掉不符合条件的那些元素，返回符合条件的元素组成新列表。

序列中的每个元素作为参数传递给函数进行判断，返回True或者False，最后将返回True的元素放到新列表中。

`filter()`语法如下：

```python
filter(function, iterable)  # 前者为函数，后者为待执行的序列
```

### 3.2 demo


```python
help(filter)  # 帮助文档
```

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0mjqs5d4j31260su42k.jpg)



1. 使用自定义函数

**返回10以内的偶数**

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0ml7zrluj30ue0dkq5b.jpg)



2. 使用匿名函数lambda

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0mmni42uj30vu0aygnc.jpg)

3. 对字符串的筛选

选择符合指定要求的字符串

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0mn6huh1j312605gq3q.jpg)



## 四、sorted

### 4.1 语法

```python
sorted(iterable, key=None, reverse=False)  
```

### 4.2 3个参数

`sorted()`接受3个参数，**返回的是一个排序后的列表**

- 可迭代对象`iterable`

- `reverse=False`，接受一个布尔值，选择是否反转排序结果，默认是`False`

- 接受一个回调函数`key=None`，回调函数只能有一个参数，根据函数的返回值进行排序

### 4.3 demo


```python
help(sorted)  # 帮助文档
```

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0mpft86yj311u0aw0ul.jpg)

1. 默认不反转

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0mti6ac7j30ig0cwaba.jpg)



2. 对元组、range对象、字典的排序

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0muwcrfmj31560hgwhg.jpg)

### 4.4 结果反转

结果反转的意义就是**将结果降序排列**，因为原本默认是升序的，使用的是`reverse=True`

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0mwjbmo1j30mk060aak.jpg)



### 4.5 理解key

`key`参数的作用是我们自定义一个函数，然后通过将序列中的元素作用于函数之后再进行排序

在这里我们使用**绝对值函数**

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk50b7pqtrj30wg0eqq52.jpg)



![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk50bjqzhuj30vo0bwjtq.jpg)



### 4.6 对比sort()

sort()方法只能**对原列表list进行排序**，参数和sorted是相同的

**结果是将原来的列表直接原地修改，而sorted 是生成新的列表，二者是不同的**

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0n0re93hj30sw0hgq5j.jpg)

## 五、zip

`zip()`是`Python`中一个非常重要的方法，能够快速的实现很多功能。

### 5.1 语法

```python
zip([iterable,...])  # iterable是一个或者多个可迭代器
```

- 函数执行的结果在Python3中返回的是一个zip对象，如果需要展示成列表的形式，直接使用list方法展开；**展开的结果是列表中嵌套元组的形式**
- 在Python2中直接返回的是`元组列表`形式


```python
help(zip)  # 查看文档
```

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0n3cwwtbj310v0u0wjd.jpg)



### 5.2 zip接受一个序列

`zip`中可以接受**列表、元组、字符串**等形式

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0n83gvjij30uq0kktbv.jpg)



`zip`接受空列表的形式，返回的仍空列表

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0n8wtt6dj30kw080jru.jpg)



### 5.3 zip接受多个序列



![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0na7o94vj30qk0mkjtq.jpg)

同时对**不同类型的序列进行合并**

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0nd3nk5cj30uc0jcgo8.jpg)



### 5.4 处理长度不同

当多个序列同时存在，**取长度最小的那个序列的长度**

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0neeakshj31260iogoc.jpg)

### 5.5 zip(*iterables)

我们一般认为该方法是`zip`的反过程，是一个`unzip`的过程，举例说明其使用：

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0nfheymbj30rw0cemyq.jpg)



### 5.6 复杂例子

下面看一个更为复杂的例子

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0ngg0ef2j30my04ugm1.jpg)



这个例子的解释为：

1. `[x]`是一个列表中含有列表，`x`本身就是一个列表
2. `[x]*3`结果为`[x,x,x]`，实际上也是`[[4,5,6],[4,5,6],[4,5,6]]`
3. `[*[x]*3]`的结果则为`[(4,4,4),(5,5,5),(6,6,6)]`



### 5.7 zip运用

下面通过`zip`的实际例子来说明它的应用：

1. 列表求和

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0nhvr5e5j30my0bqwfb.jpg)

2. 数据合并

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0niilhtbj30vu0buta9.jpg)

3. 字典的key-value转换

`for`循环实现：

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk0njmtckzj30yy08ewft.jpg)

使用`zip`实现：

![](https://tva1.sinaimg.cn/large/0081Kckwgy1gk50a6r2zrj30z00d4419.jpg)

