# Leetcode同步练习（四）

## 题目01：最小栈

> - 题号：155
> - 难度：简单
> - https://leetcode-cn.com/problems/min-stack/

设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。

- push(x) -- 将元素 x 推入栈中。
- pop() -- 删除栈顶的元素。
- top() -- 获取栈顶元素。
- getMin() -- 检索栈中的最小元素。

<b>示例</b>:

```c
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.getMin();   --> 返回 -2.
```

**思路**：利用辅助栈的方式。

**参考代码**：

- 状态：通过
- 执行用时: 192 ms, 在所有 C# 提交中击败了 96.43% 的用户
- 内存消耗: 33.5 MB, 在所有 C# 提交中击败了 13.63% 的用户

```c
public class MinStack
{
    /** initialize your data structure here. */
    private readonly Stack<int> _stack;
    private readonly Stack<int> _stackMin;

    public MinStack()
    {
        _stack = new Stack<int>();
        _stackMin = new Stack<int>();
    }

    public void Push(int x)
    {
        _stack.Push(x);
        if (_stackMin.Count == 0 || _stackMin.Peek() >= x)
        {
            _stackMin.Push(x);
        }
    }

    public void Pop()
    {
        int x = _stack.Pop();
        if (_stackMin.Peek() == x)
        {
            _stackMin.Pop();
        }
    }

    public int Top()
    {
        return _stack.Peek();
    }

    public int GetMin()
    {
        return _stackMin.Peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.Push(x);
 * obj.Pop();
 * int param_3 = obj.Top();
 * int param_4 = obj.GetMin();
 */
```




---
## 题目02：有效的括号

> - 题号：20
> - 难度：简单
> - https://leetcode-cn.com/problems/valid-parentheses/

给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：

- 左括号必须用相同类型的右括号闭合。
- 左括号必须以正确的顺序闭合。

注意空字符串可被认为是有效字符串。

<b>示例 1</b>:
```c
输入: "()"
输出: true
```

<b>示例 2</b>:
```c
输入: "()[]{}"
输出: true
```

<b>示例 3</b>:
```c
输入: "(]"
输出: false
```

<b>示例 4</b>:
```c
输入: "([)]"
输出: false
```

<b>示例 5</b>:
```c
输入: "{[]}"
输出: true
```

<b>示例 6</b>:
```c
输入: ""
输出: true
```



<b>示例 7</b>:
```c
输入: "(("
输出: false
```

**思路**：利用栈。

首先判断该字符串长度的奇偶性，如果是奇数，则返回false。否则，利用栈先进后出的特点，遇到“{”，“[”，“(”进行入栈操作，遇到“}”，“]”，“)”就与栈顶元素进行比较，如果是对应括号则出栈，否则返回false。

**参考代码**：

- 执行结果：通过
- 执行用时：88 ms, 在所有 C# 提交中击败了 70.31% 的用户
- 内存消耗：22 MB, 在所有 C# 提交中击败了 17.91% 的用户


```c
public class Solution {
    public bool IsValid(string s) 
    {
        if (string.IsNullOrEmpty(s))
            return true;

        int count = s.Length;
        if(count%2 == 1)
            return false;
            
        Stack<char> stack = new Stack<char>();
        for (int i = 0; i < count; i++)
        {
            char c = s[i];
            if (stack.Count == 0)
            {
                stack.Push(c);
            }
            else if(c == '[' || c == '{' || c == '(')
            {
                stack.Push(c);
            }
            else if (stack.Peek() == GetPair(c))
            {
                stack.Pop();
            }
            else
            {
                return false;
            }
        }
        return stack.Count == 0;        
    }
    
    public static char GetPair(char c)
    {
        if (c == ')')
            return '(';
        if (c == '}')
            return '{';
        if (c == ']')
            return '[';
        return '\0';
    }    
}
```

---
## 题目03：用队列实现栈

> - 题号：225
> - 难度：简单
> - https://leetcode-cn.com/problems/implement-stack-using-queues/

使用队列实现栈的下列操作：

- `push(x)` -- 元素 x 入栈
- `pop()` -- 移除栈顶元素
- `top()` -- 获取栈顶元素、
- `empty()` -- 返回栈是否为空

