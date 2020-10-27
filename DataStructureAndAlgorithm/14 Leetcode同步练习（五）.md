# Leetcode同步练习（五）
## 目录
- 题目01：用栈实现队列
- 题目02：托普利茨矩阵
- 题目03：罗马数字转整数
- 题目04：最长公共前缀
- 题目05：反转字符串
- 题目06：无重复字符的最长子串
- 题目07：最长回文子串
- 题目08：字符串相乘
- 题目09：正则表达式匹配
- 题目10：通配符匹配


---
## 题目01：用栈实现队列

> - 题号：232
> - 难度：简单
> - https://leetcode-cn.com/problems/implement-queue-using-stacks/

使用栈实现队列的下列操作：

- `push(x)` -- 将一个元素放入队列的尾部。
- `pop()` -- 从队列首部移除元素。
- `peek()` -- 返回队列首部的元素。
- `empty()` -- 返回队列是否为空。


示例:

```c
MyQueue queue = new MyQueue();

queue.push(1);
queue.push(2);  
queue.peek();  // 返回 1
queue.pop();   // 返回 1
queue.empty(); // 返回 false
```

说明:

- 你只能使用标准的栈操作 -- 也就是只有push to top，peek/pop from top，size 和 is empty 操作是合法的。
- 你所使用的语言也许不支持栈。你可以使用`list`或者`deque`（双端队列）来模拟一个栈，只要是标准的栈操作即可。
- 假设所有操作都是有效的（例如，一个空的队列不会调用`pop`或者`peek`操作）。

**参考代码：**

- 执行结果：通过
- 执行用时：112 ms, 在所有 C# 提交中击败了 95.10% 的用户
- 内存消耗：24.3 MB, 在所有 C# 提交中击败了 5.33% 的用户

```c
public class MyQueue
{
    private Stack<int> _stack = new Stack<int>();

    /** Initialize your data structure here. */
    public MyQueue()
    {
        ;
    }

    /** Push element x to the back of queue. */
    public void Push(int x)
    {
        if (_stack.Count == 0)
        {
            _stack.Push(x);
            return;
        }

        Stack<int> temp = new Stack<int>();
        while (_stack.Count != 0)
        {
            temp.Push(_stack.Pop());
        }
        _stack.Push(x);
        while (temp.Count != 0)
        {
            _stack.Push(temp.Pop());
        }
    }

    /** Removes the element from in front of queue and returns that element. */
    public int Pop()
    {
        return _stack.Count > 0 ? _stack.Pop() : -1;
    }

    /** Get the front element. */
    public int Peek()
    {
        return _stack.Count > 0 ? _stack.Peek() : -1;
    }

    /** Returns whether the queue is empty. */
    public bool Empty()
    {
        return _stack.Count == 0;
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.Push(x);
 * int param_2 = obj.Pop();
 * int param_3 = obj.Peek();
 * bool param_4 = obj.Empty();
 */
```

---
## 题目02：托普利茨矩阵

> - 题号：766
> - 难度：简单
> - https://leetcode-cn.com/problems/toeplitz-matrix/


如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。

给定一个`M x N`的矩阵，当且仅当它是托普利茨矩阵时返回`True`。

**示例 1:**

```c
输入: 
matrix = [
  [1,2,3,4],
  [5,1,2,3],
  [9,5,1,2]
]
输出: True
解释:
在上述矩阵中, 其对角线为:
"[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]"。
各条对角线上的所有元素均相同, 因此答案是True。
```

**示例 2:**

```c
输入:
matrix = [
  [1,2],
  [2,2]
]
输出: False
解释: 
对角线"[1, 2]"上的元素不同。
```

**说明:**

1. `matrix`是一个包含整数的二维数组。
2. `matrix`的行数和列数均在 [1, 20]范围内。
3. `matrix[i][j]`包含的整数在 [0, 99]范围内。


**进阶:**

