# ARKit+Swift 版本的机器学习算法 k-NN

> 本文原链接：[https://www.iosdevlog.com](https://www.iosdevlog.com/)，特别鸣谢：贾献华 的开源贡献

## 维基介绍

在[模式识别](https://zh.wikipedia.org/wiki/模式识别)领域中，**最近邻居法**（**KNN**算法，又译**K-近邻算法**）是一种用于[分类](https://zh.wikipedia.org/wiki/分类问题)和[回归](https://zh.wikipedia.org/wiki/迴歸分析)的[非参数统计](https://zh.wikipedia.org/wiki/無母數統計)方法[[1\]](https://zh.wikipedia.org/wiki/最近鄰居法#cite_note-1)。在这两种情况下，输入包含[特征空间（Feature Space）](https://zh.wikipedia.org/w/index.php?title=特徵空間(機器學習)&action=edit&redlink=1)中的***k\***个最接近的训练样本。

- 在*k-NN分类*中，输出是一个分类族群。一个对象的分类是由其邻居的“多数表决”确定的，*k*个最近邻居（*k*为正[整数](https://zh.wikipedia.org/wiki/整数)，通常较小）中最常见的分类决定了赋予该对象的类别。若*k* = 1，则该对象的类别直接由最近的一个节点赋予。
- 在*k-NN回归*中，输出是该对象的属性值。该值是其*k*个最近邻居的值的平均值。

最近邻居法采用向量空间模型来分类，概念为相同类别的案例，彼此的相似度高，而可以借由计算与已知类别案例之相似度，来评估未知类别案例可能的分类。

K-NN是一种[基于实例的学习](https://zh.wikipedia.org/w/index.php?title=基于实例的学习&action=edit&redlink=1)，或者是局部近似和将所有计算推迟到分类之后的[惰性学习](https://zh.wikipedia.org/w/index.php?title=惰性学习&action=edit&redlink=1)。k-近邻算法是所有的[机器学习](https://zh.wikipedia.org/wiki/机器学习)算法中最简单的之一。

无论是分类还是回归，衡量邻居的权重都非常有用，使较近邻居的权重比较远邻居的权重大。例如，一种常见的加权方案是给每个邻居权重赋值为1/ d，其中d是到邻居的距离。[[注 1\]](https://zh.wikipedia.org/wiki/最近鄰居法#cite_note-2)

邻居都取自一组已经正确分类（在回归的情况下，指属性值正确）的对象。虽然没要求明确的训练步骤，但这也可以当作是此算法的一个训练样本集。

k-近邻算法的缺点是对数据的局部结构非常敏感。本算法与[K-平均算法](https://zh.wikipedia.org/wiki/K-平均算法)（另一流行的机器学习技术）没有任何关系，请勿与之混淆。

## ARKit + Swift + k-NN 实现

创建 KNN 类（结构体 `struct` 也行，我是为了 与 `sklearn` 尽量一致）。

```
/// KNN
public struct KNN<Feature, Label: Hashable> {
}
```

属性

```
    /// Number of neighbors to use by default for :meth:`kneighbors` queries
    private var k: Int
    /// Data set
    private var X = [Feature]()
    /// Target values
    private var y = [Label]()


    /// distance
    private let distanceMetric: (_ x1: Feature, _ x2: Feature) -> Double
    /// draw radius for debug
    public var debugRadiusCallback: (([Double]) -> ())? = nil
```

数据：

- `k`: 指定取 k 个最接近的训练样本
- `X`: 样本特征 （数组）一般要传数组的数组
- `y`: 样本标签 （数组）

辅助：

- `distanceMetric`: 用来计算距离的函数
- `debugRadiusCallback`: 调度时候用，主要是画出最近的 k 个样本范围

## 方法

```
    /// constructorLabels for xTest
    ///
    /// - Parameters:
    ///   - k: k
    ///   - distanceMetric: distance
    public init (k: Int, distanceMetric: @escaping (_ x1: Feature, _ x2: Feature) -> Double)
    
    /// train
    ///
    /// - Parameters:
    ///   - X: Training set
    ///   - y: Target values
    public mutating func fit(X: [Feature], y: [Label]) 
    
    
    /// Labels for xTest
    ///
    /// - Parameter XTest: Test set
    /// - Returns: Target values
    public func predict(XTest: [Feature]) -> [Label] 
```

- `init()`: 构造函数 需要预先决定 `k` 和距离计算方法
- `fit()`: 拟合目标函数，kNN 不需要拟合，只要记下数据即可
- `predict()`: 预测给定的特征，返回对应的标签

## 计算距离

```
public struct Distance {
    
    /// Helper function to calculate euclidian distance
    ///
    /// - Parameters:
    ///   - x0: source coordinate
    ///   - x1: target coordinate
    /// - Returns: euclidian distance
    public static func euclideanDistance(_ x0: [Double], _ x1: [Double]) -> Double 

    // Convenience
    public static func euclideanDistance() -> (([Double], [Double]) -> Double) {
        return { self.euclideanDistance($0, $1) }
    }
```

这里使用 欧式距离（Euclidean Distance）

KNN 使用：

```
    func testKNN() {
        let kNN = KNN<Double, Int>(k: 3)
        let X = [[1.0], [2], [3], [4]]
        let y = [0, 0, 1, 1]
        kNN.fit(X, y)
        
        let label = kNN.predict([[1.2], [3]])
        
        XCTAssertEqual([0, 1], label)
    }
```

## 显示2维

```
enum MLStep: Int {
    case train
    case predict
}

enum GeometryType: Int  {
    case box
    case pyramid
    case sphere
    case cylinder
    case cone
    case tube
    case torus
...
}
```

- `MLStep`： 分成 训练 和 预测 ，训练一次，可以一直预测。
- `GeometryType`： 定义几种形状，这里定义给 `ARKIT` 使用的

## KNNViewController

```
class KNNViewController: UIViewController {

    let radius: CGFloat = 5

    public var X: [[Double]] = []
    public var y: [GeometryType] = []
    public var XTest: [[Double]] = []
    public var yTest: [GeometryType] = []
    public var radiuses: [Double] = [] {
        didSet {
            for (center, r) in zip(XTest, radiuses) {
                drawCircle(center: CGPoint(x: center[0], y: center[1]), radius: CGFloat(r))
            }
        }
    }
    public var predictLayers: [CALayer] = []

    var model = KNN<[Double], GeometryType>(k: 1, distanceMetric: Distance.euclideanDistance())

    @IBOutlet weak var kNNPickerView: UIPickerView!
    @IBOutlet weak var panelView: UIView!
    @IBOutlet weak var trainBarButtonItem: UIBarButtonItem!

    var mlStep = MLStep.train {
        didSet {
            switch mlStep {
            case .train:
                trainBarButtonItem.title = "train"
            default:
                trainBarButtonItem.title = "predict"
            }
        }
    }
...
}
```

添加 `Layer` 到 `panelView` 上实现类别，2D 只分了三个类别，分别用 方形（红），三角形（蓝），花形（绿）表示。

使用 `alpha` 表示预测类别，以预测样本为中心画一个圈，圈内为最近的 `k` 个样本。

```
    func drawCircle(center: CGPoint, radius: CGFloat, alpha: CGFloat = 0.1) {
        let r = self.radius + radius
        let kNNCircleLayer = CAShapeLayer()
        kNNCircleLayer.path = UIBezierPath(roundedRect: CGRect(x: center.x - r, y: center.y - r, width: r * 2, height: r * 2), cornerRadius: r).cgPath
        kNNCircleLayer.fillColor = UIColor(red: 0.1, green: 0.1, blue: 0.1, alpha: alpha).cgColor
        kNNCircleLayer.borderColor = UIColor(red: 0.8, green: 0.8, blue: 0.8, alpha: 1).cgColor
        kNNCircleLayer.borderWidth = 1
        panelView.layer.addSublayer(kNNCircleLayer)
    }
```

圆内为 `k` 个样本。

## ARKit 实现

能 3D 展示多好，别急，下面就是用 `ARKit` 实现的 3D 版本。

```
class ARKNNViewController: UIViewController 
```

基本实现和 `ARKNNViewController` 类似。

```
    func drawSphere(center: SCNVector3, radius: Float) {
        let geometry = SCNSphere(radius: CGFloat(radius) + self.radius)
  
        geometry.firstMaterial?.diffuse.contents = UIColor(red: 0.1, green: 0.1, blue: 0.8, alpha: 0.7)
        
        let node = SCNNode()
        node.geometry = geometry
        node.position = center
        sceneView.scene.rootNode.addChildNode(node)
    }
```

这是画最外面的范围球体，球体内为 `k` 个样本。

## 视频

b站视频：https://www.bilibili.com/video/av48647681/