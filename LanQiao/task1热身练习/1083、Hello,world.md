## 题目 1083: Hello, world!

时间限制: 1Sec 内存限制: 64MB 提交: 10817 解决: 5250

提交地址：

https://www.dotcpp.com/oj/problem1083.html

**题目描述**

这是要测试的第一个问题。 由于我们都知道ASCII码，因此您的工作很简单：输入数字并输出相应的消息。
**输入**

> 输入将包含一个由空格（空格，换行符，TAB）分隔的正整数列表。 请处理到文件末尾（EOF）。 整数将不少于32。

**输出**

> 输出相应的消息。 请注意，输出末尾没有换行符。

**样例输入**

```python
72 101 108 108 111 44
32 119 111 114 108 100 33
```

**样例输出**

```python
Hello, world!
```


```
while True:
    num=list(map(int,input().strip().split()))
    for i in num:
        print(chr(i),end='')
```

    72 101 108 108 111 44 32 119 111 114 108 100 33
    Hello, world!


```
num2=[2,2,2]
num=num2.copy()
for i in range(len(num)):
    num[i]=int(num[i]/2)
print(num2)
```

    [2, 2, 2]
