
# Leetcode同步练习（二）

## 题目01：回文数

> - 题号：9
> - 难度：简单
> - https://leetcode-cn.com/problems/palindrome-number/

判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

<b>示例 1</b>:
```c
输入: 121
输出: true
```

<b>示例 2</b>:

```c
输入: -121
输出: false
解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
```

<b>示例 3</b>:
```c
输入: 10
输出: false
解释: 从右向左读, 为 01 。因此它不是一个回文数。
```

<b>进阶</b>:

你能不将整数转为字符串来解决这个问题吗？

**参考代码**：

- 状态：通过
- 执行用时: 76 ms, 在所有 C# 提交中击败了 98.90% 的用户
- 内存消耗: 14.9 MB, 在所有 C# 提交中击败了 85.12% 的用户

```c
public class Solution {
    public bool IsPalindrome(int x) {
        if (x < 0)
            return false;

        int bit = 1;
        while (x / bit >= 10)
        {
            bit = bit * 10;
        }

        while (x > 0)
        {
            int left = x % 10;
            int right = x / bit;
            if (left != right)
            {
                return false;
            }
            x = (x % bit) / 10;
            bit = bit / 100;
        }
        return true;
}
```



---
## 题目02：x 的平方根

> - 题号：69
> - 难度：简单
> - https://leetcode-cn.com/problems/sqrtx/

实现 `int sqrt(int x)` 函数。

计算并返回`x`的平方根，其中`x`是非负整数。

由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

<b>示例 1</b>:

```c
输入: 4
输出: 2
```

<b>示例 2</b>:

```c
输入: 8
输出: 2
说明: 8 的平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
```

**思路**：利用牛顿迭代法。

