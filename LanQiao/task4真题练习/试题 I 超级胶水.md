## 试题 I: 超级胶水

时间限制: 1.0s 内存限制: 256.0MB 本题总分：25 分

**【问题描述】**

小明有 n 颗石子，按顺序摆成一排。他准备用胶水将这些石子粘在一起。
每颗石子有自己的重量，如果将两颗石子粘在一起，将合并成一颗新的石
子，重量是这两颗石子的重量之和。
为了保证石子粘贴牢固，粘贴两颗石子所需要的胶水与两颗石子的重量乘
积成正比，本题不考虑物理单位，认为所需要的胶水在数值上等于两颗石子重
量的乘积。
每次合并，小明只能合并位置相邻的两颗石子，并将合并出的新石子放在
原来的位置。
现在，小明想用最少的胶水将所有石子粘在一起，请帮助小明计算最少需
要多少胶水。

**【输入格式】**

> 输入的第一行包含一个整数 n，表示初始时的石子数量。
>
> 第二行包含 n 个整数 w1,w2, · · · ,wn，依次表示每颗石子的重量。

**【输出格式】**

> 输出一行包含一个整数，表示最少需要的胶水数。

**【样例输入】**

```python
3
3 4 5
```

**【样例输出】**

```python
47
```

**【样例输入】**

```python
8
1 5 2 6 3 7 4 8
```

**【样例输出】**

```python
546
```

**【评测用例规模与约定】**

> 对于 20% 的评测用例，1 ≤ n ≤ 15。
>
> 对于 60% 的评测用例，1 ≤ n ≤ 100。
>
> 对于 80% 的评测用例，1 ≤ n ≤ 1000。
>
> 对于所有评测用例，1 ≤ n ≤ 100000，1 ≤ wi ≤ 1000。

参考答案：


```
n=int(input())
num_lis=list(map(int,input().strip().split()))
def f(num_lis):
    re=num_lis[0]*num_lis[1]
    re_index=0
    for i in range(len(num_lis)-1):
        if num_lis[i]*num_lis[i+1]<re:
            re= num_lis[i]*num_lis[i+1]
            re_index=i
    return re_index
res=0
while True:
    if len(num_lis)==1:
        break
    re_index=f(num_lis)
    
    if re_index+2<len(num_lis):
        res+=num_lis[re_index]*num_lis[re_index+1]
        num_lis=num_lis[:re_index]+[num_lis[re_index]+num_lis[re_index+1]]+num_lis[re_index+2:]
        
    else:
        res+=num_lis[re_index]*num_lis[re_index+1]
        num_lis=num_lis[:re_index]+[num_lis[re_index]+num_lis[re_index+1]]
        
print(res)
```

    8
    1 5 2 6 3 7 4 8
    546


























