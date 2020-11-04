
# Leetcode同步练习（六）
## 目录
- 题目01：相同的树
- 题目02：对称二叉树
- 题目03：二叉树的最大深度
- 题目04： Pow(x, n)
- 题目05：子集
- 题目06：格雷编码
- 题目07：二叉树的最近公共祖先
- 题目08：二叉树的前序遍历
- 题目09：二叉树的中序遍历
- 题目10：二叉树的后序遍历

---
## 题目01：相同的树

> - 题号：100
> - 难度：简单
> - https://leetcode-cn.com/problems/same-tree/

给定两个二叉树，编写一个函数来检验它们是否相同。

如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。

**示例 1**:

```
输入:      1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]

输出: true
```

**示例 2**:

```
输入:      1          1
          /           \
         2             2

        [1,2],     [1,null,2]

输出: false
```

**示例 3**:
```
输入:      1         1
          / \       / \
         2   1     1   2

        [1,2,1],   [1,1,2]

输出: false
```

**参考代码：**

- 执行结果：通过
- 执行用时：160 ms, 在所有 C# 提交中击败了 5.85% 的用户
- 内存消耗：24 MB, 在所有 C# 提交中击败了 6.67% 的用户

```c
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int x) { val = x; }
 * }
 */
 
public class Solution
{
    public bool IsSameTree(TreeNode p, TreeNode q)
    {
        //递归终止条件
        if (p == null && q == null)
            return true;

        if (p != null && q != null && p.val == q.val)
        {
            return IsSameTree(p.left, q.left)
                    && IsSameTree(p.right, q.right);
        }
        return false;
    }
}
```

---
## 题目02：对称二叉树

> - 题号：101
> - 难度：简单
> - https://leetcode-cn.com/problems/symmetric-tree/

给定一个二叉树，检查它是否是镜像对称的。

例如，二叉树 `[1,2,2,3,4,4,3]` 是对称的。

```
    1
   / \
  2   2
 / \ / \
3  4 4  3
```

但是下面这个 `[1,2,2,null,3,null,3]` 则不是镜像对称的:

```
    1
   / \
  2   2
   \   \
   3    3
```

**参考代码：**

**第一种：采用递归的方法**

- 执行结果：通过
- 执行用时：132 ms, 在所有 C# 提交中击败了 16.67% 的用户
- 内存消耗：25.1 MB, 在所有 C# 提交中击败了 5.17% 的用户


```c
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int x) { val = x; }
 * }
 */
 
//镜像对称的递归函数
public class Solution
{
    public bool IsSymmetric(TreeNode root)
    {
        return IsMirror(root, root);
    }
    private bool IsMirror(TreeNode t1, TreeNode t2)
    {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
            && IsMirror(t1.left, t2.right)
            && IsMirror(t1.right, t2.left);
    }
}
```

**第二种：采用队列的方法**

**思路**：利用二叉树的层次遍历的方式来实现。

- 执行结果：通过
- 执行用时：112 ms, 在所有 C# 提交中击败了 70.93% 的用户
- 内存消耗：24.9 MB, 在所有 C# 提交中击败了 5.17% 的用户

```c
public class Solution
{
    public bool IsSymmetric(TreeNode root)
    {
        if (root == null)
            return true;

        Queue<TreeNode> nodes = new Queue<TreeNode>();
        nodes.Enqueue(root.left);
        nodes.Enqueue(root.right);
        while (nodes.Count != 0)
        {
            TreeNode node1 = nodes.Dequeue();
            TreeNode node2 = nodes.Dequeue();

            if (node1 == null && node2 == null)
                continue;
            if (node1 == null || node2 == null)
                return false;
            if (node1.val != node2.val)
                return false;
            nodes.Enqueue(node1.left);
            nodes.Enqueue(node2.right);
            nodes.Enqueue(node1.right);
            nodes.Enqueue(node2.left);
        }
        return true;
    }
}
```

---
## 题目03：二叉树的最大深度

> - 题号：104
> - 难度：简单
> - https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/


给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

说明: 叶子节点是指没有子节点的节点。

**示例**：

给定二叉树 [3,9,20,null,null,15,7]

```c
    3
   / \
  9  20
    /  \
   15   7
```