**注意:**

- 你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size, 和 is empty 这些操作是合法的。
- 你所使用的语言也许不支持队列。你可以使用 `list` 或者 `deque`（双端队列）来模拟一个队列, 只要是标准的队列操作即可。
- 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 `pop` 或者 `top` 操作）。


**思路**：所谓实现栈就是在入队或出队时循环前n-1个节点，从队首出队后立即再加入队排在队尾，即实现队反序。

**参考代码：**

- 执行结果：通过
- 执行用时：124 ms, 在所有 C# 提交中击败了 56.21% 的用户
- 内存消耗：23.4 MB, 在所有 C# 提交中击败了 63.73% 的用户

```c
public class MyStack
{
    Queue<int> queue = new Queue<int>();

    /** Initialize your data structure here. */
    public MyStack()
    {
    }

    /** Push element x onto stack. */
    public void Push(int x)
    {
        queue.Enqueue(x);
        for (int i = 0; i < queue.Count - 1; i++)
        {
            queue.Enqueue(queue.Dequeue());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int Pop()
    {
        return queue.Count > 0 ? queue.Dequeue() : -1;
    }

    /** Get the top element. */
    public int Top()
    {
        return queue.Count > 0 ? queue.Peek() : -1;
    }

    /** Returns whether the stack is empty. */
    public bool Empty()
    {
        return queue.Count == 0;
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.Push(x);
 * int param_2 = obj.Pop();
 * int param_3 = obj.Top();
 * bool param_4 = obj.Empty();
 */
```


---
## 题目04：整数反转

> - 题号：7
> - 难度：简单
> - https://leetcode-cn.com/problems/reverse-integer/


给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

<b>示例 1</b>:
```python
输入: 123
输出: 321
```

<b>示例 2</b>:
```python
输入: -123
输出: -321
```

<b>示例 3</b>:
```python
输入: 120
输出: 21
```
<b>示例 4</b>:
```python
输入: 2147483647（2^31 ? 1 = int.MaxValue）
输出: 0
```
<b>示例 5</b>:
```python
输入: -2147483648（?2^31 = int.MinValue）
输出: 0
```


<b>注意</b>：

假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为`[?2^31, 2^31 ? 1]`。请根据这个假设，如果反转后整数溢出那么就返回 0。


**思路**：通过队列的方式，把负数转换为正数，通过“队列”统一处理。

**参考代码**：

- 状态：通过
- 执行用时: 56 ms, 在所有 C# 提交中击败了 93.28% 的用户
- 内存消耗: 13.9 MB, 在所有 C# 提交中击败了 13.98% 的用户

```c
public class Solution {
    public int Reverse(int x) {
        if (x == int.MinValue)
            return 0;

        long result = 0;
        int negative = x < 0 ? -1 : 1;
        x = negative * x;

        Queue<int> q = new Queue<int>();
        while (x != 0)
        {
            q.Enqueue(x % 10);
            x = x/10;
        }
            
        while (q.Count != 0)
        {
            result += q.Dequeue()*(long) Math.Pow(10, q.Count);
            if (negative == 1 && result > int.MaxValue)
            {
                result = 0;
                break;
            }
            if (negative == -1 && result*-1 < int.MinValue)
            {
                result = 0;
                break;
            }
        }
        return (int)result*negative;        
    }
}
```

---
## 题目05：逆波兰表达式求值

> - 题号：150
> - 难度：中等
> - https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/

根据逆波兰表示法，求表达式的值。

有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。

说明：

- 整数除法只保留整数部分。
- 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。

<b>示例 1</b>：

```c
输入: ["2", "1", "+", "3", "*"]
输出: 9
解释: ((2 + 1) * 3) = 9
```

<b>示例 2</b>：

```c
输入: ["4", "13", "5", "/", "+"]
输出: 6
解释: (4 + (13 / 5)) = 6
```

<b>示例 3</b>：

```c
输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
输出: 22
解释: 
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
```



**思路**：利用栈后进先出的特性进行算法设计与实现。

**参考代码**：

