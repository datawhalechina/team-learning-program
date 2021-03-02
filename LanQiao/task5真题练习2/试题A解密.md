## 试题 A: 解密

本题总分：5 分

**【问题描述】**

小明设计了一种文章加密的方法：对于每个字母 c，将它变成某个另外的
字符 Tc。下表给出了字符变换的规则：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201014223039811.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTU2OTc4NQ==,size_16,color_FFFFFF,t_70#pic_center)

例如，将字符串 YeRi 加密可得字符串 EaFn。
小明有一个随机的字符串，加密后为
EaFnjISplhFviDhwFbEjRjfIBBkRyY
（由 30 个大小写英文字母组成，不包含换行符），请问原字符串是多少？
（如果你把以上字符串和表格复制到文本文件中，请务必检查复制的内容
是否与文档中的一致。在试题目录下有一个文件 str.txt，第一行为上面的字符
串，后面 52 行依次为表格中的内容。）

**【答案提交】**

这是一道结果填空题，你只需要算出结果后提交即可。本题的结果为一个
只包含 30 个大小写英文字母的字符串，在提交答案时只填写这个字符串，填写多余的内容将无法得分。



```
f=open('str.txt','r')
strings=f.readlines()
j=0
dic_={}
target=strings[0]
for string in strings[1:]:
    a,b=string.strip().split()
    dic_[b]=a
for i in target[:-2]:
    print(dic_[i],end='')

```

    YeRikGSunlRzgDlvRwYkXkrGWWhXa