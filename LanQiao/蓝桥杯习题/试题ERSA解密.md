## 试题 E: RSA 解密
本题总分：15 分

**【问题描述】**
```
RSA 是一种经典的加密算法。它的基本加密过程如下。
首先生成两个质数 p, q，令 n = p · q，设 d 与 (p − 1) · (q − 1) 互质，则可
找到 e 使得 d · e 除 (p − 1) · (q − 1) 的余数为 1。
n, d, e 组成了私钥，n, d 组成了公钥。
当使用公钥加密一个整数 X 时（小于 n），计算 C = X
d mod n，则 C 是加
密后的密文。
当收到密文 C 时，可使用私钥解开，计算公式为 X = C
e mod n。
例如，当 p = 5, q = 11, d = 3 时，n = 55, e = 27。
若加密数字 24，得 243 mod 55 = 19。
解密数字 19，得 1927 mod 55 = 24。
现在你知道公钥中 n = 1001733993063167141, d = 212353，同时你截获了
别人发送的密文 C = 20190324，请问，原文是多少？
```
**【答案提交】**

这是一道结果填空的题，你只需要算出结果后提交即可。本题的结果为一
个整数，在提交答案时只填写这个整数，填写多余的内容将无法得分。



```
import math
def isPrime(n):
    for i in range(2,int(math.sqrt(n))):
        if n%i==0:
            return False
    return True

n=1001733993063167141
p,q=-1,-1
for i in range(2,int(math.sqrt(n))):
    if n%i==0 and isPrime(i) and isPrime(n//i):
        p=i
        q=n//i
print(p,q)
```

    891234941 1123984201



```
d=212353
for e in range(int(((q-1)*(p-1))/d),int((q-1)*(p-1))):
    if (d*e)%((q-1)*(p-1))==1:
        print(e)
        break
    
```


```
p=891234941
q=1123984201
n=1001733993063167141
c=20190324
d=212353
for i in range(1,500000):       #枚举因子   d*e%((p-1)*(q-1))=1    (((q-1)*(p-1))*yz+1) %d =0
    if(((p-1)*(q-1)*i+1)%d==0):
        e=((p-1)*(q-1)*i+1)//212353
        print(((p-1)*(q-1)*i+1)//d) 
        break
    
```

    823816093931522017



```
for i in range(1,200000):
    if ((q-1)*(p-1)*i+1)%d==0:
        e=((q-1)*(p-1)*i+1)//d
        print(e)
        break


```

    823816093931522017



```
c**e%n
```


```
re=c
for i in range(2,e+1):
    re%=n
    re*=c
print(re%n)
```


```
c**e
```


```
def binpow(a,b):
    re=1
    while b>0:
        if b&1:
            re*=a
        a=a**2
        b>>=1
    return re

```


```
p=891234941
q=1123984201
n=1001733993063167141
c=20190324
d=212353
for i in range(1,500000):       #枚举因子   d*e%((p-1)*(q-1))=1    (((q-1)*(p-1))*yz+1) %d =0
    if(((p-1)*(q-1)*i+1)%d==0):
        e=((p-1)*(q-1)*i+1)//212353
        print(((p-1)*(q-1)*i+1)//d) 
        break
def quick_mod(a,b,c):
    a=a%c
    ans=1
    while b!=0:
        if b&1:
            ans=(ans*a)%c
        b>>=1
        a=(a*a)%c
    return ans
x=quick_mod(c,e,n)             #x=c^e%n   579706994112328949
print(x)
print(quick_mod(x,d,n))        #c=x^d%n
```

    823816093931522017
    579706994112328949
    20190324