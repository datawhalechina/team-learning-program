


# 04 C#面向对象设计 II


**知识结构：**

![图1 知识结构](https://img-blog.csdnimg.cn/20200909155219848.png)

---
## 5、属性

例1：属性概念的引入（问题）

![图2 Class Diagram](https://img-blog.csdnimg.cn/20200908121525946.png)


```c
public class Animal
{
    public int Age;
    public double Weight;
    public bool Sex;
    public Animal(int age, double weight, bool sex)
    {
        Age = age;
        Weight = weight;
        Sex = sex;
    }
    public void Eat()
    {
        Console.WriteLine("Animal Eat.");
    }
    public void Sleep()
    {
        Console.WriteLine("Animal Sleep.");
    }
    public override string ToString()
    {
        return string.Format("Animal Age:{0},Weight:{1},Sex:{2}",
            Age, Weight, Sex);
    }
}

class Program
{
    static void Main(string[] args)
    {
        Animal al = new Animal(1, 1.2, false);
        Console.WriteLine("Animal Age:{0},Weight:{1},Sex:{2}",
            al.Age, al.Weight, al.Sex);
        // Animal Age:1,Weight:1.2,Sex:False

        al.Age = -1;
        al.Weight = -0.5;
        Console.WriteLine(al);
        // Animal Age:-1,Weight:-0.5,Sex:False
    }
}
```

例2：问题的解决（Java语言解决该问题的方案）

![图3 Class Diagram](https://img-blog.csdnimg.cn/20200908121838952.png)

```c
public class Animal
{
    private int _age;
    private double _weight;
    private readonly bool _sex;
    public int GetAge()
    {
        return _age;
    }
    public void SetAge(int value)
    {
        _age = (value > 0) ? value : 0;
    }
    public double GetWeight()
    {
        return _weight;
    }
    public void SetWeight(double value)
    {
        _weight = (value > 0) ? value : 0;
    }
    public bool GetSex()
    {
        return _sex;
    }
    public Animal(int age, double weight, bool sex)
    {
        _age = (age > 0) ? age : 0;
        _weight = (weight > 0) ? weight : 0;
        _sex = sex;
    }
    public void Eat()
    {
        Console.WriteLine("Animal Eat.");
    }
    public void Sleep()
    {
        Console.WriteLine("Animal Sleep.");
    }
    public override string ToString()
    {
        return string.Format("Animal Age:{0},Weight:{1},Sex:{2}",
            _age, _weight, _sex);
    }
}

class Program
{
    static void Main(string[] args)
    {
        Animal al = new Animal(1, 1.2, false);
        Console.WriteLine("Animal Age:{0},Weight:{1},Sex:{2}",
            al.GetAge(), al.GetWeight(), al.GetSex());
        // Animal Age:1,Weight:1.2,Sex:False

        al.SetAge(-1);
        al.SetWeight(-0.5);
        Console.WriteLine(al);
        // Animal Age:0,Weight:0,Sex:False
    }
}
```

例3：属性的提出

![图4 Class Diagram](https://img-blog.csdnimg.cn/20200908122140438.png)

```c
public class Animal
{
    private int _age;
    private double _weight;
    private readonly bool _sex;
    public int Age
    {
        get { return _age; }
        set { _age = (value > 0) ? value : 0; }
    }
    public double Weight
    {
        get { return _weight; }
        set { _weight = (value > 0) ? value : 0; }
    }
    public bool Sex
    {
        get { return _sex; }
    }
    public Animal(int age, double weight, bool sex)
    {
        _age = (age > 0) ? age : 0;
        _weight = (weight > 0) ? weight : 0;
        _sex = sex;
    }
    public void Eat()
    {
        Console.WriteLine("Animal Eat.");
    }
    public void Sleep()
    {
        Console.WriteLine("Animal Sleep.");
    }
    public override string ToString()
    {
        return string.Format("Animal Age:{0},Weight:{1},Sex:{2}",
            Age, Weight, Sex);
    }
}

class Program
{
    static void Main(string[] args)
    {
        Animal al = new Animal(1, 1.2, false);
        Console.WriteLine("Animal Age:{0},Weight:{1},Sex:{2}",
            al.Age, al.Weight, al.Sex);
        // Animal Age:1,Weight:1.2,Sex:False

        al.Age = -1;
        al.Weight = -0.5;
        Console.WriteLine(al);
        // Animal Age:0,Weight:0,Sex:False
    }
}
```

说明：C#在声明data时就可以定义`set`、`get`方法。
- `get`：定义读取操作。
- `set`：定义赋值操作，`value`表示传入的参数值。

例4：属性的简化

![图5 Class Diagram](https://img-blog.csdnimg.cn/20200908123716795.png)

```c
public class Animal
{
    private int _age;
    private double _weight;
    public int Age
    {
        get { return _age; }
        set { _age = (value > 0) ? value : 0; }
    }
    public double Weight
    {
        get { return _weight; }
        set { _weight = (value > 0) ? value : 0; }
    }
    public bool Sex { get; private set; }
    public Animal(int age, double weight, bool sex)
    {
        _age = (age > 0) ? age : 0;
        _weight = (weight > 0) ? weight : 0;
        Sex = sex;
    }
    public void Eat()
    {
        Console.WriteLine("Animal Eat.");
    }
    public void Sleep()
    {
        Console.WriteLine("Animal Sleep.");
    }
    public override string ToString()
    {
        return string.Format("Animal Age:{0},Weight:{1},Sex:{2}",
            Age, Weight, Sex);
    }
}
class Program
{
    static void Main(string[] args)
    {
        Animal al = new Animal(1, 1.2, false);
        Console.WriteLine("Animal Age:{0},Weight:{1},Sex:{2}",
            al.Age, al.Weight, al.Sex);
        // Animal Age:1,Weight:1.2,Sex:False

        al.Age = -1;
        al.Weight = -0.5;
        Console.WriteLine(al);
        // Animal Age:0,Weight:0,Sex:False
    }
}
```


---
## 6、索引器

**6.1 定义**

是集合类中的一种特殊属性，可使得集合类中的元素像数组元素一样访问。

**6.2 语法结构**

```c
public 元素类型 this[int index]
{
    get { ... }
    set { ... }
}
public 元素类型 this[string name]
{
    get { ... }
    set { ... }
}
```

例5：利用索引器实现对集合类`StudentSet`中元素`Student`的访问。

![图6 Class Diagram](https://img-blog.csdnimg.cn/20200908125836707.png)

```c
public class Student
{
    private string _name;
    public string Name
    {
        get { return _name; }
        set
        {
            _name = string.IsNullOrEmpty(value)
                ? "NULL"
                : value;
        }
    }
    public long ID { get; set; }
    public Student(long id, string name)
    {
        ID = id;
        _name = name;
    }
    public override string ToString()
    {
        return string.Format("ID:{0},Name:{1}", ID, Name);
    }
}

public class StudentSet
{
    private readonly int _maxCount = 500;
    private readonly Student[] _stus;
    public int Count
    {
        get; private set;
    }
    public StudentSet()
    {
        Count = 0;
        _stus = new Student[_maxCount];
    }
    public void Add(Student stu)
    {
        if (stu == null)
            throw new ArgumentNullException();
        if (Count == _maxCount)
            throw new IndexOutOfRangeException();

        _stus[Count] = stu;
        Count++;
    }
    public Student this[int index]
    {
        get
        {
            if (index < 0 || index > Count - 1)
                throw new IndexOutOfRangeException();
            return _stus[index];
        }
        set
        {
            if (index < 0 || index > Count - 1)
                throw new IndexOutOfRangeException();

            if (value == null)
                throw new ArgumentNullException();
            _stus[index] = value;
        }
    }
}

class Program
{
    static void Main(string[] args)
    {
        StudentSet stuSet = new StudentSet();
        stuSet.Add(new Student(10086, "张三"));
        stuSet.Add(new Student(95988, "李四"));
        stuSet[1].Name = string.Empty;
        Console.WriteLine(stuSet.Count); // 2
        Console.WriteLine(stuSet[0]); // ID:10086,Name:张三
        Console.WriteLine(stuSet[1]); // ID:95988,Name:NULL
        Console.WriteLine(stuSet[2]);
        //未处理的异常:  System.IndexOutOfRangeException: 索引超出了数组界限。
    }
}
```


---
## 7、接口

**7.1 概念**

- 接口是类设计的蓝图，即只提供声明而没有实现。
- 接口不可以直接实例化对象（与抽象类相同）。
- C#允许一个类实现多个接口（注意与继承的区别）。
- 接口就是包含一系列不被实现的方法，而把这些方法的实现交给继承它的类。

**7.2 表示**

![图7 Class Diagram](https://img-blog.csdnimg.cn/20200908130439364.png)

例6：根据类图利用`Dog`实现`IAnimal`接口

![图8 Class Diagram](https://img-blog.csdnimg.cn/20200908130626106.png)

```c
public interface IAnimal
{
    int Age { get; set; }
    double Weight { get; set; }
    void Eat();
    void Sleep();
}

public class Dog : IAnimal
{
    private int _age;
    private double _weight;
    public int Age
    {
        get { return _age; }
        set { _age = (value >= 0) ? value : 0; }
    }
    public double Weight
    {
        get { return _weight; }
        set { _weight = (value >= 0) ? value : 0; }
    }
    public void Eat()
    {
        Console.WriteLine("Dog Eat");
    }
    public void Sleep()
    {
        Console.WriteLine("Dog Sleep");
    }
}

class Program
{
    static void Main(string[] args)
    {
        IAnimal al = new Dog();
        al.Age = 1;
        al.Weight = 2.5;
        Console.WriteLine("Dog:Age={0},Weight={1}",
            al.Age, al.Weight);
        // Dog:Age=1,Weight=2.5
        al.Eat();
        // Dog Eat
        al.Sleep();
        // Dog Sleep
    }
}
```

例7：利用接口实现“饲养系统”

某饲养员（Raiser）在目前状态下需要饲养三种动物：狗（Dog）、鸟（Bird）和鱼（Fish），该三种动物只需要让其睡觉（Sleep）和吃饭（Eat）即可。请设计该饲养系统，要求满足软件设计的“开闭原则”。

方案一：（在抽象类部分已经给出）

![图9 利用抽象类实现饲养系统](https://img-blog.csdnimg.cn/2020091218043517.png)


方案二：

![图10 利用接口实现饲养系统](https://img-blog.csdnimg.cn/20200908135547691.png)

```c
public interface IAnimal
{
    int Age { get; set; }
    double Weight { get; set; }
    void Eat();
    void Sleep();
}

public class Bird : IAnimal
{
    private int _age;
    private double _weight;
    public int Age
    {
        get { return _age; }
        set { _age = (value >= 0) ? value : 0; }
    }
    public double Weight
    {
        get { return _weight; }
        set { _weight = (value >= 0) ? value : 0; }
    }
    public void Eat()
    {
        Console.WriteLine("Bird Eat.");
    }
    public void Sleep()
    {
        Console.WriteLine("Bird Sleep.");
    }
    public void Fly()
    {
        Console.WriteLine("Bird Fly.");
    }
}

public class Dog : IAnimal
{
    private int _age;
    private double _weight;
    public int Age
    {
        get { return _age; }
        set { _age = (value >= 0) ? value : 0; }
    }
    public double Weight
    {
        get { return _weight; }
        set { _weight = (value >= 0) ? value : 0; }
    }
    public void Eat()
    {
        Console.WriteLine("Dog Eat.");
    }
    public void Sleep()
    {
        Console.WriteLine("Dog Sleep.");
    }
    public void Run()
    {
        Console.WriteLine("Dog Run.");
    }
}

public class Fish : IAnimal
{
    private int _age;
    private double _weight;
    public int Age
    {
        get { return _age; }
        set { _age = (value >= 0) ? value : 0; }
    }
    public double Weight
    {
        get { return _weight; }
        set { _weight = (value >= 0) ? value : 0; }
    }
    public void Eat()
    {
        Console.WriteLine("Fish Eat.");
    }
    public void Sleep()
    {
        Console.WriteLine("Fish Sleep.");
    }
    public void Swim()
    {
        Console.WriteLine("Fish Swim.");
    }
}

public class Raiser
{
    public void Raise(IAnimal al)
    {
        al.Eat();
        al.Sleep();
    }
}

class Program
{
    static void Main(string[] args)
    {
        Raiser rsr = new Raiser();
        rsr.Raise(new Dog());
        // Dog Eat.
        // Dog Sleep.

        rsr.Raise(new Bird());
        // Bird Eat.
        // Bird Sleep.

        rsr.Raise(new Fish());
        //Fish Eat.
        //Fish Sleep.
    }
}
```

**7.3 接口（interface）与抽象类（abstract class）**

（1）相同点：
- 接口与抽象类都不可以直接实例化对象。

（2）不同点：
- 抽象类中的数据和操作必须有限制修饰符，而接口中的数据和操作不可以有限制修饰符。
- 抽象类中可以有带实现体的方法（非abstract方法），而接口只能有方法的声明。
- 抽象类在子类中通过override关键字覆写抽象方法，而接口被子类直接实现。

例8：一个类可以实现多个接口，但要注意多个接口中有重名方法的处理。

方式一：

![图11 Class Diagram](https://img-blog.csdnimg.cn/20200908140142289.png)

```c
public interface IHighWayWorker
{
    void HighWayOperation();
    void Build();
}

public interface IRailWayWorker
{
    void RailWayOperation();
    void Build();
}

public class Worker : IRailWayWorker, IHighWayWorker
{
    public void HighWayOperation()
    {
        Console.WriteLine("HighWayOperation.");
    }
    public void RailWayOperation()
    {
        Console.WriteLine("RailWayOperation");
    }
    public void Build()
    {
        Console.WriteLine("HighWay,RailWay,Build.");
    }
}

class Program
{
    static void Main(string[] args)
    {
        Worker wr = new Worker();
        wr.Build(); // HighWay,RailWay,Build.
        IHighWayWorker hwr = new Worker();
        hwr.Build(); // HighWay,RailWay,Build.
        hwr.HighWayOperation(); // HighWayOperation.
        IRailWayWorker rwr = new Worker();
        rwr.Build(); // HighWay,RailWay,Build.
        rwr.RailWayOperation();// RailWayOperation
    }
}
```

方式二：

![图12 Class Diagram](https://img-blog.csdnimg.cn/20200908140557927.png)

```c
public interface IHighWayWorker
{
    void HighWayOperation();
    void Build();
}

public interface IRailWayWorker
{
    void RailWayOperation();
    void Build();
}

public class Worker : IHighWayWorker, IRailWayWorker
{
    public void HighWayOperation()
    {
        Console.WriteLine("HighWayOperation.");
    }
    public void RailWayOperation()
    {
        Console.WriteLine("RailWayOperation");
    }
    void IHighWayWorker.Build()
    {
        Console.WriteLine("HighWay Build.");
    }
    void IRailWayWorker.Build()
    {
        Console.WriteLine("RailWay Build.");
    }
    // 注意：void IHighWayWorker.Build()和void IRailWayWorker.Build()
    // 前面不能够加限制修饰符。
}

class Program
{
    static void Main(string[] args)
    {
        Worker wr = new Worker();
        //wr.Build(); 该语句错误
        IHighWayWorker hwr = new Worker();
        hwr.Build();//HighWay Build.
        hwr.HighWayOperation();//HighWayOperation.
        IRailWayWorker rwr = new Worker();
        rwr.Build();//RailWay Build.
        rwr.RailWayOperation();//RailWayOperation
    }
}
```

---
## 8、泛型

例9：存储`int`数据类型的集合及操作。

```c
public class IntSet
{
    private readonly int _maxSize;
    private readonly int[] _set;
    public IntSet()
    {
        _maxSize = 100;
        _set = new int[_maxSize];
        //...
    }
    public void Insert(int k, int x)
    {
        //....
        _set[k] = x;
    }
    public int Locate(int k)
    {
        //...
        return _set[k];
    }
}

class Program
{
    static void Main(string[] args)
    {
        IntSet iSet = new IntSet();
        iSet.Insert(0, 123);
        int i = iSet.Locate(0);
        Console.WriteLine(i); // 123
    }
}
```

例10：存储`string`数据类型的集合及操作。

```c
public class StringSet
{
    private readonly int _maxSize;
    private readonly string[] _set;
    public StringSet()
    {
        _maxSize = 100;
        _set = new string[_maxSize];
        //...
    }
    public void Insert(int k, string x)
    {
        //....
        _set[k] = x;
    }
    public string Locate(int k)
    {
        //...
        return _set[k];
    }
}

class Program
{
    static void Main(string[] args)
    {
        StringSet strSet = new StringSet();
        strSet.Insert(0, "abc");
        string j = strSet.Locate(0);
        Console.WriteLine(j); // abc
    }
}
```

例11：利用`object`类存储通用数据类型的集合及操作。


```c
public class GSet
{
    private readonly int maxSize;
    private readonly object[] _set;
    public GSet()
    {
        maxSize = 100;
        _set = new object[maxSize];
        //...
    }
    public void Insert(int k, object x)
    {
        //....
        _set[k] = x;
    }
    public object Locate(int k)
    {
        //...
        return _set[k];
    }
}

class Program
{
    static void Main(string[] args)
    {
        GSet gSet1 = new GSet();
        gSet1.Insert(0, 123);
        int k1 = (int)gSet1.Locate(0);
        Console.WriteLine(k1); // 123

        GSet gSet2 = new GSet();
        gSet2.Insert(0, "abc");
        string k2 = (string)gSet2.Locate(0);
        Console.WriteLine(k2); // abc

        GSet gSet3 = new GSet();
        gSet3.Insert(0, 123);
        gSet3.Insert(1, "abc");//编译时可以通过，运行时出现异常。
        int k3 = (int)gSet3.Locate(1); //这样使用存在类型安全问题。
        Console.WriteLine(k3);
        // 未处理的异常:  System.InvalidCastException: 指定的转换无效。
    }
}
```

泛型定义：即参数化类型。

在编译时用一个具体类型代替该参数类型，可定义类型安全的类而不影响工作效率。

例12：利用泛型`T`存储通用数据类型的集合及操作。

```c
public class GSet<T>
{
    private readonly int _maxSize;
    private readonly T[] _set;
    public GSet()
    {
        _maxSize = 100;
        _set = new T[_maxSize];
        //...
    }
    public void Insert(int k, T x)
    {
        //....
        _set[k] = x;
    }
    public T Locate(int k)
    {
        //...
        return _set[k];
    }
}

class Program
{
    static void Main(string[] args)
    {
        GSet<int> gSet1 = new GSet<int>();
        gSet1.Insert(0, 123);
        int k1 = gSet1.Locate(0);
        Console.WriteLine(k1); // 123

        GSet<string> gSet2 = new GSet<string>();
        gSet2.Insert(0, "abc");
        string k2 = gSet2.Locate(0);
        Console.WriteLine(k2); // abc
    }
}
```

我们把`T`称为类型参数，当然我们也可以对`T`进行约束。


例13：为类型参数`T`增加约束，比如`T`只能是值类型。

```c
public class GSet<T> where T : struct
{
    private readonly int _maxSize;
    private readonly T[] _set;
    public GSet()
    {
        _maxSize = 100;
        _set = new T[_maxSize];
        //...
    }
    public void Insert(int k, T x)
    {
        //....
        _set[k] = x;
    }
    public T Locate(int k)
    {
        //...
        return _set[k];
    }
}

class Program
{
    static void Main(string[] args)
    {
        GSet<int> gSet1 = new GSet<int>();
        gSet1.Insert(0, 123);
        int k1 = gSet1.Locate(0);
        Console.WriteLine(k1); // 123

        // GSet<string> gSet2 = new GSet<string>(); // 编译错误
        // 错误 CS0453 类型“string”必须是不可以为 null 值的类型，
        // 才能用作泛型类型或方法“GSet < T >”中的参数“T”	
    }
}
```

有关泛型约束可以查看以下图文：
- [技术图文：C#语言中的泛型 I](https://mp.weixin.qq.com/s?__biz=MzIyNDA1NjA1NQ==&mid=2651013150&idx=1&sn=f91d81a00062d81d10e22a39c3dfb799&chksm=f3e32786c494ae908df44b253a45693ebbaff4f4e447712e9a037ae423276f5269b24675c625&token=1451124869&lang=zh_CN#rd)
- [技术图文：C#语言中的泛型 II](https://mp.weixin.qq.com/s/Zy09wOrB-M3U_jGt0MSbLA)


---
## 9、类与类之间的关系

![图13 依赖关系](https://img-blog.csdnimg.cn/20200909151212562.png)

```c
public class Oxygen
{
    //...
}

public class Water
{
    //...
}

public abstract class Animal
{
    public int Age;
    public double Weight;
    public abstract void Eat();
    public abstract void Sleep();
    public abstract void Breed();
    public abstract void Metabolism(Oxygen o2, Water water);
}
```

![图14 继承关系](https://img-blog.csdnimg.cn/20200909151400979.png)

```c
public class Bird : Animal
{
    public string Feather;
    public void Fly()
    {
        //...
    }
    public void Egg()
    {
        //...
    }
    public override void Eat()
    {
        //...
    }
    public override void Sleep()
    {
        //...
    }
    public override void Breed()
    {
        //...
    }
    public override void Metabolism(Oxygen o2, Water water)
    {
        //...
    }
}

public class Penguin : Bird
{
    //...
}

public class Goose : Bird
{
    //...
}

public class Duck : Bird
{
    //...
}
```


![图15 关联关系](https://img-blog.csdnimg.cn/20200909151625397.png)

```c
public class Climate
{
    //...
}

public class Penguin : Bird
{
    private Climate _climate;
    //...
}
```

![图16 实现关系](https://img-blog.csdnimg.cn/20200909151759894.png)

```c
public interface ILanguage
{
    void Speak();
}
public class DonaldDuck : Duck, ILanguage
{
    public void Speak()
    {
        //...
    }
    //...
}
```

