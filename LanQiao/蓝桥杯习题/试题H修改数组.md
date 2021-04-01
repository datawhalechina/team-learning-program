## 试题 H: 修改数组

时间限制: 1.0s 内存限制: 256.0MB 本题总分：20 分


**【问题描述】**

给定一个长度为 N 的数组 A = [A1, A2, · · · AN]，数组中有可能有重复出现
的整数。
现在小明要按以下方法将其修改为没有重复整数的数组。小明会依次修改
A2, A3, · · · , AN。
当修改 Ai 时，小明会检查 Ai 是否在 A1 ∼ Ai−1 中出现过。如果出现过，则
小明会给 Ai 加上 1 ；如果新的 Ai 仍在之前出现过，小明会持续给 Ai 加 1 ，直
到 Ai 没有在 A1 ∼ Ai−1 中出现过。
当 AN 也经过上述修改之后，显然 A 数组中就没有重复的整数了。
现在给定初始的 A 数组，请你计算出最终的 A 数组。

**【输入格式】**

第一行包含一个整数 N。
第二行包含 N 个整数 A1, A2, · · · , AN 。

**【输出格式】**

输出 N 个整数，依次是最终的 A1, A2, · · · , AN。

**【样例输入】**

- 5
- 2 1 1 3 4

**【样例输出】**

- 2 1 3 4 5

**【评测用例规模与约定】**

- 对于 80% 的评测用例，1 ≤ N ≤ 10000。
- 对于所有评测用例，1 ≤ N ≤ 100000，1 ≤ Ai ≤ 1000000。


```
N=int(input())
nums=list(map(int,input().strip().split()))
visited=set()
for i in range(len(nums)):
    while  nums[i] in visited:
        nums[i]+=1
    visited.add(nums[i])
nums=list(map(str,nums))
print(' '.join(nums))
```

    5
    2 1 1 3 4
    2 1 3 4 5



```
a=[{2},{3},set(),set()]
a.remove(set())
print(a)
```

    [{2}, {3}, set()]



```
help(list.remove)
```

    Help on method_descriptor:
    
    remove(...)
        L.remove(value) -> None -- remove first occurrence of value.
        Raises ValueError if the value is not present.


​    


```
[{3}-{3},set(),{4}].remove({4})
# a=[{3}-{3},set(),{4}].remove({3}-{3})
# print(a)
```


```
N,M,k=map(int,input().strip().split())
queue=[]
for i in range(N):
    candy1,candy2,candy3=map(int,input().strip().split())
    queue.append({candy1,candy2,candy3})
re={}
all=set([ i for i in range(1,M+1)])

while all!=re and queue:
    temp=max(queue,key=len)
    re|=temp
#     queue.remove()
    remove_count=0
    for i in queue:
        
```