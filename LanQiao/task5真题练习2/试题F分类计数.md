## 试题 F: 分类计数

时间限制: 1.0s 内存限制: 512.0MB 本题总分：15 分

**【问题描述】**

输入一个字符串，请输出这个字符串包含多少个大写字母，多少个小写字
母，多少个数字。

**【输入格式】**

> 输入一行包含一个字符串。

**【输出格式】**

> 输出三行，每行一个整数，分别表示大写字母、小写字母和数字的个数。

**【样例输入】**

```python
1+a=Aab
```

**【样例输出】**

```python
1
3
1
```

**【评测用例规模与约定】**
对于所有评测用例，字符串由可见字符组成，长度不超过 100。

参考答案：


```
string=input()
num_set=set()
for i in range(10):
    num_set.add(str(i))
# print(num_set)
num_len=0
h_len=0
l_len=0
for i in string:
    if i in num_set:
        num_len+=1
    elif 'A'<=i<='Z':
        h_len+=1
    elif 'a'<=i<='z':
        l_len+=1
print(h_len)
print(l_len)
print(num_len)
        
```

    1+a=Aab
    1
    3
    1
