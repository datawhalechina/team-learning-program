
# Leetcode同步练习（一）

## 题目01：两数之和

> - 题号：1
> - 难度：简单
> - https://leetcode-cn.com/problems/two-sum/

给定一个整数数组 `nums` 和一个目标值 `target`，请你在该数组中找出和为目标值的那 <b>两个整数</b>，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

**示例1:**

```c
给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9，所以返回 [0, 1]
```

**示例2：**

```c
给定 nums = [230, 863, 916, 585, 981, 404, 316, 785, 88, 12, 70, 435, 384, 778, 887, 755, 740, 337, 86, 92, 325, 422, 815, 650, 920, 125, 277, 336, 221, 847, 168, 23, 677, 61, 400, 136, 874, 363, 394, 199, 863, 997, 794, 587, 124, 321, 212, 957, 764, 173, 314, 422, 927, 783, 930, 282, 306, 506, 44, 926, 691, 568, 68, 730, 933, 737, 531, 180, 414, 751, 28, 546, 60, 371, 493, 370, 527, 387, 43, 541, 13, 457, 328, 227, 652, 365, 430, 803, 59, 858, 538, 427, 583, 368, 375, 173, 809, 896, 370, 789], target = 542

因为 nums[28] + nums[45] = 221 + 321 = 542，所以返回 [28, 45]
```

**参考代码：**

思路：直接利用暴力匹配算法。

- 执行结果：通过
- 执行用时：432 ms, 在所有 C# 提交中击败了 65.82% 的用户
- 内存消耗：30.8 MB, 在所有 C# 提交中击败了 8.67% 的用户

```c
public class Solution 
{
    public int[] TwoSum(int[] nums, int target) 
    {
        int[] result = new int[2];
        for (int i = 0; i < nums.Length - 1; i++)
        {
            int find = target - nums[i];
            for (int j = i + 1; j < nums.Length; j++)
            {
                if (find == nums[j])
                {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }
}
```


---
## 题目02：删除排序数组中的重复项

> - 题号：26
> - 难度：简单
> - https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/

给定一个 **排序数组**，你需要在 **原地** 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。

不要使用额外的数组空间，你必须在 **原地修改输入数组** 并在使用 O(1) 额外空间的条件下完成。

<b>示例 1</b>:

```c
给定数组 nums = [1,1,2], 
函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。 
你不需要考虑数组中超出新长度后面的元素。
```

<b>示例 2</b>:

```c
给定 nums = [0,0,1,1,1,2,2,3,3,4],
函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
你不需要考虑数组中超出新长度后面的元素。
```

<b>说明</b>:

为什么返回数值是整数，但输出的答案是数组呢?

请注意，输入数组是以“**引用**”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。

你可以想象内部操作如下:

```c
// nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
int len = removeDuplicates(nums);

// 在函数里修改输入数组对于调用者是可见的。
// 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
for (int i = 0; i < len; i++) {
    print(nums[i]);
}
```



**参考代码：**

思路：双索引法，就是一个快索引一个慢索引，`j`快`i`慢，当`nums[j] == nums[i]`时，`j++`就可以跳过重复项，不相等时，让`i++`并让`nums[i] = nums[j]`，把值复制过来继续执行到末尾即可，时间复杂度为`O(n)`。

- 执行结果：通过
- 执行用时：300 ms, 在所有 C# 提交中击败了 64.43% 的用户
- 内存消耗：33.5 MB, 在所有 C# 提交中击败了 5.48% 的用户

```c
public class Solution 
{
    public int RemoveDuplicates(int[] nums) 
    {
        if (nums.Length < 2)
            return nums.Length;
            
        int i = 0;
        for (int j = 1; j < nums.Length; j++)
        {
            if (nums[j] != nums[i])
            {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;        
    }
}
```

---
## 题目03：移除元素

> - 题号：27
> - 难度：简单
> - https://leetcode-cn.com/problems/remove-element/


给定一个数组`nums`和一个值`val`，你需要**原地**移除所有数值等于`val`的元素，返回移除后数组的新长度。

不要使用额外的数组空间，你必须在**原地修改输入数组**并在使用 O(1) 额外空间的条件下完成。

元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

**示例 1:**

```c
给定 nums = [3,2,2,3], val = 3,

函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。

你不需要考虑数组中超出新长度后面的元素。
```

**示例 2:**

```c
给定 nums = [0,1,2,2,3,0,4,2], val = 2,

函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。

注意这五个元素可为任意顺序。

你不需要考虑数组中超出新长度后面的元素。
```

**示例 3:**
```c
输入：[] value = 0

输出：0
```

**示例 4:**
```c
输入：[1] value = 1

输出：0
```

**示例 5:**

```c
输入：[4,5] value = 5

输出：1
```



**说明:**

为什么返回数值是整数，但输出的答案是数组呢?