1. 如果矩阵存储在磁盘上，并且磁盘内存是有限的，因此一次最多只能将一行矩阵加载到内存中，该怎么办？
2. 如果矩阵太大以至于只能一次将部分行加载到内存中，该怎么办？

**思路**：所有对角线上的元素都满足$a_{11} = a_{00}, a_{22} = a_{11}, a_{kk} = a_{k-1k-1}$对于对角线上的元素来说，如果当前元素不是第一个出现的元素，那么它前面的元素一定在当前元素的左上角。可以推出，对于位于坐标$(r, c)$上的元素，只需要检查`r == 0 OR c == 0 OR matrix[r-1][c-1] == matrix[r][c]`就可以了。

**参考代码**：

- 执行结果：通过
- 执行用时：128 ms, 在所有 C# 提交中击败了 47.50% 的用户
- 内存消耗：27.5 MB, 在所有 C# 提交中击败了 16.13% 的用户

```c
public class Solution
{
    public bool IsToeplitzMatrix(int[][] matrix)
    {
        for (int i = 1; i < matrix.Length; i++)
        {
            int[] row = matrix[i];
            for (int j = 1; j < row.Length; j++)
            {
                if (matrix[i][j] != matrix[i - 1][j - 1])
                    return false;
            }
        }
        return true;
    }
}
```


---
## 题目03：罗马数字转整数

> - 题号：13
> - 难度：简单
> - https://leetcode-cn.com/problems/roman-to-integer/

罗马数字包含以下七种字符: `I， V， X， L，C，D`和`M`。

```
字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
```

例如， 罗马数字 2 写做`II`，即为两个并列的 1。12 写做`XII`，即为`X + II`。 27 写做`XXVII`, 即为`XX + V + II`。


通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做`IIII`，而是`IV`。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为`IX`。这个特殊的规则只适用于以下六种情况：

```
I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
```

给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。

**示例 1:**

```c
输入:"III"
输出: 3
```

**示例 2:**

```c
输入: "IV"
输出: 4
```

**示例 3:**
```c
输入: "IX"
输出: 9
```

**示例 4:**
```c
输入: "LVIII"
输出: 58
解释: L = 50, V= 5, III = 3.
```

**示例 5:**
```c
输入: "MCMXCIV"
输出: 1994
解释: M = 1000, CM = 900, XC = 90, IV = 4.
```

**思路**：直接法，根据“罗马数字中小的数字在大的数字的右边”以及六种特殊情况的规则直接去写代码。

**参考代码**：

- 执行结果：通过
- 执行用时：96 ms, 在所有 C# 提交中击败了 95.88% 的用户
- 内存消耗：25.7 MB, 在所有 C# 提交中击败了 5.27% 的用户

```c
public class Solution
{
    public int RomanToInt(string s)
    {
        int result = 0;
        int count = s.Length;
        int i = 0;
        while (i < count)
        {
            char c = s[i];
            int move = 0;
            switch (c)
            {
                case 'I':
                    result += PreI(i, s, ref move);
                    break;
                case 'V':
                    result += 5;
                    move = 1;
                    break;
                case 'X':
                    result += PreX(i, s, ref move);
                    break;
                case 'L':
                    result += 50;
                    move = 1;
                    break;
                case 'C':
                    result += PreC(i, s, ref move);
                    break;
                case 'D':
                    result += 500;
                    move = 1;
                    break;
                case 'M':
                    result += 1000;
                    move = 1;
                    break;
            }
            i += move;
        }
        return result;
    }
    private int PreI(int index, string s, ref int move)
    {
        //I  1
        //IV 4
        //IX 9
        //II 2
        int result = 0;
        int count = s.Length;
        if (index + 1 < count)
        {
            char c = s[index + 1];
            switch (c)
            {
                case 'V':
                    result = 4;
                    move = 2;
                    break;
                case 'X':
                    result = 9;
                    move = 2;
                    break;
                case 'I':
                    result = 2;
                    move = 2;
                    break;
            }
        }
        else
        {
            result = 1;
            move = 1;
        }
        return result;
    }

    private int PreX(int index, string s, ref int move)
    {
        //X 10
        //XL 40
        //XC 90
        int result = 0;
        int count = s.Length;
        if (index + 1 < count)
        {
            char c = s[index + 1];
            switch (c)
            {
                case 'L':
                    result = 40;
                    move = 2;
                    break;
                case 'C':
                    result = 90;
                    move = 2;
                    break;
                default:
                    result = 10;
                    move = 1;
                    break;
            }
        }
        else
        {
            result = 10;
            move = 1;
        }
        return result;
    }

    private int PreC(int index, string s, ref int move)
    {
        //C 100
        //CD 400
        //CM 900
        int result = 0;
        int count = s.Length;
        if (index + 1 < count)
        {
            char c = s[index + 1];
            switch (c)
            {
                case 'D':
                    result = 400;
                    move = 2;
                    break;
                case 'M':
                    result = 900;
                    move = 2;
                    break;
                default:
                    result = 100;
                    move = 1;
                    break;
            }
        }
        else
        {
            result = 100;
            move = 1;
        }
        return result;
    }
}
```

