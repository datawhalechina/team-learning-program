## 试题 G: 走方格

时间限制: 1.0s 内存限制: 256.0MB 本题总分：20 分

**【问题描述】**
在平面上有一些二维的点阵。
这些点的编号就像二维数组的编号一样，从上到下依次为第 1 至第 n 行，
从左到右依次为第 1 至第 m 列，每一个点可以用行号和列号来表示。
现在有个人站在第 1 行第 1 列，要走到第 n 行第 m 列。只能向右或者向下
走。
注意，如果行号和列数都是偶数，不能走入这一格中。
问有多少种方案。

**【输入格式】**

> 输入一行包含两个整数 n, m。

**【输出格式】**

> 输出一个整数，表示答案。

**【样例输入】**

```python
3 4
```

**【样例输出】**

```python
2
```

**【样例输入】**

```python
6 6
```




```
n,m=map(int,input().strip().split())
def main():
    re=[0]
    def dfs(x,y):
        if x==n-1 and y==m-1:
            re[0]+=1
        if 0<=x+1<=(n-1) and not ((x+1)%2==0 and (y)%2==0):
            dfs(x+1,y)
        if 0<=y+1<=(m-1) and not ((x)%2==0 and (y+1)%2==0):
            dfs(x,y+1)
    dfs(0,0)
    print(re[0])
main()
```

    3 4
    2


### bfs


```
def main():
    n,m=map(int,input().strip().split())
    queue=[(0,0)]
    re=0
    while queue:
        x,y=queue.pop(0)
        if x==n-1 and y==m-1:
            re+=1
        if 0<=x+1<n and 0<=y<m and not ((x+1+1)%2==0 and (y+1)%2==0):
            queue.append((x+1,y))
        if 0<=x<n and 0<=y+1<m and not ((x+1)%2==0 and (y+1+1)%2==0):
            queue.append((x,y+1))
    print(re)
    return 
main()
```

    3 4
    2


### dfs


```
def main():
    n,m=map(int,input().strip().split())
    queue=[(0,0)]
    re=[0]
    def dfs(x,y):
        if x==n-1 and y==m-1:
            re[0]+=1
        if 0<=x+1<n and 0<=y<m and not ((x+1+1)%2==0 and (y+1)%2==0):
            dfs(x+1,y)
        if 0<=x<n and 0<=y+1<m and not ((x+1)%2==0 and (y+1+1)%2==0):
            dfs(x,y+1)
    dfs(0,0)
    print(re[0])
    return 
main()
```

    4 3
    2
