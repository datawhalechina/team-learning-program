
# Leetcode同步练习（三）


## 题目01：合并两个有序链表

> - 题号：21
> - 难度：简单
> - https://leetcode-cn.com/problems/merge-two-sorted-lists/

将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

**示例：**

```c
输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
```

**参考代码**：

- 执行结果：通过
- 执行用时：108 ms, 在所有 C# 提交中击败了 83.80% 的用户
- 内存消耗：25.9 MB, 在所有 C# 提交中击败了 5.85% 的用户

```c
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int x) { val = x; }
 * }
 */
public class Solution
{
    public ListNode MergeTwoLists(ListNode l1, ListNode l2)
    {
        ListNode pHead = new ListNode(int.MaxValue);
        ListNode temp = pHead;

        while (l1 != null && l2 != null)
        {
            if (l1.val < l2.val)
            {
                temp.next = l1;
                l1 = l1.next;
            }
            else
            {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }

        if (l1 != null)
            temp.next = l1;

        if (l2 != null)
            temp.next = l2;

        return pHead.next;
    }
}
```

---
## 题目02：删除排序链表中的重复元素

> - 题号：83
> - 难度：简单
> - https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/


给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。


**示例 1:**

```c
输入: 1->1->2
输出: 1->2
```

**示例 2:**
```c
输入: 1->1->2->3->3
输出: 1->2->3
```

**思路**：利用双指针的方式。

`p1`作为前面的指针探路，`p2`作为后面的指针跟进，如果遇到重复元素，`p2.next`跳过去，`p1`跑完整个链表所有重复元素都被摘下来。

**参考代码**：

- 执行结果：通过
- 执行用时：160 ms, 在所有 C# 提交中击败了 5.23% 的用户
- 内存消耗：25.9 MB, 在所有 C# 提交中击败了 5.72% 的用户

```c
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int x) { val = x; }
 * }
 */
 
public class Solution
{
    public ListNode DeleteDuplicates(ListNode head)
    {
        if (head == null)
            return head;

        ListNode p1 = head.next;
        ListNode p2 = head;
        while (p1 != null)
        {
            if (p1.val == p2.val)
                p2.next = p1.next;
            else
                p2 = p2.next;
            p1 = p1.next;
        }
        return head;
    }
}
```

---
## 题目03：环形链表

> - 题号：141
> - 难度：简单
> - https://leetcode-cn.com/problems/linked-list-cycle/

给定一个链表，判断链表中是否有环。

为了表示给定链表中的环，我们使用整数`pos` 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果`pos`是 -1，则在该链表中没有环。

<b>示例 1</b>：
```c
输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。
```

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9hc3NldHMubGVldGNvZGUtY24uY29tL2FsaXl1bi1sYy11cGxvYWQvdXBsb2Fkcy8yMDE4LzEyLzA3L2NpcmN1bGFybGlua2VkbGlzdC5wbmc)

<b>示例 2</b>：
```c
输入：head = [1,2], pos = 0
输出：true
解释：链表中有一个环，其尾部连接到第一个节点。
```
![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9hc3NldHMubGVldGNvZGUtY24uY29tL2FsaXl1bi1sYy11cGxvYWQvdXBsb2Fkcy8yMDE4LzEyLzA3L2NpcmN1bGFybGlua2VkbGlzdF90ZXN0Mi5wbmc)

<b>示例 3</b>：
```c
输入：head = [1], pos = -1
输出：false
解释：链表中没有环。
```
![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9hc3NldHMubGVldGNvZGUtY24uY29tL2FsaXl1bi1sYy11cGxvYWQvdXBsb2Fkcy8yMDE4LzEyLzA3L2NpcmN1bGFybGlua2VkbGlzdF90ZXN0My5wbmc)

<b>进阶</b>：

你能用 O(1)（即，常量）内存解决此问题吗？

**思路**：利用双指针的方式。

通常情况下，判断是否包含了重复的元素，我们使用`Hash`的方式来做。对于单链表的这种场景，我们也可以使用双指针的方式。