---
## 题目04：最长公共前缀

> - 题号：14
> - 难度：简单
> - https://leetcode-cn.com/problems/longest-common-prefix/

编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。

<b>示例 1</b>:
```c
输入: ["flower","flow","flight"]
输出: "fl"
```

<b>示例 2</b>:
```c
输入: ["dog","racecar","car"]
输出: ""
解释: 输入不存在公共前缀。
```

<b>说明</b>:

所有输入只包含小写字母 a-z。


**参考代码**：

- 状态：通过
- 执行用时: 144 ms, 在所有 C# 提交中击败了 94.92% 的用户
- 内存消耗: 23.4 MB, 在所有 C# 提交中击败了 11.69% 的用户


```c
public class Solution {
    public string LongestCommonPrefix(string[] strs) 
    {
        if (strs.Length == 0)
            return string.Empty;

        string result = strs[0];
        for (int i = 1; i < strs.Length; i++)
        {
            result = Prefix(result, strs[i]);
            if (string.IsNullOrEmpty(result))
                break;
        }
        return result;        
    }
    
    public string Prefix(string str1, string str2)
    {
        int len1 = str1.Length;
        int len2 = str2.Length;
        int len = Math.Min(len1, len2);
        int i = 0;
        for (; i < len; i++)
        {
            if (str1[i] != str2[i])
                break;
        }
        return i == 0 ? string.Empty : str1.Substring(0, i);
    }    
}
```

---
## 题目05：反转字符串

> - 题号：344
> - 难度：简单
> - https://leetcode-cn.com/problems/reverse-string/



编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。

不要给另外的数组分配额外的空间，你必须原地 <b>修改输入数组</b>、使用 O(1) 的额外空间解决这一问题。

你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。


<b>示例 1</b>：
```c
输入：["h","e","l","l","o"]
输出：["o","l","l","e","h"]
```

<b>示例 2</b>：
```c
输入：["H","a","n","n","a","h"]
输出：["h","a","n","n","a","H"]
```



**参考代码：**

- 状态：通过
- 执行用时: 572 ms, 在所有 C# 提交中击败了 94.94% 的用户
- 内存消耗: 33.6 MB, 在所有 C# 提交中击败了 5.05% 的用户

```c
public class Solution {
    public void ReverseString(char[] s) {
        int i = 0;
        int j = s.Length-1;
        while (i < j)
        {
            char c = s[i];
            s[i] = s[j];
            s[j] = c;
            i++;
            j--;
        }
    }
}
```

---
## 题目06：无重复字符的最长子串

> - 题号：3
> - 难度：中等
> - https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/

给定一个字符串，请你找出其中不含有重复字符的 **最长子串** 的长度。

**示例 1:**

```c
输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
```

**示例 2:**

```c
输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
```

**示例 3:**
```c
输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
```

**思路：**

