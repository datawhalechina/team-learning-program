## 试题 I: 超级胶水

时间限制: 1.0s 内存限制: 256.0MB 本题总分：25 分

**【问题描述】**

小明有 n 颗石子，按顺序摆成一排。他准备用胶水将这些石子粘在一起。
每颗石子有自己的重量，如果将两颗石子粘在一起，将合并成一颗新的石
子，重量是这两颗石子的重量之和。
为了保证石子粘贴牢固，粘贴两颗石子所需要的胶水与两颗石子的重量乘
积成正比，本题不考虑物理单位，认为所需要的胶水在数值上等于两颗石子重
量的乘积。
每次合并，小明只能合并位置相邻的两颗石子，并将合并出的新石子放在
原来的位置。
现在，小明想用最少的胶水将所有石子粘在一起，请帮助小明计算最少需
要多少胶水。

**【输入格式】**

> 输入的第一行包含一个整数 n，表示初始时的石子数量。
>
> 第二行包含 n 个整数 w1,w2, · · · ,wn，依次表示每颗石子的重量。

**【输出格式】**

> 输出一行包含一个整数，表示最少需要的胶水数。

**【样例输入】**

```python
3
3 4 5
```

**【样例输出】**

```python
47
```

**【样例输入】**

```python
8
1 5 2 6 3 7 4 8
```

**【样例输出】**

```python
546
```

**【评测用例规模与约定】**

> 对于 20% 的评测用例，1 ≤ n ≤ 15。
>
> 对于 60% 的评测用例，1 ≤ n ≤ 100。
>
> 对于 80% 的评测用例，1 ≤ n ≤ 1000。
>
> 对于所有评测用例，1 ≤ n ≤ 100000，1 ≤ wi ≤ 1000。


```
n=int(input())
num_lis=list(map(int,input().strip().split()))
def f(num_lis):
    re=num_lis[0]*num_lis[1]
    re_index=0
    for i in range(len(num_lis)-1):
        if num_lis[i]*num_lis[i+1]<re:
            re= num_lis[i]*num_lis[i+1]
            re_index=i
    return re_index
res=0
while True:
    if len(num_lis)==1:
        break
    re_index=f(num_lis)
    
    if re_index+2<len(num_lis):
        res+=num_lis[re_index]*num_lis[re_index+1]
        num_lis=num_lis[:re_index]+[num_lis[re_index]+num_lis[re_index+1]]+num_lis[re_index+2:]
        
    else:
        res+=num_lis[re_index]*num_lis[re_index+1]
        num_lis=num_lis[:re_index]+[num_lis[re_index]+num_lis[re_index+1]]
        
print(res)
```

    8
    1 5 2 6 3 7 4 8
    546



```

```


```

```


```
def multiply( num1, num2):
    numDict = {'0':0, '1':1, '2':2, '3':3, '4':4, '5':5, '6':6, '7':7, '8':8, '9':9}
    l1 = list(num1)
    l2 = list(num2)
    n1 = 0
    n2 = 0
    bit1 = len(num1) - 1
    bit2 = len(num2) - 1
    for i in l1:
        for key in numDict.keys():
            if i == key:
                n1 += numDict[key] * 10**bit1
                bit1 -= 1
    for j in l2:
        for key in numDict.keys():
            if j == key:
                n2 += numDict[key] * 10**bit2
                bit2 -= 1
    return str(n1*n2)
def multiply( num1, num2):
    """
    :type num1: str
    :type num2: str
    :rtype: str
    """
    if num1=='0' or num2=='0':
        return '0'
    res = [0]*(len(num1)+len(num2))
    for index1,i in enumerate(num1[::-1]):
        for index2,j in enumerate(num2[::-1]):
            tmp = res[index1+index2] + int(i)*int(j)
            res[index1+index2] = tmp%10
            res[index1+index2+1] += tmp//10
                     
    result = ''
        
    for i in res[::-1]:
        result+=str(i)
        
                
    return result.lstrip('0')
def sum_(num_lis):
    sum_='1'
    for string in num_lis:
        sum_=multiply(sum_,string)
    return sum_
def run(num_lis):
    print('Input list',num_lis)
    print('The sum is',int(sum_(num_lis)))
def main(num_lis):
    run(num_lis)
main(['61431','1002'])

```

    Input list ['61431', '1002']
    The sum is 61553862



```
61431*1002
```




    61553862




```
def add_(num1,num2):
    a=list(map(int,list(num1)))
    b=list(map(int,list(num2)))
    a=a[::-1]
    b=b[::-1]
    a_point=0
    b_point=0
    carry=0
    re=[]
    while a_point<len(a) or b_point<len(b):
        if a_point<len(a):
            t1=a[a_point]
        else:
            t1=0
        if b_point<len(b):
            t2=b[b_point]
        else:
            t2=0
        t=t1+t2+carry
        re.append(str(t%10))
        carry=t//10
        a_point+=1
        b_point+=1
    if carry !=0:
        re.append('1')
    return ''.join(re[::-1])
def sum_(num_lis):
    sum_='0'
    for string in num_lis:
        sum_=add_(sum_,string)
    return sum_
def run(num_lis):
    print('Input list',num_lis)
    print('The sum is',int(sum_(num_lis)))
def main(num_lis):
    run(num_lis)
main(['61431','1002'])
```

    Input list ['61431', '1002']
    The sum is 62433



```

```


```
def main(num_lis):
    print('Input list',num_lis)
    print('The sum is',sum(map(int,num_lis)))
main(['61431','1002'])
```

    Input list ['61431', '1002']
    The sum is 62433



```

```


```
import random 
alpha_x=random.randint(0,9)
alpha_y=random.randint(0,9)
print(alpha_x)
print(alpha_y)
star_set=set()
while len(star_set)<8:
    x=random.randint(0,9)
    y=random.randint(0,9)
    if x==alpha_x and y==alpha_y:
        continue
    else:
        star_set.add((x,y))
print(' ',end='')
for i in range(10):
    print(' ',i,sep='',end='')
print()
for x in range(10):
    print(x,end='')
    for y in range(9):
        if x==alpha_x and y==alpha_y:
            print(' ','X',sep='',end='')
        
        elif (x,y) in star_set:
            print(' ','*',sep='',end='')
        else:
            print('  ',end='')
    if x==alpha_x and 9==alpha_y:
        print(' ',X,sep='')
    elif (x,9)in star_set:
        print(' ','*',sep='')
    else:
        print('  ')
            
        
```

    5
    2
      0 1 2 3 4 5 6 7 8 9
    0             *      
    1           *        
    2     *              
    3                    
    4                    
    5     X              
    6       *            
    7       *           *
    8           *       *
    9                    
