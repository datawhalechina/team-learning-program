## 只出现一次的数字||


```
def singleNumber( nums) -> int:
        one=0
        two=0
        three=0
        for num in nums:
            two|=one&num
            one=one^num
            three|=one&two
            print(three)
            print('{:b}'.format(three))
            one=one&~three
            two=two&~three
        return one
singleNumber([2,2,3,2])
```

    0
    0
    0
    0
    2
    10
    2
    10





    1