第一个指针 `p1` 每次移动两个节点，第二个指针 `p2` 每次移动一个节点，如果该链表存在环的话，第一个指针一定会再次碰到第二个指针，反之，则不存在环。

比如：`head = [1,2,3,4,5]`，奇数

```c
p1：1   3   5   2   4   1
p2：1   2   3   4   5   1
```

比如：`head = [1,2,3,4]`，偶数
```c
p1：1   3   1   3   1
p2：1   2   3   4   1
```

**参考代码**：

- 状态：通过
- 执行用时: 112 ms, 在所有 C# 提交中击败了 98.43% 的用户
- 内存消耗: 24.9 MB, 在所有 C# 提交中击败了 5.13% 的用户

```c
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public bool HasCycle(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;

        while (p1 != null && p1.next != null)
        {
            p1 = p1.next.next;
            p2 = p2.next;
            if (p1 == p2)
                return true;
        }
        return false;
    }
}
```

## 题目04：反转链表


> - 题号：206
> - 难度：简单
> - https://leetcode-cn.com/problems/reverse-linked-list/

反转一个单链表。

<b>示例</b>:
```c
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
```

<b>进阶</b>:

你可以迭代或递归地反转链表。你能否用两种方法解决这道题？

**思路**：利用双指针的方式。

`p1`作为前面的指针探路，`p2`作为后面的指针跟进，顺着链表跑一圈，搞定问题。

**参考代码**：

- 状态：通过
- 执行用时: 116 ms, 在所有 C# 提交中击败了 97.50% 的用户
- 内存消耗: 23.3 MB, 在所有 C# 提交中击败了 5.26% 的用户

```c
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int x) { val = x; }
 * }
 */

public class Solution
{
    public ListNode ReverseList(ListNode head)
    {
        if (head == null || head.next == null)
            return head;
            
        ListNode p1 = head;
        ListNode p2 = null;
        while (p1 != null)
        {
            ListNode temp = p1.next;
            p1.next = p2;
            p2 = p1;
            p1 = temp;
        }
        return p2;
    }
}
```

---
## 题目05：删除链表中的节点

> - 题号：237
> - 难度：简单
> - https://leetcode-cn.com/problems/delete-node-in-a-linked-list/

请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。

现有一个链表 -- head = [4,5,1,9]，它可以表示为:

