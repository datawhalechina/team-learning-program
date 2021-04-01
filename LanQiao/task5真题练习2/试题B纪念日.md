## 试题 B: 纪念日

本题总分：5 分

**【问题描述】**

2020 年 7 月 1 日是中国共产党成立 99 周年纪念日。
中国共产党成立于 1921 年 7 月 23 日。
请问从 1921 年 7 月 23 日中午 12 时到 2020 年 7 月 1 日中午 12 时一共包
含多少分钟？

**【答案提交】**

这是一道结果填空题，你只需要算出结果后提交即可。本题的结果为一个
整数，在提交答案时只填写这个整数，填写多余的内容将无法得分。


```
import datetime
end=datetime.datetime(year=2020,month=7,day=1,hour=12)
start=datetime.datetime(year=1921,month=7,day=23,hour=12)
re=end-start
re
```




    datetime.timedelta(36138)




```
36128*24*60
```




    52024320