请注意，输入数组是以“**引用**”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。

你可以想象内部操作如下:

```c
// nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
int len = removeElement(nums, val);

// 在函数里修改输入数组对于调用者是可见的。
// 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
for (int i = 0; i < len; i++) {
    print(nums[i]);
}
```

**参考代码：**

思路：双索引法，利用双索引`i`和`j`，`i`为慢索引拖后，`j`为快索引向前冲。如果`nums[j]!=val`，将`num[j]`的值赋给`num[i]`，循环结束后，`i`指针前面的元素，即为需要保留的元素，从而达到了移除元素的目的，时间复杂度为 `O(n)`。

- 执行结果：通过
- 执行用时：272 ms, 在所有 C# 提交中击败了 94.41% 的用户
- 内存消耗：29.9 MB, 在所有 C# 提交中击败了 5.21% 的用户

```c
public class Solution 
{
    public int RemoveElement(int[] nums, int val) 
    {
        int i = 0;
        for (int j = 0; j < nums.Length; j++)
        {
            if (nums[j] != val)
            {
                nums[i] = nums[j];
                    i++;
            }
        }
        return i;  
    }
}
```


---
## 题目04：最大子序和

> - 题号：53
> - 难度：简单
> - https://leetcode-cn.com/problems/maximum-subarray/

给定一个整数数组`nums`，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

<b>示例 1</b>:
```c
输入: [-2,1,-3,4,-1,2,1,-5,4],
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
```

<b>示例 2</b>:
```c
输入: [-2,1],
输出: 1
```

<b>进阶</b>:

如果你已经实现复杂度为`O(n)`的解法，尝试使用更为精妙的分治法求解。

**参考代码：**

思路：利用暴力算法。

- 状态：通过
- 执行用时: 596 ms, 在所有 C# 提交中击败了 14.18% 的用户
- 内存消耗: 24.5 MB, 在所有 C# 提交中击败了 5.88% 的用户

```c
public class Solution {
    public int MaxSubArray(int[] nums) {
        int len = nums.Length;
        if (len == 0)
            return 0;
        if (len == 1)
            return nums[0];
        int max = int.MinValue;

        for (int i = 0; i < len; i++)
        {
            int sum = nums[i];
            if (sum > max)
            {
                max = sum;
            }
            for (int j = i + 1; j < len; j++)
            {
                sum += nums[j];
                if (sum > max)
                {
                    max = sum;
                }
            }
        }
        return max;        
    }
}
```

---
## 题目05：盛最多水的容器


> - 题号：11
> - 难度：中等
> - https://leetcode-cn.com/problems/container-with-most-water/

给定`n`个非负整数`a1，a2，...，an`，每个数代表坐标中的一个点`(i, ai)`。在坐标内画`n`条垂直线，垂直线`i` 的两个端点分别为`(i, ai)`和`(i, 0)`。找出其中的两条线，使得它们与`x`轴共同构成的容器可以容纳最多的水。

<b>说明</b>：你不能倾斜容器，且`n`的值至少为 2。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9hbGl5dW4tbGMtdXBsb2FkLm9zcy1jbi1oYW5nemhvdS5hbGl5dW5jcy5jb20vYWxpeXVuLWxjLXVwbG9hZC91cGxvYWRzLzIwMTgvMDcvMjUvcXVlc3Rpb25fMTEuanBn?x-oss-process=image/format,png)

图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。

<b>示例</b>:
```c
输入: [1,8,6,2,5,4,8,3,7]
输出: 49
```


**参考代码：**

思路：利用双索引的方法。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9waWMubGVldGNvZGUtY24uY29tLzg4NGQ1YmNlOTU1ZThiYzA1MGY2NTUxNTQwNGEwNDI2NGYyYzFmZjgyNzYyMWFiZDcyYjgxNzA5ZmUzNzMyM2YtJUU5JTgxJThEJUU1JThFJTg2JUU3JTlBJTg0JUU2JTgzJTg1JUU1JTg2JUI1LmpwZw?x-oss-process=image/format,png)

以0-7走到1-7这一步为例，解释为什么放弃0-6这一分支：

```c
用h(i)表示第i条线段的高度，S(ij)表示第i条线段和第j条线段圈起来的面积。

已知 h(0) < h(7)，从而S(07) = h(0) * 7。

有S(06) = min(h(0), h(6)) * 6。

当h(0) <= h(6)，有S(06) = h(0) * 6；
当h(0) > h(6)，有S(06) = h(6) * 6，S(06) < h(0) * 6。

由此可知，S(06)必然小于S(07)。
```
把每一棵子树按照同样的方法分析，很容易可以知道，双索引法走的路径包含了最大面积。