![](https://img-blog.csdnimg.cn/20190920111622847.png)


<b>示例 1</b>:
```c
输入: head = [4,5,1,9], node = 5
输出: [4,1,9]
解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
```

<b>示例 2</b>:
```c
输入: head = [4,5,1,9], node = 1
输出: [4,5,9]
解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
```

<b>说明</b>:

- 链表至少包含两个节点。
- 链表中所有节点的值都是唯一的。
- 给定的节点为非末尾节点并且一定是链表中的一个有效节点。
- 不要从你的函数中返回任何结果。



**思路：** 这道题没有给出链表的头节点，而是直接给出要删除的节点，让我们进行原地删除。我们对于该节点的前一个节点一无所知，所以无法直接执行删除操作。因此，我们将要删除节点的 next 节点的值赋值给要删除的节点，转而去删除 next 节点，从而达成目的。

**参考代码**：

- 状态：通过
- 41 / 41 个通过测试用例
- 执行用时: 120 ms, 在所有 C# 提交中击败了 99.55% 的用户
- 内存消耗: 24.4 MB, 在所有 C# 提交中击败了 5.88% 的用户

```c
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int x) { val = x; }
 * }
 */

public class Solution
{
    public void DeleteNode(ListNode node)
    {
        ListNode temp = node.next;
        while (temp != null)
        {
            node.val = temp.val;
            temp = temp.next;
            if (temp != null)
            {
                node = node.next;
            }
        }
        node.next = null;
    }
}
```



---
## 题目06：两数相加

> - 题号：2
> - 难度：中等
> - https://leetcode-cn.com/problems/add-two-numbers/

给出两个 <b>非空</b> 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 <b>逆序</b> 的方式存储的，并且它们的每个节点只能存储 <b>一位</b> 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

<b>示例 1</b>：
```c
输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807
```

<b>示例 2</b>：
```c
输入：(3 -> 7) + (9 -> 2)
输出：2 -> 0 -> 1
原因：73 + 29 = 102
```

**思路**：模仿我们小学时代学的加法运算。个位相加超过十进一，十位相加有进位则加上进位，依次类推。

<b>参考代码：</b>

- 状态：通过
- 执行用时: 144 ms, 在所有 C# 提交中击败了 97.98% 的用户
- 内存消耗: 26.7 MB, 在所有 C# 提交中击败了 5.07% 的用户

```c
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int x) { val = x; }
 * }
 */
public class Solution 
{
    public ListNode AddTwoNumbers(ListNode l1, ListNode l2) 
    {
        ListNode result = new ListNode(-1);
        ListNode l3 = result;
        int flag = 0;
        while (l1 != null && l2 != null)
        {
            int a = l1.val;
            int b = l2.val;
            int c = a + b + flag;
            l3.next = new ListNode(c%10);

            flag = c >= 10 ? 1 : 0;
            l1 = l1.next;
            l2 = l2.next;
            l3 = l3.next;
        }
        
        while (l1 != null)
        {
            int a = l1.val + flag;

            l3.next = new ListNode(a%10);

            flag = a >= 10 ? 1 : 0;
            l1 = l1.next;
            l3 = l3.next;
        }
        
        while (l2 != null)
        {
            int b = l2.val + flag;

            l3.next = new ListNode(b%10);
            flag = b >= 10 ? 1 : 0;
            l2 = l2.next;
            l3 = l3.next;
        }

        if (flag == 1)
        {
            l3.next = new ListNode(flag);
        }
        return result.next;        
    }
}
```

---
## 题目07：删除链表的倒数第N个节点

> - 题号：19
> - 难度：中等
> - https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/

给定一个链表，删除链表的倒数第`n`个节点，并且返回链表的头结点。

**示例**：

```c
给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.
```

**说明**：

给定的`n`保证是有效的。

**进阶**：

你能尝试使用一趟扫描实现吗？

**思路：** 使用两个指针，前面的指针`p1`先走`n`步，接着让后面的指针`p2`与`p1`同步走，`p1`走到终点，`p2`即走到要移除的结点位置。

**参考代码：**
- 执行结果：通过
- 执行用时：104 ms, 在所有 C# 提交中击败了 86.93% 的用户
- 内存消耗：24.6 MB, 在所有 C# 提交中击败了 100.00% 的用户

```c
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int x) { val = x; }
 * }
 */
public class Solution 
{
    public ListNode RemoveNthFromEnd(ListNode head, int n) 
    {
        ListNode p1 = head;
        ListNode p2 = head;
        
        while (n > 0)
        {
            p1 = p1.next;
            n--;
        }

        if (p1 == null) //移除头结点
        {
            return head.next;
        }
            
        while (p1.next != null)
        {
            p1 = p1.next;
            p2 = p2.next;
        }
            
        p2.next = p2.next.next;
        return head;
    }
}
```


---
## 题目08：两两交换链表中的节点

> - 题号：24
> - 难度：中等
> - https://leetcode-cn.com/problems/swap-nodes-in-pairs/

给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

**你不能只是单纯的改变节点内部的值**，而是需要实际的进行节点交换。

 

**示例:**

> 给定 1->2->3->4, 你应该返回 2->1->4->3.


**参考代码**：

- 执行结果：通过
- 执行用时：100 ms, 在所有 C# 提交中击败了 93.18% 的用户
- 内存消耗：23.4 MB, 在所有 C# 提交中击败了 87.72% 的用户

```c
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int x) { val = x; }
 * }
 */
 
public class Solution
{
    public ListNode SwapPairs(ListNode head)
    {
        if (head == null || head.next == null)
            return head;

        head = Swap(head);
        
        ListNode temp = head.next;
        
        while (temp != null && temp.next != null)
        {
            temp.next = Swap(temp.next);
            if (temp.next != null)
            {
                temp = temp.next.next;
            }
        }
        return head;
    }

    public ListNode Swap(ListNode node)
    {
        if (node == null || node.next == null)
            return node;

        ListNode t = node.next;
        node.next = t.next;
        t.next = node;
        return t;
    }
}
```

---
## 题目09：旋转链表


> - 题号：61
> - 难度：中等
> - https://leetcode-cn.com/problems/rotate-list/

给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。

**示例 1:**

```c
输入: 1->2->3->4->5->NULL, k = 2
输出: 4->5->1->2->3->NULL

解释:
向右旋转 1 步: 5->1->2->3->4->NULL
向右旋转 2 步: 4->5->1->2->3->NULL
```

**示例 2:**

```c
输入: 0->1->2->NULL, k = 4
输出: 2->0->1->NULL

解释:
向右旋转 1 步: 2->0->1->NULL
向右旋转 2 步: 1->2->0->NULL
向右旋转 3 步: 0->1->2->NULL
向右旋转 4 步: 2->0->1->NULL
```




**参考代码**：

- 执行结果：通过
- 执行用时：100 ms, 在所有 C# 提交中击败了 98.13% 的用户
- 内存消耗：25.1 MB, 在所有 C# 提交中击败了 100.00% 的用户


```c
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int x) { val = x; }
 * }
 */
 
public class Solution
{
    public ListNode RotateRight(ListNode head, int k)
    {
        if (head == null || k == 0)
            return head;

        int len = GetLength(head);
        int index = len - k%len;

        if (index == len)
            return head;

        ListNode temp1 = head;
        ListNode temp2 = head;
        for (int i = 0; i < index - 1; i++)
        {
            temp1 = temp1.next;
        }
        head = temp1.next;
        temp1.next = null;

        temp1 = head;
        while (temp1.next != null)
        {
            temp1 = temp1.next;
        }
        temp1.next = temp2;
        return head;
    }

    public int GetLength(ListNode head)
    {
        ListNode temp = head;
        int i = 0;
        while (temp != null)
        {
            i++;
            temp = temp.next;
        }
        return i;
    }
} 
```



---
## 题目10：合并K个排序链表

> - 题号：23
> - 难度：困难
> - https://leetcode-cn.com/problems/merge-k-sorted-lists/


合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

<b>示例</b>:
```c
输入:
[
  1->4->5,
  1->3->4,
  2->6
]
输出: 1->1->2->3->4->4->5->6
```

<b>思路：</b>两两合并的方式。

构造合并两个有序链表得到一个新的有序链表的方法：`ListNode MergeTwoLists(ListNode l1, ListNode l2)`。可以使用该方法合并前两个有序链表得到一个新的有序链表，之后把这个新链表与第三个有序链表合并，依次类推，最后得到合并`K`个有序列表的新列表。


**参考代码**：

- 执行结果：通过
- 执行用时：256 ms, 在所有 C# 提交中击败了 36.69% 的用户
- 内存消耗：29.3 MB, 在所有 C# 提交中击败了 18.37% 的用户


```c
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int x) { val = x; }
 * }
 */
public class Solution 
{
    public ListNode MergeTwoLists(ListNode l1, ListNode l2) 
    {
        ListNode pHead = new ListNode(int.MaxValue);
        ListNode temp = pHead;

        while (l1 != null && l2 != null)
        {
            if (l1.val < l2.val)
            {
                temp.next = l1;
                l1 = l1.next;
            }
            else
            {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }

        if (l1 != null)
            temp.next = l1;

        if (l2 != null)
            temp.next = l2;

        return pHead.next;
    }
    
    public ListNode MergeKLists(ListNode[] lists) {
        if (lists.Length == 0)
            return null;

        ListNode result = lists[0];
        for (int i = 1; i < lists.Length; i++)
        {
            result = MergeTwoLists(result, lists[i]);
        }
        return result;
    }
}
```