返回它的最大深度 3 。

**参考代码：**

**第一种：利用队列实现层次遍历的思路**

- 执行结果：通过
- 执行用时：108 ms, 在所有 C# 提交中击败了 88.13% 的用户
- 内存消耗：25.5 MB, 在所有 C# 提交中击败了 5.97% 的用户

```c
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int x) { val = x; }
 * }
 */
public class Solution
{
    public int MaxDepth(TreeNode root)
    {
        if (root == null)
            return 0;

        Queue<TreeNode> q = new Queue<TreeNode>();
        int deep = 0;
        q.Enqueue(root);

        while (q.Count != 0)
        {
            deep++;
            int count = 0;
            int size = q.Count;

            while (count < size)
            {
                TreeNode node = q.Dequeue();
                count++;

                if (node.left != null)
                    q.Enqueue(node.left);
                if (node.right != null)
                    q.Enqueue(node.right);
            }
        }
        return deep;
    }
}
```



**第二种：利用递归**

**思路**：递归分别求左右子树的最大深度，并加到原有层数上，最后返回两者中的最大值。

- 执行结果：通过
- 执行用时：132 ms, 在所有 C# 提交中击败了 16.62% 的用户
- 内存消耗：25.5 MB, 在所有 C# 提交中击败了 6.06% 的用户

```c
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int x) { val = x; }
 * }
 */
public class Solution
{
    public int MaxDepth(TreeNode root)
    {
        if (root == null)
            return 0;
        int llen = 1;
        int rlen = 1;
        if (root.left != null)
        {
            llen += MaxDepth(root.left);
        }
        if (root.right != null)
        {
            rlen += MaxDepth(root.right);
        }
        return llen > rlen ? llen : rlen;
    }
}
```
---
## 题目04： Pow(x, n)

> - 题号：50
> - 难度：中等
> - https://leetcode-cn.com/problems/powx-n/


实现 `pow(x, n)` ，即计算 x 的 n 次幂函数。

**示例 1:**

```c
输入: 2.00000, 10
输出: 1024.00000
```

**示例 2:**
```c
输入: 2.10000, 3
输出: 9.26100
```

**示例 3:**
```c
输入: 2.00000, -2
输出: 0.25000
解释: 2-2 = 1/22 = 1/4 = 0.25
```

**示例 4:**
```c
输入: 1.00000, -2147483648
输出: 1.00000
```

**说明:**

- -100.0 < x < 100.0
- n 是 32 位有符号整数，其数值范围是 [?2^31, 2^31 ? 1] 。


**思路**：利用快速幂法。

假设我们要求`a^b`，那么`b`可以拆成二进制表示，例如当`b = 5`时，5的二进制是0101，`5 = 2^3×0 + 2^2×1 + 2^1×0 + 2^0×1`，因此，我们将`a^5`转化为算 `a^(2^3×0 + 2^2×1 + 2^1×0 + 2^0×1)`，即`a^(2^0) × a^(2^2)`。


