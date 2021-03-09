## 试题 E: 完美平方数
本题总分：15 分

**【问题描述】**

如果整个整数 X 本身是完全平方数，同时它的每一位数字也都是完全平方
数，我们就称 X 是完美平方数。
前几个完美平方数是 0、1、4、9、49、100、144……
即第 1 个完美平方数是 0，第 2 个是 1，第 3 个是 4，……
请你计算第 2020 个完美平方数是多少？

**【答案提交】**

这是一道结果填空题，你只需要算出结果后提交即可。本题的结果为一个
整数，在提交答案时只填写这个整数，填写多余的内容将无法得分。



```
visited={0}
def f(string):
    for i in string:
        if i not in {'0','1','4','9'}:
            return False
    return True
num=1
while True :
    if f(str(num**2)):
        visited.add(num**2)
        print(len(visited))
        if len(visited)==2020:
            print(num**2)
            break
    num+=1
#     491499994440019919104
```


```
# def f (x):
#     for i in range(int(x**(0.5))+1):
#         if i**2==x:
#             return True
# #     return False

# import math
# def is_sqr(n):
#     a = int(math.sqrt(n))
#     return a * a == n
# # Squares=set()
# # for i in range(20200000):
# #     Squares.add(i**2)
# visited={0,1,4,9}
# #num=50
# len_=1
# queue=[1,4,9]
# while queue:
#     num=queue.pop(0)
#     queue.append(int(str(num)+'0'))
#     queue.append(int(str(num)+'1'))
#     queue.append(int(str(num)+'4'))
#     queue.append(int(str(num)+'9'))
# #     label=1
# #     for i in str(num):
# #         if  int(i) not in visited :
# #             label=-1
# #     if label==1:
#     if is_sqr(num) :
#         visited.add(num)
#         len_+=1
#         print(len(visited))
#         if len(visited)==2020:
#             print(num)
#             break
```