![牛顿迭代公式](https://img-blog.csdnimg.cn/2019082111485172.png)


**参考代码**

- 状态：通过
- 执行用时: 48 ms, 在所有 C# 提交中击败了 100.00% 的用户
- 内存消耗: 13.7 MB, 在所有 C# 提交中击败了 5.40% 的用户

```c
public class Solution {
    public int MySqrt(int x) {
        if (x < 0)
            throw new ArgumentOutOfRangeException();

        double error = 1.0e-5;
        double cur = x;
        while (Math.Abs(cur*cur - x) > error)
        {
            cur = (cur + 1.0*x/cur)/2.0;
        }
        return (int)cur;        
    }
}
```


---
## 题目03：爬楼梯

> - 题号：70
> - 难度：简单
> - https://leetcode-cn.com/problems/climbing-stairs/

假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

<b>注意</b>：给定 n 是一个正整数。

<b>示例 1</b>：
```c
输入： 2
输出： 2
解释： 有两种方法可以爬到楼顶。
1.  1 阶 + 1 阶
2.  2 阶
```

<b>示例 2</b>：
```c
输入： 3
输出： 3
解释： 有三种方法可以爬到楼顶。
1.  1 阶 + 1 阶 + 1 阶
2.  1 阶 + 2 阶
3.  2 阶 + 1 阶
```

<b>示例 3</b>：
```c
输入： 44
输出： 1134903170
```

**思路**：利用循环

分析这个题目：
- 1 阶，f(1) = 1 种方案
- 2 阶，f(2) = 2 种方案
- 3 阶，f(3) = 3 种方案
- 4 阶，f(4) = 5 种方案
- ……
- n 阶，f(n) = f(n-1) + f(n-2) 种方案

即，该问题可以转换为斐波那契数列问题。

**参考代码**：

- 状态：通过
- 执行用时: 52 ms, 在所有 C# 提交中击败了 97.87% 的用户
- 内存消耗: 13.7 MB, 在所有 C# 提交中击败了 5.98% 的用户

```c
public class Solution {
    public int ClimbStairs(int n) {
        if (n <= 2)
            return n;

        int first = 1;
        int second = 2;
        int result = 0;

        for (int i = 3; i <= n; i++)
        {
            result = first + second;
            first = second;
            second = result;
        }
        return result;        
    }
}
```

---
## 题目04：买卖股票的最佳时机

> - 题号：121
> - 难度：简单
> - https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/

给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。

注意你不能在买入股票前卖出股票。

<b>示例 1</b>:
```c
输入: [7,1,5,3,6,4]
输出: 5
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
```

<b>示例 2</b>:
```c
输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
```

**思路**：如果今天把股票卖掉能够赚最多钱的最大值，即为最大利润。


**参考代码**：

- 状态：通过
- 执行用时: 132 ms, 在所有 C# 提交中击败了 97.33% 的用户
- 内存消耗: 24 MB, 在所有 C# 提交中击败了 5.62% 的用户

```c
public class Solution
{
    public int MaxProfit(int[] prices)
    {
        if (prices.Length <= 1)
            return 0;

        int min = prices[0];
        int max = 0;
        for (int i = 1; i < prices.Length; i++)
        {
            int earn = prices[i] - min;
            if (earn > max)
            {
                max = earn;
            }
            if (prices[i] < min)
            {
                min = prices[i];
            }
        }
        return max;
    }
}
```


---
## 题目05：买卖股票的最佳时机 II

> - 题号：122
> - 难度：简单
> - https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/


给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。

<b>注意</b>：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

<b>示例 1</b>:
```c
输入: [7,1,5,3,6,4]
输出: 7
解释: 
    在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
    随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
```

<b>示例 2</b>:
```c
输入: [1,2,3,4,5]
输出: 4
解释: 
    在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
    注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
    因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
```
    
<b>示例 3</b>:
```c
输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
```

**思路**：贪心算法

贪心策略：只要后一天价格比前一天高，就在前一天买进后一天卖出。

![有升有降的情况](https://img-blog.csdnimg.cn/20190914100650406.png)


![连续上升的情况](https://img-blog.csdnimg.cn/20200325110545912.png)

**参考代码**：

- 状态：通过
- 执行用时: 140 ms, 在所有 C# 提交中击败了 72.02% 的用户
- 内存消耗: 24.2 MB, 在所有 C# 提交中击败了 5.36% 的用户


```c
public class Solution
{
    public int MaxProfit(int[] prices)
    {
        int earn = 0;
        for (int i = 0; i < prices.Length-1; i++)
        {
            earn += Math.Max(prices[i + 1] - prices[i], 0);
        }
        return earn;
    }
}
```


---
## 题目06：跳跃游戏

> - 题号：55
> - 难度：中等
> - https://leetcode-cn.com/problems/jump-game/

给定一个非负整数数组，你最初位于数组的第一个位置。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

判断你是否能够到达最后一个位置。

**示例 1:**

```c
输入: [2,3,1,1,4]
输出: true
解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3
步到达最后一个位置。
```

**示例 2:**

```c
输入: [3,2,1,0,4]
输出: false
解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0，
所以你永远不可能到达最后一个位置。
```

**示例 3:**

```c
输入：[0]
输出：true
```

**思路：贪心算法**

贪心策略：每次记录能跳到点的最大值，如果当前点超出最大值，返回false，如果最大值达到最后一个位置，返回true。

**参考代码：**

- 执行结果：通过
- 执行用时：120 ms, 在所有 C# 提交中击败了 57.32% 的用户
- 内存消耗：26.2 MB, 在所有 C# 提交中击败了 6.67% 的用户

```c
public class Solution
{
    public bool CanJump(int[] nums)
    {
        int maxlength = 0;      //记录所能到达的最远点
        for (int i = 0; maxlength < nums.Length-1; i++)
        {
            if (i > maxlength)
            {
                return false;//若此点已不能到达，返回false 
            }
            maxlength = Math.Max(i + nums[i], maxlength);
        }
        return true;
    }
}
```





---
## 题目07：三数之和

> - 题号：15
> - 难度：中等
> - https://leetcode-cn.com/problems/3sum/

给定一个包含`n`个整数的数组`nums`，判断`nums`中是否存在三个元素`a，b，c`，使得`a + b + c = 0`？找出所有满足条件且不重复的三元组。

注意：答案中不可以包含重复的三元组。

**示例：**

```c
给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]
```



**思路：利用 排序 + 三索引 的方法。**

为了避免三次循环，提升执行效率。首先，对`nums`进行排序。然后，固定3个索引`i,l(left),r(right)`，`i`进行最外层循环，`l`指向`nums[i]`之后数组的最小值，`r`指向`nums[i]`之后数组的最大值。模仿快速排序的思路，如果`nums[i] > 0`就不需要继续计算了，否则计算`nums[i] + nums[l] + nums[r]`是否等于零并进行相应的处理。如果大于零，向`l`方向移动`r`指针，如果小于零，向`r`方向移动`l`索引，如果等于零，则加入到存储最后结果的result链表中。当然，题目中要求这个三元组不可重复，所以在进行的过程中加入去重就好。

**参考代码：**

- 执行结果：通过
- 执行用时：348 ms, 在所有 C# 提交中击败了 99.54% 的用户
- 内存消耗：35.8 MB, 在所有 C# 提交中击败了 6.63% 的用户

```c
public class Solution 
{
    public IList<IList<int>> ThreeSum(int[] nums) 
    {
        IList<IList<int>> result = new List<IList<int>>();

        nums = nums.OrderBy(a => a).ToArray();
        int len = nums.Length;
        
        for (int i = 0; i < len - 2; i++)
        {
            if (nums[i] > 0) 
                break; // 如果最小的数字大于0, 后面的操作已经没有意义

            if (i > 0 && nums[i - 1] == nums[i])
                continue; // 跳过三元组中第一个元素的重复数据

            int l = i + 1;
            int r = len - 1;

            while (l < r)
            {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum < 0)
                {
                    l++;
                }
                else if (sum > 0)
                {
                    r--;
                }
                else
                {
                    result.Add(new List<int>() {nums[i], nums[l], nums[r]});
                    // 跳过三元组中第二个元素的重复数据
                    while (l < r && nums[l] == nums[l + 1]) 
                    {
                        l++;
                    }
                    // 跳过三元组中第三个元素的重复数据
                    while (l < r && nums[r - 1] == nums[r]) 
                    {
                        r--;
                    }
                    l++;
                    r--;
                }
            }
        }
        return result;    
    }
}
```


---
## 题目08：最接近的三数之和

> - 题号：16
> - 难度：中等
> - https://leetcode-cn.com/problems/3sum-closest/

给定一个包括`n`个整数的数组`nums`和一个目标值`target`。找出`nums`中的三个整数，使得它们的和与`target`最接近。返回这三个数的和。假定每组输入只存在唯一答案。

<b>示例</b> :

```c
例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
```


<b>思路：利用 排序 + 三索引 的方法</b>


**参考代码：**

- 状态：通过
- 执行用时: 132 ms, 在所有 C# 提交中击败了 100.00% 的用户
- 内存消耗: 24 MB, 在所有 C# 提交中击败了 5.55% 的用户


```c
public class Solution 
{
    public int ThreeSumClosest(int[] nums, int target) 
    {
        nums = nums.OrderBy(a => a).ToArray();
        int result = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.Length - 2; i++)
        {
            int start = i + 1, end = nums.Length - 1;
            while (start < end)
            {
                int sum = nums[start] + nums[end] + nums[i];
                if (Math.Abs(target - sum) < Math.Abs(target - result))
                    result = sum;
                if (sum > target)
                    end--;
                else if (sum < target)
                    start++;
                else
                    return result;
            }
        }
        return result;        
    }
}
```

---
## 题目09：螺旋矩阵 II

> - 题号：59
> - 难度：中等
> - https://leetcode-cn.com/problems/spiral-matrix-ii/

给定一个正整数 n，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。

<b>示例</b>:
```c
输入: 3
输出:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
```

**参考代码**：

- 状态：通过
- 执行用时: 296 ms, 在所有 C# 提交中击败了 97.67% 的用户
- 内存消耗: 25 MB, 在所有 C# 提交中击败了 11.11% 的用户

```c
public class Solution
{
    public int[][] GenerateMatrix(int n)
    {
        int[][] matrix = new int[n][];
        for (int i = 0; i < n; i++)
        {
            matrix[i] = new int[n];
        }

        int start = 0;//起始位置
        int end1 = n - 1;//最左边位置
        int end2 = n - 1;//最下边位置
        int count = 1;

        while (start < end1 && start < end2)
        {
            LeftToRight(start, end1, start, matrix, ref count);
            TopToBottom(start + 1, end2, end1, matrix, ref count);
            RightToLeft(end1 - 1, start, end2, matrix, ref count);
            BottomToTop(end2 - 1, start + 1, start, matrix, ref count);
            start++;
            end1 = n - 1 - start;
            end2 = n - 1 - start;
        }
        if (n%2 == 1)
        {
            matrix[start][start] = count;
        }
        return matrix;
    }

    private void LeftToRight(int start, int end, int rowIndex, int[][] matrix, ref int from)
    {
        for (int i = start; i <= end; i++)
        {
            matrix[rowIndex][i] = from;
            from++;
        }
    }

    private void TopToBottom(int start, int end, int colIndex, int[][] matrix, ref int from)
    {
        for (int i = start; i <= end; i++)
        {
            matrix[i][colIndex] = from;
            from++;
        }
    }

    private void RightToLeft(int start, int end, int rowIndex, int[][] matrix, ref int from)
    {
        for (int i = start; i >= end; i--)
        {
            matrix[rowIndex][i] = from;
            from++;
        }
    }

    private void BottomToTop(int start, int end, int colIndex, int[][] matrix, ref int from)
    {
        for (int i = start; i >= end; i--)
        {
            matrix[i][colIndex] = from;
            from++;
        }
    }
}
```



## 题目10：不同路径

> - 题号：62
> - 难度：中等
> - https://leetcode-cn.com/problems/unique-paths/

一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

问总共有多少条不同的路径？

![问题](https://img-blog.csdnimg.cn/20190912160514978.png)

例如，上图是一个7 x 3 的网格。有多少可能的路径？

<b>说明</b>：m 和 n 的值均不超过 100。

<b>示例 1</b>:
```c
输入: m = 3, n = 2
输出: 3
解释:
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向右 -> 向下
2. 向右 -> 向下 -> 向右
3. 向下 -> 向右 -> 向右
```

<b>示例 2</b>:
```c
输入: m = 7, n = 3
输出: 28
```

<b>示例 3</b>:
```c
输入: m = 23, n = 12
输出: 193536720
```

<b>思路：</b>利用动态规划。

动态规划表格01：

![表01](https://img-blog.csdnimg.cn/20190912160347481.png)

动态规划表格02：

![表02](https://img-blog.csdnimg.cn/20190912160424714.png)

动态规划的最优子结构为：`d[i,j] = d[i-1,j] + d[i,j-1]`

- 状态：通过
- 62 / 62 个通过测试用例
- 执行用时: 52 ms, 在所有 C# 提交中击败了 93.18% 的用户
- 内存消耗: 13.6 MB, 在所有 C# 提交中击败了 17.65% 的用户

```c
public class Solution
{
    public int UniquePaths(int m, int n)
    {
        int[,] memo = new int[m, n];
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (i == 0)
                {
                    memo[i, j] = 1;
                }
                else if (j == 0)
                {
                    memo[i, j] = 1;
                }
                else
                {
                    memo[i, j] = memo[i - 1, j] + memo[i, j - 1];
                }
            }
        }
        return memo[m - 1, n - 1];
    }
}
```