![](https://img-blog.csdnimg.cn/20200419235913493.png)

我们先算出所有2的幂，然后在算出所有x的2的幂次方。再把n拆成二进制，把二进制当中对应位置是1的值乘起来，就得到了结果。这套方法称为 **快速幂法**。


**参考代码**：

- 执行结果：通过
- 执行用时：56 ms, 在所有 C# 提交中击败了 51.87% 的用户
- 内存消耗：15.1 MB, 在所有 C# 提交中击败了 50.00% 的用户

```c
public class Solution
{
    public double MyPow(double x, int n)
    {
        int neg = n < 0 ? -1 : 1;
        long g = Math.Abs((long)n);

        double[] d = new double[32];
        d[0] = x;
        for (int i = 1; i < 32; i++)
        {
            d[i] = d[i - 1] * d[i - 1];
        }

        double result = 1.0d;
        for (int i = 0; i < 32; i++)
        {
            int mask = 1 << i;
            if ((mask & g) != 0)
            {
                result *= d[i];
            }
        }
        return neg != -1 ? result : 1.0 / result;
    }
}
```
---
## 题目05：子集

> - 题号：78
> - 难度：中等
> - https://leetcode-cn.com/problems/subsets/


给定一组**不含重复元素**的整数数组 nums，返回该数组所有可能的子集（幂集）。

<b>说明</b>：解集不能包含重复的子集。

<b>示例</b>:
```c
输入: nums = [1,2,3]
输出:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
```

**参考代码**：

**第一种：回溯法**

依次以nums[i]为启始点进行搜索，且后续搜索数值都要大于前一个数值，这样会避免重复搜索。

![回溯过程](https://img-blog.csdnimg.cn/20190913152245222.png)

- 状态：通过
- 行用时: 356 ms, 在所有 C# 提交中击败了 92.31% 的用户
- 内存消耗: 29.2 MB, 在所有 C# 提交中击败了 6.67% 的用户

```c
public class Solution
{
    private IList<IList<int>> _result;
    
    public IList<IList<int>> Subsets(int[] nums)
    {
        _result = new List<IList<int>>();
        int len = nums.Length;

        if (len == 0)
        {
            return _result;
        }
        IList<int> item = new List<int>();
        Find(nums, 0, item);
        return _result;
    }

    private void Find(int[] nums, int begin, IList<int> item)
    {
        // 注意：这里要 new 一下
        _result.Add(new List<int>(item)); 

        if (begin == nums.Length)
            return;

        for (int i = begin; i < nums.Length; i++)
        {
            item.Add(nums[i]);
            Find(nums, i + 1, item);

            // 组合问题，状态在递归完成后要重置
            item.RemoveAt(item.Count - 1); 
        }
    }
}
```

**第二种：子集扩展法**

- 状态：通过
- 执行用时: 352 ms, 在所有 C# 提交中击败了 94.51% 的用户
- 内存消耗: 29.2 MB, 在所有 C# 提交中击败了 6.67% 的用户

```c
public class Solution
{
    public IList<IList<int>> Subsets(int[] nums)
    {
        IList<IList<int>> result = new List<IList<int>>();
        IList<int> item = new List<int>();
        result.Add(item);
        for (int i = 0; i < nums.Length; i++)
        {
            for (int j = 0, len = result.Count; j < len; j++)
            {
                item = new List<int>(result[j]);
                item.Add(nums[i]);
                result.Add(item);
            }
        }
        return result;
    }
}
```

**第三种：位运算**

**思路：** 利用整数集合的思路。

以`{1,2,3}`为例，三个数，共`2^3`个子集。

```c
000 -> []
100 -> [1]
101 -> [1,3]
110 -> [1,2]
111 -> [1,2,3]
...
```

- 状态：通过
- 执行用时: 348 ms, 在所有 C# 提交中击败了 97.80% 的用户
- 内存消耗: 29.5 MB, 在所有 C# 提交中击败了 6.67% 的用户

```c
public class Solution
{
    public IList<IList<int>> Subsets(int[] nums)
    {
        IList<IList<int>> result = new List<IList<int>>();
        int count = nums.Length;

        for (int i = 0; i < 1 << count; i++)
        {
            IList<int> item = new List<int>();
            for (int j = 0; j < count; j++)
            {
                int mask = 1 << j;
                if ((mask & i) != 0)
                    item.Add(nums[j]);
            }
            result.Add(item);
        }
        return result;
    }
}
```

---
## 题目06：格雷编码

> - 题号：89
> - 难度：中等
> - https://leetcode-cn.com/problems/gray-code/

格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。

给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。

<b>示例 1</b>:
```c
输入: 2
输出: [0,1,3,2]
解释:
00 - 0
01 - 1
11 - 3
10 - 2

对于给定的 n，其格雷编码序列并不唯一。
例如，[0,2,3,1] 也是一个有效的格雷编码序列。

00 - 0
10 - 2
11 - 3
01 - 1
```

<b>示例 2</b>:
```c
输入: 0
输出: [0]
解释: 我们定义格雷编码序列必须以 0 开头。
    给定编码总位数为 n 的格雷编码序列，其长度为 2^n。
    当 n = 0 时，长度为 2^0 = 1。
    因此，当 n = 0 时，其格雷编码序列为 [0]。
```

**思路**：


![雷格码](https://img-blog.csdnimg.cn/20190915115931873.png)

由 n 位推导 n+1 位结果时，n+1 位结果包含 n 位结果，同时包含 n 位结果中在高位再增加一个位 1 所形成的令一半结果，但是这一半结果需要与前一半结果镜像排列。

**参考代码**：

- 状态：通过
- 12 / 12 个通过测试用例
- 执行用时: 296 ms, 在所有 C# 提交中击败了 95.83% 的用户
- 内存消耗: 24.8 MB, 在所有 C# 提交中击败了 16.67% 的用户

```c
public class Solution
{
    public IList<int> GrayCode(int n)
    {
        IList<int> lst = new List<int>();
        lst.Add(0);
        for (int i = 1; i <= n; i++)
        {
            for (int j = lst.Count - 1; j >= 0; j--)
            {
                int item = lst[j] + (1 << i - 1);
                lst.Add(item);
            }
        }
        return lst;
    }
}
```


---
## 题目07：二叉树的最近公共祖先



> - 题号：236
> - 难度：中等
> - https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/

给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（<b>一个节点也可以是它自己的祖先</b>）。”

例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4]

![](https://img-blog.csdnimg.cn/20190922095031349.png)

<b>示例 1</b>:
```c
输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出: 3
解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
```

<b>示例 2</b>:
```c
输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
输出: 5
解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
```

<b>说明</b>:

- 所有节点的值都是唯一的。
- p、q 为不同节点且均存在于给定的二叉树中。


**参考代码**：

**思路**：利用递归

- 状态：通过
- 执行用时: 132 ms, 在所有 C# 提交中击败了 96.10% 的用户
- 内存消耗: 27.5 MB, 在所有 C# 提交中击败了 20.00% 的用户

```c
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int x) { val = x; }
 * }
 */
 
public class Solution
{
    public TreeNode LowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
    {
        return Find(root, p, q);
    }

    private TreeNode Find(TreeNode current, TreeNode p, TreeNode q)
    {
        if (current == null || current == p || current == q)
            return current;
        TreeNode left = Find(current.left, p, q);
        TreeNode right = Find(current.right, p, q);
        if (left != null && right != null)
            return current;
        return left != null ? left : right;
    }
}
```



---
## 题目08：二叉树的前序遍历

> - 题号：144
> - 难度：中等
> - https://leetcode-cn.com/problems/binary-tree-preorder-traversal/


给定一个二叉树，返回它的 前序遍历。

**示例:**


```c
输入: [1,null,2,3]  
   1
    \
     2
    /
   3 

输出: [1,2,3]
```


进阶: 递归算法很简单，你可以通过迭代算法完成吗？


**参考代码**：

**第一种：利用栈**

- 执行结果：通过
- 执行用时：276 ms, 在所有 C# 提交中击败了 84.15% 的用户
- 内存消耗：29.9 MB, 在所有 C# 提交中击败了 5.00% 的用户

```c
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int x) { val = x; }
 * }
 */
public class Solution
{
    public IList<int> PreorderTraversal(TreeNode root)
    {
        IList<int> lst = new List<int>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (stack.Count != 0 || root != null)
        {
            if (root != null)
            {
                stack.Push(root);
                lst.Insert(lst.Count, root.val);
                root = root.left;
            }
            else
            {
                TreeNode node = stack.Pop();
                root = node.right;
            }
        }
        return lst;
    }
}
```

**第二种：利用递归**

- 执行结果：通过
- 执行用时：280 ms, 在所有 C# 提交中击败了 79.27% 的用户
- 内存消耗：29.8 MB, 在所有 C# 提交中击败了 5.00% 的用户

```c
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int x) { val = x; }
 * }
 */
public class Solution
{
    public IList<int> PreorderTraversal(TreeNode root)
    {
        IList<int> lst = new List<int>();
        PreOrder(root, lst);
        return lst;
    }
    private void PreOrder(TreeNode node, IList<int> lst)
    {
        if (node == null)
            return;

        lst.Add(node.val);
        PreOrder(node.left, lst);
        PreOrder(node.right, lst);
    }
}
```


---
## 题目09：二叉树的中序遍历

> - 题号：94
> - 难度：中等
> - https://leetcode-cn.com/problems/binary-tree-inorder-traversal/

给定一个二叉树，返回它的中序 遍历。

**示例:**

```c
输入: [1,null,2,3]
   1
    \
     2
    /
   3

输出: [1,3,2]
```

进阶: 递归算法很简单，你可以通过迭代算法完成吗？


**参考代码：**

**第一种：利用栈**

- 执行结果：通过
- 执行用时：284 ms, 在所有 C# 提交中击败了 53.59% 的用户
- 内存消耗：30 MB, 在所有 C# 提交中击败了 6.67% 的用户

```c
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int x) { val = x; }
 * }
 */
public class Solution
{
    public IList<int> InorderTraversal(TreeNode root)
    {
        IList<int> lst = new List<int>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (stack.Count != 0 || root != null)
        {
            if (root != null)
            {
                stack.Push(root);
                root = root.left;
            }
            else
            {
                TreeNode node = stack.Pop();
                lst.Add(node.val);
                root = node.right;
            }
        }
        return lst;
    }
}
```



**第二种：使用递归**

- 执行结果：通过
- 执行用时：264 ms, 在所有 C# 提交中击败了 99.16% 的用户
- 内存消耗：29.8 MB, 在所有 C# 提交中击败了 6.67% 的用户

```c
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int x) { val = x; }
 * }
 */
public class Solution
{
    public IList<int> InorderTraversal(TreeNode root)
    {
        IList<int> lst = new List<int>();
        MidOrder(root, lst);
        return lst;
    }
    private void MidOrder(TreeNode node, IList<int> lst)
    {
        if (node == null)
            return;
        MidOrder(node.left, lst);
        lst.Add(node.val);
        MidOrder(node.right, lst);
    }
}
```



---
## 题目10：二叉树的后序遍历

> - 题号：145
> - 难度：困难
> - https://leetcode-cn.com/problems/binary-tree-postorder-traversal/


给定一个二叉树，返回它的 后序 遍历。

**示例:**


```c
输入: [1,null,2,3]  
   1
    \
     2
    /
   3 

输出: [3,2,1]
```


进阶: 递归算法很简单，你可以通过迭代算法完成吗？


**参考代码**：


**第一种：利用栈**

前序遍历顺序为：根 -> 左 -> 右

后序遍历顺序为：左 -> 右 -> 根

如果1：我们将前序遍历中节点插入结果链表尾部的逻辑，修改为将节点插入结果链表的头部

那么结果链表就变为了：右 -> 左 -> 根

如果2：我们将遍历的顺序由从左到右修改为从右到左，配合如果1

那么结果链表就变为了：左 -> 右 -> 根

这刚好是后序遍历的顺序

基于这两个思路，我们处理方式如下：

第一：修改前序遍历代码中，节点写入结果链表的代码，将插入队尾修改为插入队首

第二：修改前序遍历代码中，每次先查看左节点再查看右节点的逻辑，变为先查看右节点再查看左节点

- 执行结果：通过
- 执行用时：272 ms, 在所有 C# 提交中击败了 98.15% 的用户
- 内存消耗：30 MB, 在所有 C# 提交中击败了 6.90% 的用户

```c
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int x) { val = x; }
 * }
 */
public class Solution
{
    public IList<int> PostorderTraversal(TreeNode root)
    {
        IList<int> lst = new List<int>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (stack.Count != 0 || root != null)
        {
            if (root != null)
            {
                stack.Push(root);
                lst.Insert(0, root.val);
                root = root.right;
            }
            else
            {
                TreeNode node = stack.Pop();
                root = node.left;
            }
        }
        return lst;
    }
}
```

**第二种：利用递归**

- 执行结果：通过
- 执行用时：276 ms, 在所有 C# 提交中击败了 89.81% 的用户
- 内存消耗：30 MB, 在所有 C# 提交中击败了 6.90% 的用户

```c
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int x) { val = x; }
 * }
 */
public class Solution
{
    public IList<int> PostorderTraversal(TreeNode root)
    {
        IList<int> lst = new List<int>();
        PostOrder(root, lst);
        return lst;
    }
    private void PostOrder(TreeNode node, IList<int> lst)
    {
        if (node == null)
            return;
            
        PostOrder(node.left, lst);
        PostOrder(node.right, lst);
        lst.Add(node.val);
    }
}
```