借助动态规划的思路，从前到后求出以每个位置为终止位置，所构成无重复子串的长度，之后求这些长度的最大值即可。

对于任意位置`index`其最长无重复子串的长度为`result[index] = min{k1,k2}`，`k1 = result[index-1] + 1`，`k2`为从`index`位置往前推直到出现`index`位置的字符或`index=0`为止的子串长度。


**参考代码：**

- 执行结果：通过
- 执行用时：96 ms, 在所有 C# 提交中击败了 82.81% 的用户
- 内存消耗：25.2 MB, 在所有 C# 提交中击败了 25.52% 的用户

```c
public class Solution
{
    public int LengthOfLongestSubstring(string s)
    {
        if (string.IsNullOrEmpty(s))
            return 0;
        int[] result = new int[s.Length];
        result[0] = 1;

        for (int i = 1; i < s.Length; i++)
        {
            int count = GetLength(i, s);
            result[i] = result[i-1] < count ? result[i-1]+1 : count;
        }
        return result.Max();
    }
    private int GetLength(int index,string s)
    {
        char c = s[index];
        int result = 1;
        for (int i = index-1; i >= 0; i--)
        {
            if (s[i] != c)
                result += 1;
            else
                break;
        }
        return result;
    }
}
```

---
## 题目07：最长回文子串

> - 题号：5
> - 难度：中等
> - https://leetcode-cn.com/problems/longest-palindromic-substring/


给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

<b>示例 1</b>：
```c
输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。
```

<b>示例 2</b>：
```c
输入: "cbbd"
输出: "bb"
```

<b>示例 3</b>：
```c
输入: "a"
输出: "a"
```

**思路：** 利用动态规划的方法。

回文是一个正读和反读都相同的字符串，例如，“aba”是回文，而“abc”不是。

动态规划算法与分治法类似，其基本思想也是将待求解问题分解成若干个子问题，先求解子问题，然后从这些子问题的解得到原问题的解。

与分治法不同的是，<u>适合于用动态规划求解的问题，经分解得到子问题往往不是互相独立的（即下一个子阶段的求解是建立在上一个子阶段的解的基础上，进行进一步的求解）</u>。若用分治法来解这类问题，则分解得到的子问题数目太多，有些子问题被重复计算了很多次。如果我们能够<u>保存已解决的子问题的答案，而在需要时再找出已求得的答案，这样就可以避免大量的重复计算，节省时间</u>。

我们可以用一个表来记录所有已解的子问题的答案。不管该子问题以后是否被用到，只要它被计算过，就将其结果填入表中。这就是动态规划法的基本思路。

具体的动态规划算法多种多样，但它们具有相同的<u>填表格式</u>。

使用记号 `s[l, r]` 表示原始字符串的一个子串，`l`、`r` 分别是区间的左右边界的索引值，使用左闭、右闭区间表示左右边界可以取到。

`dp[l, r]` 表示子串 `s[l, r]`（包括区间左右端点）是否构成回文串，是一个二维布尔型数组。

- 当子串只包含 1 个字符，它一定是回文子串；
- 当子串包含 2 个以上字符的时候：`s[l, r]` 是一个回文串，那么这个回文串两边各往里面收缩一个字符（如果可以的话）的子串 `s[l + 1, r - 1]` 也一定是回文串。

故，当 `s[l] == s[r]` 成立的时候，`dp[l, r]`的值由 `dp[l + 1, r - l]` 决定，这里还需要再多考虑一点点：“原字符串去掉左右边界”的子串的边界情况。

- 当原字符串的元素个数为 3 的时候，如果左右边界相等，那么去掉它们以后，只剩下 1 个字符，它一定是回文串，故原字符串也一定是回文串；
- 当原字符串的元素个数为 2 的时候，如果左右边界相等，那么去掉它们以后，只剩下 0 个字符，显然原字符串也一定是回文串。

