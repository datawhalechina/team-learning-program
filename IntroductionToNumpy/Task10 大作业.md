# 大作业

本次练习使用 鸢尾属植物数据集`.\iris.data`，在这个数据集中，包括了三类不同的鸢尾属植物：Iris Setosa，Iris Versicolour，Iris Virginica。每类收集了50个样本，因此这个数据集一共包含了150个样本。

- sepallength：萼片长度
- sepalwidth：萼片宽度
- petallength：花瓣长度
- petalwidth：花瓣宽度

以上四个特征的单位都是厘米（cm）。

```python
     sepallength  sepalwidth  petallength  petalwidth         species
0            5.1         3.5          1.4         0.2     Iris-setosa
1            4.9         3.0          1.4         0.2     Iris-setosa
2            4.7         3.2          1.3         0.2     Iris-setosa
3            4.6         3.1          1.5         0.2     Iris-setosa
4            5.0         3.6          1.4         0.2     Iris-setosa
..           ...         ...          ...         ...             ...
145          6.7         3.0          5.2         2.3  Iris-virginica
146          6.3         2.5          5.0         1.9  Iris-virginica
147          6.5         3.0          5.2         2.0  Iris-virginica
148          6.2         3.4          5.4         2.3  Iris-virginica
149          5.9         3.0          5.1         1.8  Iris-virginica

[150 rows x 5 columns]
```



**1. 导入鸢尾属植物数据集，保持文本不变。**

【知识点：输入和输出】
- 如何导入存在数字和文本的数据集？





**2. 求出鸢尾属植物萼片长度的平均值、中位数和标准差（第1列，sepallength）**

【知识点：统计相关】
- 如何计算numpy数组的均值，中位数，标准差？



**3. 创建一种标准化形式的鸢尾属植物萼片长度，其值正好介于0和1之间，这样最小值为0，最大值为1（第1列，sepallength）。**

【知识点：统计相关】
- 如何标准化数组？


**4. 找到鸢尾属植物萼片长度的第5和第95百分位数（第1列，sepallength）。**

【知识点：统计相关】
- 如何找到numpy数组的百分位数？




**5. 把iris_data数据集中的20个随机位置修改为np.nan值。**

【知识点：随机抽样】
- 如何在数组中的随机位置修改值？



**6. 在iris_data的sepallength中查找缺失值的个数和位置（第1列）。**

【知识点：逻辑函数、搜索】
- 如何在numpy数组中找到缺失值的位置？


**7. 筛选具有 sepallength（第1列）< 5.0 并且 petallength（第3列）> 1.5 的 iris_data行。**

【知识点：搜索】
- 如何根据两个或多个条件筛选numpy数组？



**8. 选择没有任何 nan 值的 iris_data行。**

【知识点：逻辑函数、搜索】
- 如何从numpy数组中删除包含缺失值的行？




**9. 计算 iris_data 中sepalLength（第1列）和petalLength（第3列）之间的相关系数。**

【知识点：统计相关】
- 如何计算numpy数组两列之间的相关系数？





**10. 找出iris_data是否有任何缺失值。**

【知识点：逻辑函数】
- 如何查找给定数组是否具有空值？




**11. 在numpy数组中将所有出现的nan替换为0。**

【知识点：逻辑函数】
- 如何在numpy数组中用0替换所有缺失值？



**12. 找出鸢尾属植物物种中的唯一值和唯一值出现的数量。**

【知识点：数组操作】
- 如何在numpy数组中查找唯一值的计数？




**13. 将 iris_data 的花瓣长度（第3列）以形成分类变量的形式显示。定义：Less than 3 --> 'small'；3-5 --> 'medium'；'>=5 --> 'large'。**

【知识点：统计相关】
- 如何将数字转换为分类（文本）数组？





**14. 在 iris_data 中创建一个新列，其中 volume 是 `(pi x petallength x sepallength ^ 2）/ 3`。**

【知识点：数组操作】
- 如何从numpy数组的现有列创建新列？





**15. 随机抽鸢尾属植物的种类，使得Iris-setosa的数量是Iris-versicolor和Iris-virginica数量的两倍。**

【知识点：随机抽样】
- 如何在numpy中进行概率抽样？




**16. 根据 sepallength 列对数据集进行排序。**

【知识点：排序】
- 如何按列对2D数组进行排序？


**17. 在鸢尾属植物数据集中找到最常见的花瓣长度值（第3列）。**

【知识点：数组操作】
- 如何在numpy数组中找出出现次数最多的值？




**18. 在鸢尾花数据集的 petalwidth（第4列）中查找第一次出现的值大于1.0的位置。**

【知识点：搜索】
- 如何找到第一次出现大于给定值的位置？