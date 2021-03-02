## 试题 H: 整数小拼接

时间限制: 1.0s 内存限制: 256.0MB 本题总分：20 分
**【问题描述】**
给定义个长度为 n 的数组 A1, A2, · · · , An。你可以从中选出两个数 Ai 和 Aj
(i 不等于 j)，然后将 Ai 和 Aj 一前一后拼成一个新的整数。例如 12 和 345 可
以拼成 12345 或 34512 。注意交换 Ai 和 Aj 的顺序总是被视为 2 种拼法，即便
是 Ai = Aj 时。
请你计算有多少种拼法满足拼出的整数小于等于 K。
**【输入格式】**

> 第一行包含 2 个整数 n 和 K。
> 第二行包含 n 个整数 A1, A2, · · · , An。

**【输出格式】**

> 一个整数代表答案。

**【样例输入】**

```python
4 33
1 2 3 4
```

**【样例输出】**

```python
8
```

**【评测用例规模与约定】**
对于 30% 的评测用例，1 ≤ N ≤ 1000, 1 ≤ K ≤ 108, 1 ≤ Ai ≤ 104。
对于所有评测用例，1 ≤ N ≤ 100000，1 ≤ K ≤ 1010，1 ≤ Ai ≤ 109.



```
n,K=map(int,input().strip().split())
num_lis=list(map(int,input().strip().split()))
re=0
for i in range(n):
    for j in range(i+1,n):
        if (num_lis[i]*10+num_lis[j])<=K:
#             print( num_lis[i]*10+num_lis[j])
            re+=1
        if (num_lis[j]*10+num_lis[i])<=K:
#             print(num_lis[j]*10+num_lis[i])
            re+=1
print(re)
        
        
```

    4 33
    1 2 3 4
    8