- 状态：通过
- 50 / 50 个通过测试用例
- 执行用时: 144 ms, 在所有 C# 提交中击败了 99.64% 的用户
- 内存消耗: 26.6 MB, 在所有 C# 提交中击败了 5.45% 的用户

```c
public class Solution 
{
    public int MaxArea(int[] height) 
    {
        int i = 0, j = height.Length - 1;
        int max = int.MinValue;
        while (i < j)
        {
            int temp = (j - i) * Math.Min(height[i], height[j]);
            if (temp > max)
            {
                max = temp;
            }
            if (height[i] < height[j])
            {
                i++;
            }
            else
            {
                j--;
            }
        }
        return max;        
    }
}
```

---
## 题目06：搜索旋转排序数组

> - 题号：33
> - 难度：中等
> - https://leetcode-cn.com/problems/search-in-rotated-sorted-array/

假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

你可以假设数组中不存在重复的元素。

你的算法时间复杂度必须是`O(log n)`级别。

<b>示例 1</b>:
```c
输入: nums = [4,5,6,7,0,1,2], target = 0
输出: 4
```

<b>示例 2</b>:
```c
输入: nums = [4,5,6,7,0,1,2], target = 3
输出: -1
```

<b>示例 3</b>:
```c
输入: nums = [5,1,3], target = 5
输出: 0
```

<b>示例 4</b>:
```c
输入: nums = [4,5,6,7,8,1,2,3], target = 8
输出: 4
```

<b>示例 5</b>:
```c
输入: nums = [3,1], target = 1
输出: 1
```

**参考代码：**

思路：利用二分法。

- 状态：通过
- 执行用时: 128 ms, 在所有 C# 提交中击败了 97.17% 的用户
- 内存消耗: 23.8 MB, 在所有 C# 提交中击败了 12.00% 的用户

```c
public class Solution 
{
    public int Search(int[] nums, int target) 
    {
        int i = 0, j = nums.Length - 1;
        while (i <= j)
        {
            int mid = (i + j) / 2;
            if (nums[mid] == target)
                return mid;

            if (nums[mid] >= nums[i])
            {
                //左半部分有序
                if (target > nums[mid])
                {
                    i = mid + 1;
                }
                else
                {
                    if (target == nums[i])
                        return i;

                    if (target > nums[i])
                        j = mid - 1;
                    else
                        i = mid + 1;
                }
            }
            else
            {
                if (target < nums[mid])
                {
                    j = mid - 1;
                }
                else
                {
                    if (target == nums[j])
                        return j;
                    if (target < nums[j])
                        i = mid + 1;
                    else
                        j = mid - 1;
                }
            }
        }
        return -1;        
    }
}
```





---
## 题目07：数组中的第K个最大元素

> - 题号：215
> - 难度：中等
> - https://leetcode-cn.com/problems/kth-largest-element-in-an-array/

在未排序的数组中找到第 `k` 个最大的元素。请注意，你需要找的是数组排序后的第 `k` 个最大的元素，而不是第 `k` 个不同的元素。

<b>示例 1</b>:
```c
输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
```

<b>示例 2</b>:
```c
输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4
```

<b>说明</b>:

你可以假设 `k` 总是有效的，且 `1 ≤ k ≤ 数组的长度`。





**参考代码：**

思路：利用排序的方法。

- 状态：通过
- 执行用时: 152 ms, 在所有 C# 提交中击败了 76.47% 的用户
- 内存消耗: 24.6 MB, 在所有 C# 提交中击败了 5.55% 的用户

```c
public class Solution
{
    public int FindKthLargest(int[] nums, int k)
    {
        nums = nums.OrderBy(a => a).ToArray();
        return nums[nums.Length - k];
    }
}
```




---
## 题目08：除自身以外数组的乘积


> - 题号：238
> - 难度：中等
> - https://leetcode-cn.com/problems/product-of-array-except-self/

给定长度为`n`的整数数组`nums`，其中`n > 1`，返回输出数组`output`，其中`output[i]`等于 `nums`中除`nums[i]`之外其余各元素的乘积。

<b>示例</b>:
```c
输入: [1,2,3,4]
输出: [24,12,8,6]
```

<b>说明</b>: 请不要使用除法，且在`O(n)` 时间复杂度内完成此题。

<b>进阶</b>：

你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）






**参考代码：**

思路：乘积 = 当前数左边的乘积 * 当前数右边的乘积

```c
                [1, 2, 3, 4]
   左边的乘积    [1, 1, 2, 6]
   右边的乘积    [24,12,4, 1]
结果 = 左*右     [24,12,8, 6] 
```

- 状态：通过
- 执行用时: 304 ms, 在所有 C# 提交中击败了 100.00% 的用户
- 内存消耗: 34.6 MB, 在所有 C# 提交中击败了 100.00% 的用户