综上，如果一个字符串的左右边界相等，判断为回文只需以下二者之一成立即可：
- 去掉左右边界以后的字符串不构成区间，即`s[l + 1, r - 1]`包含元素少于2个，即：`r - l <= 2`。
- 去掉左右边界以后的字符串是回文串，即`dp[l + 1, r - 1] == true`。

![](https://img-blog.csdnimg.cn/20200317155934443.png)

**参考代码**：

- 状态：通过
- 执行用时: 232 ms, 在所有 C# 提交中击败了 46.79% 的用户
- 内存消耗: 40.9 MB, 在所有 C# 提交中击败了 5.43% 的用户


```c
public class Solution {
    public string LongestPalindrome(string s) 
    {
        if (string.IsNullOrEmpty(s))
            return string.Empty;
        int len = s.Length;
        if (len == 1)
            return s;
        int longestPalindromelen = 1;
        string longestPalindromeStr = s.Substring(0, 1);
        bool[,] dp = new bool[len, len];

        for (int r = 1; r < len; r++)
        {
            for (int l = 0; l < r; l++)
            {
                if (s[r] == s[l] && (r - l <= 2 || dp[l + 1, r - 1] == true))
                {
                    dp[l, r] = true;
                    if (longestPalindromelen < r - l + 1)
                    {
                        longestPalindromelen = r - l + 1;
                        longestPalindromeStr = s.Substring(l, r - l + 1);
                    }
                }
            }
        }
        return longestPalindromeStr;        
    }
}
```


---
## 题目08：字符串相乘

> - 题号：43
> - 难度：中等
> - https://leetcode-cn.com/problems/multiply-strings/


给定两个以字符串形式表示的非负整数`num1`和`num2`，返回`num1`和`num2`的乘积，它们的乘积也表示为字符串形式。

<b>示例 1</b>:
```c
输入: num1 = "2", num2 = "3"
输出: "6"
```

<b>示例 2</b>:
```c
输入: num1 = "123", num2 = "456"
输出: "56088"
```



<b>示例 3</b>:
```c
输入: num1 = "498828660196", num2 = "840477629533"
输出: "419254329864656431168468"
```


<b>说明</b>：

- `num1`和`num2`的长度小于110。
- `num1`和`num2`只包含数字 0-9。
- `num1`和`num2`均不以零开头，除非是数字 0 本身。
- 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。


**参考代码**：

- 状态：通过
- 执行用时：132 ms, 在所有 C# 提交中击败了 94.74% 的用户
- 内存消耗：24.1 MB, 在所有 C# 提交中击败了 31.82% 的用户

```c
public class Solution {
    public string Multiply(string num1, string num2) {
        if (num1 == "0" || num2 == "0")
            return "0";
        int len1 = num1.Length;
        int len2 = num2.Length;
        int len = len1 + len2;
        int[] temp = new int[len];

        for (int i = len2 - 1; i >= 0; i--)
        {
            int k = len2 - i;
            int b = num2[i] - '0';
            for (int j = len1 - 1; j >= 0; j--)
            {
                int a = num1[j] - '0';
                int c = a*b;

                temp[len - k] += c%10;
                if (temp[len - k] >= 10)
                {
                    temp[len - k] = temp[len - k]%10;
                    temp[len - k - 1]++;
                }

                temp[len - k - 1] += c/10;
                if (temp[len - k - 1] >= 10)
                {
                    temp[len - k - 1] = temp[len - k - 1]%10;
                    temp[len - k - 2]++;
                }
                k++;
            }
        }

        StringBuilder sb = new StringBuilder();
        int s = temp[0] == 0 ? 1 : 0;
        while (s < len)
        {
            sb.Append(temp[s]);
            s++;
        }
        return sb.ToString();        
    }
}
```






---
## 题目09：正则表达式匹配

> - 题号：10
> - 难度：困难
> - https://leetcode-cn.com/problems/regular-expression-matching/

给你一个字符串`s`和一个字符规律`p`，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

```c
'.' 匹配任意单个字符
'*' 匹配零个或多个前面的那一个元素
```

所谓匹配，是要涵盖 **整个** 字符串`s`的，而不是部分字符串。

**说明:**

- `s`可能为空，且只包含从 a-z 的小写字母。
- `p`可能为空，且只包含从 a-z 的小写字母，以及字符 '.' 和 '*'。

**示例 1:**

```c
输入: 
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
```
**示例 2:**

```c
输入:
s = "aa"
p = "a*"
输出: true
解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
```

**示例 3:**

```c
输入:
s = "ab"
p = ".*"
输出: true
解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
```

**示例 4:**

```c
输入:
s = "aab"
p = "c*a*b"
输出: true
解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
```

**示例 5:**

```c
输入:
s = "mississippi"
p = "mis*is*p*."
输出: false
```

**思路**：回溯法。

这种匹配思路其实就是不断地减掉`s`和`p`的可以匹配首部，直至一个或两个字符串被减为空的时候，根据最终情况来得出结论。

如果只是两个普通字符串进行匹配，按序遍历比较即可：

```c
if (s[i] == p[i])
```

如果正则表达式字符串p只有一种"."一种特殊标记，依然是按序遍历比较即可 ：

```c
if (s[i] == p[i] || p[i] == '.')
```

上述两种情况实现时还需要判断字符串长度和字符串判空的操作。

但是，"*"这个特殊字符需要特殊处理，当p的第i个元素的下一个元素是星号时会有两种情况：

- `i`元素需要出现0次，我们就保持`s`不变，将`p`的减掉两个元素，调用`IsMatch`。例如`s：bc、p：a*bc`，我们就保持`s`不变，减掉`p`的"a*"，调用`IsMatch(s:bc,p:bc)`。
- `i`元素需要出现一次或更多次，先比较`i`元素和`s`首元素，相等则保持`p`不变，`s`减掉首元素，调用`IsMatch`。例如`s：aabb、p：a*bb`，就保持`p`不变，减掉`s`的首元素，调用`IsMatch(s:abb,p:a*bb)`。

此时存在一些需要思考的情况，例如`s：abb、p：a*abb`，会用两种方式处理：

- 按照上述第二种情况比较`i`元素和`s`首元素，发现相等就会减掉`s`的首字符，调用`IsMatch(s:bb,p:a*abb)`。在按照上述第一种情况减去`p`的两个元素，调用`IsMatch(s:bb,p:abb)`，最终导致`false`。
- 直接按照上述第一种情况减去`p`的两个元素，调用`IsMatch(s:abb,p:abb)`，最终导致`true`。

所以说这算是一种暴力方法，会将所有的情况走一边，看看是否存在可以匹配的情况。




**参考代码**：

- 执行结果：通过
- 执行用时：768 ms, 在所有 C# 提交中击败了 10.69% 的用户
- 内存消耗：25.6 MB, 在所有 C# 提交中击败了 5.00% 的用户

```c
public class Solution
{
    public bool IsMatch(string s, string p)
    {
        //若正则串p为空串，则s为空串匹配成功，s不为空串匹配失败。
        if (string.IsNullOrEmpty(p))
            return string.IsNullOrEmpty(s) ? true : false;

        //判断s和p的首字符是否匹配，注意要先判断s不为空
        bool headMatched = string.IsNullOrEmpty(s) == false
            && (s[0] == p[0] || p[0] == '.');

        if (p.Length >= 2 && p[1] == '*')
        {
            //如果p的第一个元素的下一个元素是*，则分别对两种情况进行判断
            return IsMatch(s, p.Substring(2)) ||
                (headMatched && IsMatch(s.Substring(1), p));
        }
        else if (headMatched)
        {
            //否则，如果s和p的首字符相等
            return IsMatch(s.Substring(1), p.Substring(1));
        }
        else
        {
            return false;
        }
    }
}
```

## 题目10：通配符匹配


> - 题号：44
> - 难度：困难
> - https://leetcode-cn.com/problems/wildcard-matching/


给定一个字符串(`s`) 和一个字符模式(`p`) ，实现一个支持`'?'`和`'*'`的通配符匹配。

```
'?' 可以匹配任何单个字符。
'*' 可以匹配任意字符串（包括空字符串）。
两个字符串完全匹配才算匹配成功。
```

**说明:**

- `s`可能为空，且只包含从`a-z`的小写字母。
- `p`可能为空，且只包含从`a-z`的小写字母，以及字符`?`和`*`。

**示例 1:**

```c
输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
```
**示例 2:**

```c
输入:
s = "aa"
p = "*"
输出: true
解释: '*' 可以匹配任意字符串。
```

**示例 3:**

```c
输入:
s = "cb"
p = "?a"
输出: false
解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
```

**示例 4:**

```c
输入:
s = "adceb"
p = "*a*b"
输出: true
解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
```

**示例 5:**

```c
输入:
s = "acdcb"
p = "a*c?b"
输出: false
```

**示例 6:**

```c
输入：
"abefcdgiescdfimde"
"ab*cd?i*de"
输出：true
```

**示例 7:**

```c
输入：
"aaaa"
"***a"
输出：true
```


**思路**：双索引法。

我们用`i`和`j`分别标记`s`和`p`的第一个字符下标，即都初始化为0。用`istart`和`jstart`分别标记`s`和`p`中`'*'`匹配过的位置，即初始化为`-1`。

和普通字符串匹配的思路差不多，已经匹配成功的部分就不再考虑了，所以要用`i`和`j`标记当前正在比较的字符；但是最近匹配过的`'*'`可能会被重复使用去匹配更多的字符，所以我们要用`istart`和`jstart`分别标记`s`和`p`中最近匹配过`'*'`的位置。

1. 如果`i`和`j`标记的字符正好相等或者`j`字符是`'?'`匹配成功，则"移除"`i`和`j`元素，即自增`i`、`j`。
2. 否则如果`j`字符是`'*'`依然可以匹配成功，则用`istart`和`jstart`分别标记`i`元素和`j`元素，自增`j`。
3. 再否则如果`istart>-1`说明之前匹配过`'*'`，因为`'*'`可以匹配多个字符，所以这里要再次利用这个最近匹配过的`'*'`匹配更多的字符，移动`i`标记`istart`的下一个字符，再让`istart`重新标记`i`元素，同时移动`j`标记`jstart`的下一个字符。
4. 上述三种情况都不满足，则匹配失败，返回`false`。

最后当`s`中的字符都判断完毕，则认为`s`为空，此时需要`p`为空或者`p`中只剩下星号的时候，才能成功匹配。

**参考代码**：

- 执行结果：通过
- 执行用时：92 ms, 在所有 C# 提交中击败了 95.00% 的用户
- 内存消耗：25.7 MB, 在所有 C# 提交中击败了 66.67% 的用户

```c
public class Solution
{
    public bool IsMatch(string s, string p)
    {
        //若正则串p为空串，则s为空串匹配成功，s不为空串匹配失败。
        if (string.IsNullOrEmpty(p))
            return string.IsNullOrEmpty(s) ? true : false;

        int i = 0, j = 0, istart = -1, jstart = -1, plen = p.Length;

        //判断s的所有字符是否匹配
        while (i < s.Length)
        {
            //三种匹配成功情况以及匹配失败返回false
            if (j < plen && (s[i] == p[j] || p[j] == '?'))
            {
                i++;
                j++;
            }
            else if (j < plen && p[j] == '*')
            {
                istart = i;
                jstart = j;
                j++;
            }
            else if (istart > -1)
            {
                i = istart + 1;
                istart = i;
                j = jstart + 1;
            }
            else
            {
                return false;
            }
        }
        //s中的字符都判断完毕，则认为s为空
        //此时需要p为空或者p中只剩下星号的时候，才能成功匹配。
        //如果p中剩余的都是*，则可以移除剩余的*
        while (j < plen && p[j] == '*')
        {
            j++;
        }
        return j == plen;
    }
}
```

