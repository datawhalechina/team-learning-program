## 试题 I: BST 插入节点问题

时间限制: 1.0s 内存限制: 512.0MB 本题总分：25 分

**【问题描述】**

给定一棵包含 N 个节点的二叉树，节点编号是 1 ∼ N。其中 i 号节点具有
权值 Wi，并且这些节点的权值恰好形成了一棵排序二叉树 (BST)。
现在给定一个节点编号 K，小明想知道，在这 N 个权值以外，有多少个整
数 X (即 X 不等于任何 Wi ) 满足：给编号为 K 的节点增加一个权值为 X 的子
节点，仍可以得到一棵 BST。
例如在下图中，括号外的数字表示编号、括号内的数字表示权值。即编号
1 ∼ 4 的节点权值依次是 0、10、20、30。

如果 K = 1，那么答案为 0。因为 1 号节点已经有左右子节点，不能再增
加子节点了。
如果 K = 2，那么答案为无穷多。因为任何一个负数都可以作为 2 的左子
节点。
如果 K = 3，那么答案为 9。因为 X = 11, 12, · · · , 19 都可以作为 3 的左子
节点。

**【输入格式】**

> 第一行包含 2 个整数 N 和 K。
>
> 以下 N 行每行包含 2 个整数，其中第 i 行是编号为 i 的节点的父节点编号
>
> Pi 和权值 Wi 。注意 Pi = 0 表示 i 是根节点。
>
> 输入保证是一棵 BST。

**【输出格式】**

> 一个整数代表答案。如果答案是无穷多，输出 −1。

**【样例输入】**

```python
4 3
0 10
1 0
1 20
3 30
```

**【样例输出】**

```python
9
```

**【评测用例规模与约定】**

> 对于 60% 的评测用例，1 ≤ K ≤ N ≤ 100，0 ≤ Wi ≤ 200，且 Wi 各不相同。
>
> 对于所有评测用例，1 ≤ K ≤ N ≤ 10000，0 ≤ Wi ≤ 100000000，且 Wi 各不 相同。



```
class TreeNode:
    def __init__(self,x):
        self.val=x
        self.left=None
        self.right=None
        self.parent=None
N,K=map(int,input().strip().split())
map_=[]
map_node=[]
for i in range(N):
    p,w=map(int,input().strip().split())
    map_.append([p,w])
    map_node.append(TreeNode(w))
    if p==0:
        root_index=i
for i in range(N):
    if map_node[map_[i][0]-1].val<map_node[i].val:
        map_node[map_[i][0]-1].right=map_node[i]
        map_node[i].parent=map_node[map_[i][0]-1]
    else:
        map_node[map_[i][0]-1].left=map_node[i]
        map_node[i].parent=map_node[map_[i][0]-1]
if map_node[K-1].left and map_node[K-1].right:
    print(0)
elif map_node[K-1].right:
    if not map_node[K-1].parent:
        print(-1)
    else:
        print(map_node[K-1].val-map_node[K-1].parent.val-1)
elif map_node[K-1].left:
    print(-1)
    

    
```

    4 3
    0 10
    1 0
    1 20
    3 30
    9
