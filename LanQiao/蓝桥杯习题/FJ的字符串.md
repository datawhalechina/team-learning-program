FJ的字符串

提交此题   
资源限制
时间限制：1.0s   内存限制：512.0MB

**问题描述**
```
　　FJ在沙盘上写了这样一些字符串：
　　A1 = “A”
　　A2 = “ABA”
　　A3 = “ABACABA”
　　A4 = “ABACABADABACABA”
　　… …
　　你能找出其中的规律并写所有的数列AN吗？
```
**输入格式**

- 仅有一个数：N ≤ 26。

**输出格式**

- 　　请输出相应的字符串AN，以一个换行符结束。输出中不得含有多余的空格或换行、回车符。

**样例输入**
```
3
```
**样例输出**
```
ABACABA
```


```
chr(65)
```




    'A'




```
N=int(input())
def op(n,s):
    if n==N+65-1:
        print (s+chr(n)+s)
        return
    op(n+1,s+chr(n)+s)
op(65,"")
```

    3
    ABACABA