```c
public class Solution
{
    public int[] ProductExceptSelf(int[] nums)
    {
        int len = nums.Length;
        int[] output1 = new int[len];//正向乘积
        int[] output2 = new int[len];//反向乘积
        output1[0] = 1;
        output2[len - 1] = 1;
        for (int i = 1; i < len; i++)
        {
            output1[i] = output1[i - 1]*nums[i - 1];
            output2[len - i - 1] = output2[len - i]*nums[len - i];
        }
        for (int i = 0; i < len; i++)
        {
            output1[i] *= output2[i];
        }
        return output1;
    }
}
```


---
## 题目09：寻找两个有序数组的中位数


> - 题号：4
> - 难度：困难
> - https://leetcode-cn.com/problems/median-of-two-sorted-arrays/

给定两个大小为`m`和`n`的有序数组`nums1`和`nums2`。

请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为`O(log(m + n))`。

你可以假设`nums1`和`nums2`不会同时为空。

<b>示例 1</b>:
```c
nums1 = [1, 3]
nums2 = [2]

则中位数是 2.0
```

<b>示例 2</b>:

```c
nums1 = [1, 2]
nums2 = [3, 4]

则中位数是 (2 + 3)/2 = 2.5
```

**参考代码：**

思路：利用二分策略。

中位数：用来将一个集合划分为两个长度相等的子集，其中一个子集中的元素总是大于另一个子集中的元素。

由于题目要求时间复杂度为`O(log(m + n))`，所以不能从两个有序数组的首尾挨个遍历来找到中位数（复杂度 `O(m + n)`）；而是要通过二分策略，通过每次比较，能够直接按比例的刷掉一组数字，最后找到中位数（复杂度`O(log(m + n))`）。

```c
nums1: [a1,a2,a3,...am]
nums2: [b1,b2,b3,...bn]

[nums1[:i],nums2[:j] | nums1[i:], nums2[j:]]

nums1 取 i 个数的左半边
nums2 取 j = (m+n+1)/2 - i 的左半边
```
只要保证左右两边 <b>个数</b> 相同，中位数就在 `|` 这个边界旁边产生，从而可以利用二分法找到合适的`i`。

- 状态：通过
- 执行用时: 160 ms, 在所有 C# 提交中击败了 99.18% 的用户
- 内存消耗: 26.8 MB, 在所有 C# 提交中击败了 5.05% 的用户

```c
public class Solution {
    public double FindMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.Length;
        int n = nums2.Length;
        if (m > n)
            return FindMedianSortedArrays(nums2, nums1);

        int k = (m + n + 1)/2;
        int left = 0;
        int right = m;
        while (left < right)
        {
            int i = (left + right)/2;
            int j = k - i;
            if (nums1[i] < nums2[j - 1])
                left = i + 1;
            else
                right = i;
        }
        int m1 = left;
        int m2 = k - left;
        int c1 = Math.Max(m1 == 0 ? int.MinValue : nums1[m1 - 1],
            m2 == 0 ? int.MinValue : nums2[m2 - 1]);

        if ((m + n)%2 == 1)
            return c1;

        int c2 = Math.Min(m1 == m ? int.MaxValue : nums1[m1],
            m2 == n ? int.MaxValue : nums2[m2]);
        return (c1 + c2)*0.5;        
    }
}
```

---
## 题目10：缺失的第一个正数


> - 题号：41
> - 难度：困难
> - https://leetcode-cn.com/problems/first-missing-positive/

给定一个未排序的整数数组，找出其中没有出现的最小的正整数。

<b>示例1</b>:

```c
输入: [1,2,0]
输出: 3
```

<b>示例2</b>:

```c
输入: [3,4,-1,1]
输出: 2
```

<b>示例3</b>:
```c
输入: [7,8,9,11,12]
输出: 1
```

<b>示例4</b>:
```c
输入: [1,1]
输出: 2
```

<b>实例5</b>:

```c
输入: []
输出: 1
```

<b>说明</b>:

你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。

**参考代码：**

思路：把数组进行一次“排序”，“排序”的规则是：如果这个数字`i`落在“区间范围里”，`i`就应该放在索引为`i - 1`的位置上。

- 执行结果：通过
- 执行用时：100 ms, 在所有 C# 提交中击败了 93.75% 的用户
- 内存消耗：24.2 MB, 在所有 C# 提交中击败了 97.44% 的用户


```c
public class Solution {
    public int FirstMissingPositive(int[] nums) {
        int len = nums.Length;
        for (int i = 0; i < len; i++)
        {
            while (nums[i] != i + 1 && nums[i] <= len && nums[i] > 0 && nums[i] != nums[nums[i] - 1])
            {
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }
        for (int i = 0; i < len; i++)
        {
            if (nums[i] != i + 1)
            {
                return i + 1;
            }
        }
        return len + 1; //nums.Length = 0    
    }
}
```



