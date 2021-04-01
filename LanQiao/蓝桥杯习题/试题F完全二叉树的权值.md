## 试题 G: 外卖店优先级
时间限制: 1.0s 内存限制: 256.0MB 本题总分：20 分

**【问题描述】**

“饱了么”外卖系统中维护着 N 家外卖店，编号 1 ∼ N。每家外卖店都有
一个优先级，初始时 (0 时刻) 优先级都为 0。
每经过 1 个时间单位，如果外卖店没有订单，则优先级会减少 1，最低减
到 0；而如果外卖店有订单，则优先级不减反加，每有一单优先级加 2。
如果某家外卖店某时刻优先级大于 5，则会被系统加入优先缓存中；如果
优先级小于等于 3，则会被清除出优先缓存。
给定 T 时刻以内的 M 条订单信息，请你计算 T 时刻时有多少外卖店在优
先缓存中。

**【输入格式】**

第一行包含 3 个整数 N、M 和 T。
以下 M 行每行包含两个整数 ts 和 id，表示 ts 时刻编号 id 的外卖店收到
一个订单。

**【输出格式】**

输出一个整数代表答案。


**【样例输入】**
```
2 6 6
1 1
5 2
3 1
6 2
2 1
6 2
```
**【样例输出】**
```
1
```
**【样例解释】**

6 时刻时，1 号店优先级降到 3，被移除出优先缓存；2 号店优先级升到 6，
加入优先缓存。所以是有 1 家店 (2 号) 在优先缓存中。

**【评测用例规模与约定】**

对于 80% 的评测用例，1 ≤ N, M, T ≤ 10000。
对于所有评测用例，1 ≤ N, M, T ≤ 100000，1 ≤ ts ≤ T，1 ≤ id ≤ N。


```
N,M,T=map(int,input().strip().split())
shop=[0 for i in range(N)]
message=[[] for i in range(T)]

for i in range(M):
    ts,id=map(int,input().strip().split())
    message[ts-1].append(id)
# message.sort(key=lambda x:x[0])
first=set()
for i in range(len(message)):
    for id in message[i]:
        shop[id-1]+=3
    for id in range(1,len(shop)):
        shop[id-1]-=1
        if shop[id-1]>5:
            first.add(shop[id-1])
        if shop[id-1]<=3:
            first=first-{shop[id-1]}
print(len(first))
            
        
            
```

    2 6 6
    1 1
    5 2
    3 1
    6 2
    2 1
    6 2
    1