- 执行结果：通过
- 执行用时：100 ms, 在所有 C# 提交中击败了 100.00% 的用户
- 内存消耗：25 MB, 在所有 C# 提交中击败了 69.81% 的用户

```c
public class Solution {
    public int EvalRPN(string[] tokens) 
    {
        Stack<int> stack = new Stack<int>();
        for (int i = 0; i < tokens.Length; i++)
        {
            string str = tokens[i];
            if (str == "+" || str == "-" || str == "*" || str == "/")
            {
                int b = stack.Pop();
                int a = stack.Pop();
                int c = Compute(a, b, str);
                stack.Push(c);
            }
            else
            {
                stack.Push(int.Parse(str));
            }
        }
        return stack.Pop();        
    }
    
    public static int Compute(int a, int b, string oper)
    {
        int result = 0;
        switch (oper)
        {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
        }
        return result;
    }    
}
```


---
## 题目06：全排列

> - 题号：46
> - 难度：中等
> - https://leetcode-cn.com/problems/permutations/

给定一个没有重复数字的序列，返回其所有可能的全排列。

<b>示例</b>:
```c
输入: [1,2,3]
输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
```



<b>思路：回溯法（back tracking）</b> 是一种选优搜索法，又称为试探法，按选优条件向前搜索，以达到目标。但当探索到某一步时，发现原先选择并不优或达不到目标，就退回一步重新选择，这种走不通就退回再走的技术为回溯法，而满足回溯条件的某个状态的点称为“回溯点”。

> 白话：回溯法可以理解为通过选择不同的岔路口寻找目的地，一个岔路口一个岔路口的去尝试找到目的地。如果走错了路，继续返回来找到岔路口的另一条路，直到找到目的地。

本练习的回溯过程如下所示：


![回溯过程](https://img-blog.csdnimg.cn/20201009214444744.png)

**参考代码：**

- 状态：通过
- 执行用时: 364 ms, 在所有 C# 提交中击败了 80.00% 的用户
- 内存消耗: 30.6 MB, 在所有 C# 提交中击败了 7.14% 的用户

```c
public class Solution
{
    private IList<IList<int>> _result;
    private bool[] _used;

    public IList<IList<int>> Permute(int[] nums)
    {
        _result = new List<IList<int>>();
        if (nums == null || nums.Length == 0)
            return _result;
        _used = new bool[nums.Length];

        FindPath(nums, 0, new List<int>());
        return _result;
    }

    public void FindPath(int[] nums, int count, List<int> path)
    {
        if (count == nums.Length)
        {
            //递归终止条件
            List<int> item = new List<int>();
            item.AddRange(path);
            //加入拷贝
            _result.Add(item);
            return;
        }
        for (int i = 0; i < nums.Length; i++)
        {
            if (_used[i] == false)
            {
                path.Add(nums[i]);
                _used[i] = true;
                FindPath(nums, count + 1, path);
                path.RemoveAt(path.Count - 1);
                _used[i] = false;
            }
        }
    }
}
```




---
## 题目07：字符串转换整数 (atoi)

> - 题号：8
> - 难度：中等
> - https://leetcode-cn.com/problems/string-to-integer-atoi/

请你来实现一个 `atoi` 函数，使其能将字符串转换成整数。

首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。

当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。

该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。

注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。

在任何情况下，若函数不能进行有效的转换时，请返回 0。

<b>说明</b>：

假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 `[?2^31, 2^31? 1]`。如果数值超过这个范围，请返回 `INT_MAX (2^31? 1)` 或 `INT_MIN (?2^31)` 。

<b>示例 1</b>:
```c
输入: "42"
输出: 42
```

<b>示例 2</b>:
```c
输入: "   -42"
输出: -42
解释: 第一个非空白字符为 '-', 它是一个负号。
我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
```
<b>示例 3</b>:
```c
输入: "4193 with words"
输出: 4193
解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
```

<b>示例 4</b>:
```c
输入: "words and 987"
输出: 0
解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
因此无法执行有效的转换。
```

<b>示例 5</b>:
```c
输入: "-91283472332"
输出: -2147483648
解释: 数字 "-91283472332" 超过 32 位有符号整数范围。 
因此返回 INT_MIN (?231) 。
```


<b>示例 6</b>:
```c
输入: "  0000000000012345678"
输出: 12345678
```

<b>示例 7</b>:
```c
输入: "20000000000000000000"
输出: 2147483647
```

**思路**：通过队列的方式。

**参考代码**：

- 状态：通过
- 执行用时: 104 ms, 在所有 C# 提交中击败了 98.32% 的用户
- 内存消耗: 24.3 MB, 在所有 C# 提交中击败了 24.45% 的用户

```c
public class Solution {
    public int MyAtoi(string str) {
        str = str.Trim();
        if (string.IsNullOrEmpty(str))
            return 0;

        if (str[0] != '-' && str[0] != '+')
        {
            if (str[0] < '0' || str[0] > '9')
                return 0;
        }
        int negative = 1;
        long result = 0;
        Queue<int> q = new Queue<int>();
        for (int i = 0; i < str.Length; i++)
        {
            if (str[i] == '-' && i == 0)
            {
                negative = -1;
                continue;
            }
            if (str[i] == '+' && i == 0)
            {
                continue;
            }
            if (str[i] < '0' || str[i] > '9')
            {
                break;
            }
            q.Enqueue(str[i] - '0');
        }

        while (q.Count != 0)
        {
            int i = q.Dequeue();

            //去掉队列前端的零
            if (i == 0 && result == 0)
                continue;
                
            // 返回超过位数的数字
            if (negative == 1 && q.Count > 10)
            {
                return int.MaxValue;
            }
            if (negative == -1 && q.Count > 10)
            {
                return int.MinValue;
            }

            result += i * (long)Math.Pow(10, q.Count);
            if (negative == 1 && result > int.MaxValue)
            {
                return int.MaxValue;
            }
            if (negative == -1 && result * -1 < int.MinValue)
            {
                return int.MinValue;
            }
        }
        return (int)result * negative;        
    }
}
```

---
## 题目08：设计循环双端队列

> - 题号：641
> - 难度：中等
> - https://leetcode-cn.com/problems/design-circular-deque/

设计实现双端队列。

你的实现需要支持以下操作：

- `MyCircularDeque(k)`：构造函数,双端队列的大小为k。
- `insertFront()`：将一个元素添加到双端队列头部。 如果操作成功返回 true。
- `insertLast()`：将一个元素添加到双端队列尾部。如果操作成功返回 true。
- `deleteFront()`：从双端队列头部删除一个元素。 如果操作成功返回 true。
- `deleteLast()`：从双端队列尾部删除一个元素。如果操作成功返回 true。
- `getFront()`：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
- `getRear()`：获得双端队列的最后一个元素。如果双端队列为空，返回 -1。
- `isEmpty()`：检查双端队列是否为空。
- `isFull()`：检查双端队列是否满了。

<b>示例</b>：

```c
MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
circularDeque.insertLast(1);			        // 返回 true
circularDeque.insertLast(2);			        // 返回 true
circularDeque.insertFront(3);			        // 返回 true
circularDeque.insertFront(4);			        // 已经满了，返回 false
circularDeque.getRear();  				// 返回 2
circularDeque.isFull();				        // 返回 true
circularDeque.deleteLast();			        // 返回 true
circularDeque.insertFront(4);			        // 返回 true
circularDeque.getFront();				// 返回 4
```

<b>提示</b>：

- 所有值的范围为 [1, 1000]
- 操作次数的范围为 [1, 1000]
- 请不要使用内置的双端队列库。


**参考代码**：

- 执行结果：通过
- 执行用时：160 ms, 在所有 C# 提交中击败了 90.48% 的用户
- 内存消耗：34.7 MB, 在所有 C# 提交中击败了 7.14% 的用户


```c
public class MyCircularDeque 
{
    private int _pFront;
    private int _pRear;
    private readonly int[] _dataset;
    private int _length;
    private int _maxSize;
    
    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) 
    {
        _dataset = new int[k];
        _length = 0;
        _maxSize = k;
        _pFront = 0;
        _pRear = 0;        
    }
    
    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public bool InsertFront(int value) 
    {
        if (_length == _maxSize)
            return false;

        _pFront = (_pFront - 1 + _maxSize)%_maxSize;
        _dataset[_pFront] = value;
        _length++;
        return true;        
    }
    
    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public bool InsertLast(int value) 
    {
        if (_length == _maxSize)
            return false;

        _dataset[_pRear] = value;
        _pRear = (_pRear + 1)%_maxSize;
        _length++;
        return true;        
    }
    
    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public bool DeleteFront() 
    {
        if (_length == 0)
            return false;
        _pFront = (_pFront + 1)%_maxSize;
        _length--;
        return true;        
    }
    
    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public bool DeleteLast() 
    {
        if (_length == 0)
            return false;
        _pRear = (_pRear - 1 + _maxSize)%_maxSize;
        _length--;
        return true;        
    }
    
    /** Get the front item from the deque. */
    public int GetFront() 
    {
        if (_length == 0)
            return -1;

        return _dataset[_pFront];        
    }
    
    /** Get the last item from the deque. */
    public int GetRear() 
    {
        if (_length == 0)
            return -1;
        int index = (_pRear - 1 + _maxSize)%_maxSize;
        return _dataset[index];        
    }
    
    /** Checks whether the circular deque is empty or not. */
    public bool IsEmpty() 
    {
        return _length == 0;        
    }
    
    /** Checks whether the circular deque is full or not. */
    public bool IsFull() 
    {
        return _length == _maxSize;        
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * bool param_1 = obj.InsertFront(value);
 * bool param_2 = obj.InsertLast(value);
 * bool param_3 = obj.DeleteFront();
 * bool param_4 = obj.DeleteLast();
 * int param_5 = obj.GetFront();
 * int param_6 = obj.GetRear();
 * bool param_7 = obj.IsEmpty();
 * bool param_8 = obj.IsFull();
 */
```





---
## 题目09：最长有效括号

> - 题号：32
> - 难度：困难
> - https://leetcode-cn.com/problems/longest-valid-parentheses/

给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。

<b>示例 1</b>:

```c
输入: "(()"
输出: 2
解释: 最长有效括号子串为 "()"
```

<b>示例 2</b>:
```c
输入: ")()())"
输出: 4
解释: 最长有效括号子串为 "()()"
```

<b>示例 3</b>:
```c
输入: ""
输出: 0
解释: 
```
<b>示例 4</b>:
```c
输入: "()(())"
输出: 6
解释: 最长有效括号子串为 "()(())"
```

**思路**：利用栈。

我们可以用栈在遍历给定字符串的过程中去判断到目前为止扫描的子字符串的有效性，同时计算最长有效字符串的长度。我们首先将 ?1 放入栈顶。

- 对于遇到的每个‘(’，我们将它的下标放入栈中。
- 对于遇到的每个‘)’，我们弹出栈顶的元素，判断有效性，计算有效长度。

**参考代码**：

- 状态：通过
- 执行用时：92 ms, 在所有 C# 提交中击败了 53.78% 的用户
- 内存消耗：23.5 MB, 在所有 C# 提交中击败了 7.14% 的用户

```c
public class Solution {
    public int LongestValidParentheses(string s) {
        Stack<int> stack = new Stack<int>();
        stack.Push(-1);
        int result = 0;
        for (int i = 0; i < s.Length; i++)
        {
            if (s[i] == '(')
            {
                stack.Push(i);
            }
            else
            {
                stack.Pop();
                if (stack.Count == 0)
                {
                    stack.Push(i);
                }
                else
                {
                    result = Math.Max(result, i - stack.First());
                }
            }
        }
        return result;
    }
}
```

---
## 题目10：滑动窗口最大值

> - 题号：239
> - 难度：困难
> - https://leetcode-cn.com/problems/sliding-window-maximum/

给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回滑动窗口中的最大值。

<b>示例</b>:

```c
输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7] 
解释: 

  滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
```

<b>提示</b>：

你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。

 

<b>进阶</b>：

你能在线性时间复杂度内解决此题吗？

**参考代码：**

<b>实现一</b>：暴力寻找每个窗口的最大值。


- 状态：通过
- 执行用时: 472 ms, 在所有 C# 提交中击败了 53.33% 的用户
- 内存消耗: 34.3 MB, 在所有 C# 提交中击败了 50.00% 的用户

```c
public class Solution {
    public int[] MaxSlidingWindow(int[] nums, int k) {
        int len = nums.Length;
        if (len == 0)
            return nums;

        int[] result = new int[len - k + 1];
        for (int i = 0; i < result.Length; i++)
        {
            result[i] = GetMax(nums, i, i + k);
        }
        return result;        
    }
    
    public int GetMax(int[] arr,int start,int end)
    {
        int max = int.MinValue;
        for (int i = start; i < end; i++)
        {
            if (arr[i] > max)
                max = arr[i];
        }
        return max;
    }    
}
```

<b>实现二</b>：如果想提升时间效率就需要使用结构了。

- 状态：通过
- 执行用时: 364 ms, 在所有 C# 提交中击败了 75.33% 的用户
- 内存消耗: 40.5 MB, 在所有 C# 提交中击败了 100.00% 的用户


**思路**：利用循环双端队列来记录当前窗口的信息，其中窗口中最大值的数组位置记录在队首元素中。该双端队列要保证队列中数组位置的数值由大到小排序。
- 超出窗口索引要删除队首元素。
- 大于队尾元素，要依次删除队尾元素，把该元素的位置插入到队尾。


```c
public class MyCircularDeque
{
    private int _pFront;
    private int _pRear;
    private readonly int[] _dataset;
    private int _length;
    private int _maxSize;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k)
    {
        _dataset = new int[k];
        _length = 0;
        _maxSize = k;
        _pFront = 0;
        _pRear = 0;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public bool InsertFront(int value)
    {
        if (_length == _maxSize)
            return false;

        _pFront = (_pFront - 1 + _maxSize) % _maxSize;
        _dataset[_pFront] = value;
        _length++;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public bool InsertLast(int value)
    {
        if (_length == _maxSize)
            return false;

        _dataset[_pRear] = value;
        _pRear = (_pRear + 1) % _maxSize;
        _length++;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public bool DeleteFront()
    {
        if (_length == 0)
            return false;
        _pFront = (_pFront + 1) % _maxSize;
        _length--;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public bool DeleteLast()
    {
        if (_length == 0)
            return false;
        _pRear = (_pRear - 1 + _maxSize) % _maxSize;
        _length--;
        return true;
    }

    /** Get the front item from the deque. */
    public int GetFront()
    {
        if (_length == 0)
            return -1;

        return _dataset[_pFront];
    }

    /** Get the last item from the deque. */
    public int GetRear()
    {
        if (_length == 0)
            return -1;
        int index = (_pRear - 1 + _maxSize) % _maxSize;
        return _dataset[index];

    }

    /** Checks whether the circular deque is empty or not. */
    public bool IsEmpty()
    {
        return _length == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public bool IsFull()
    {
        return _length == _maxSize;
    }
}

public class Solution {
    public int[] MaxSlidingWindow(int[] nums, int k) {
        int len = nums.Length;
        if (len == 0)
            return nums;
        int[] result = new int[len - k + 1];
        MyCircularDeque deque = new MyCircularDeque(k);

        for (int i = 0; i < len; i++)
        {
            // 判断当前队列中队首的值是否有效
            if (deque.IsEmpty() == false && deque.GetFront() <= i - k)
            {
                deque.DeleteFront();
            }

            // 保证从大到小 如果前面数小则需要依次弹出，直至满足要求
            while (deque.IsEmpty() == false && nums[deque.GetRear()] <= nums[i])
            {
                deque.DeleteLast();
            }
            //添加当前值对应的数组下标
            deque.InsertLast(i);

            // 当窗口长度为k时 保存当前窗口中最大值
            if (i + 1 >= k)
            {
                result[i + 1 - k] = nums[deque.GetFront()];
            }
        }
        return result;        
    }
}
```

---
<p style="text-align:center;">
<img src="https://img-blog.csdnimg.cn/20200727174102237.png">
</p>