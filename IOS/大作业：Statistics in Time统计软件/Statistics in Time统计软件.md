# Statistics in Time统计软件

学习了这么多的基础知识，我们还没有完整的开发一款app！本部分我们将开始Statistics in Time软件的开发，这是一款可以在手机上进行简易数据分析的demo。值得注意的是，这一个Task是开放内容，并没有标准的答案。我们仅将演示完成基本功能的代码，至于更对的样式与搭配则需要你自己来完成。欢迎大家将新奇的想法与精美的UI进行搭配，组合出专属于自己的掌上数据分析app！期待着大家更好的作品！



## 功能分析

日常生活中，我们可能常常需要简易观察一下一些数据之间的统计信息，例如：近几周的菜价是否有不寻常的波动；考试成绩是否符合正态分布；近几天的气温数据是否具有相关性等等等等.....

不管你是数据分析师，是学生/老师，还是财务工作人员，统计都无处不在！由于每个人对统计的需求不尽相同，我们难以做出一款适用于每个人的统计软件，但是我们可以只根据自己的日常需求自己写一个简易的Statistics in Time在手。以我自己为例，我希望能够设计一款随手就能查看一组数据之间的各种信息，其中可以包括这组数据的均值、方差、峰度、偏度等等（如果能可视化分析当然就更赞了！）此外，我还希望能够设计出一款自带日历功能的app，这样就能随时提醒我需要做的事情。因此需求如下：

- 统计功能
- 日历功能

下面，我们将围绕这两个功能进行实现。



## 准备工作

**Step 1：**创建新工程文件Statistics in Time

<img src="/Users/mac/Desktop/iOSdev/大作业：Statistics in Time统计软件/img/1.png" alt="1" style="zoom: 33%;" />

**Step 2：**删除storyboard，并设置代码启动。

**Step3：**`菜单栏`----`file`----`new`----`file`（快捷键command+N）

<img src="/Users/mac/Desktop/iOSdev/大作业：Statistics in Time统计软件/img/2.png" alt="2" style="zoom: 33%;" />

<img src="/Users/mac/Desktop/iOSdev/大作业：Statistics in Time统计软件/img/3.png" alt="3" style="zoom:33%;" />

<img src="/Users/mac/Desktop/iOSdev/大作业：Statistics in Time统计软件/img/4.png" alt="4" style="zoom:33%;" />

**Step4：**同理创建StatisticsViewController.swift与CalendarViewController，继承自UIViewController。

<img src="/Users/mac/Desktop/iOSdev/大作业：Statistics in Time统计软件/img/5.png" alt="5" style="zoom: 33%;" />

**Step 5：**创建navigationViewController，继承自UINavigationViewController。

**Step 6：**删除`ViewController.swift`，并在`SceneDelegate.swfit`中更换如下代码：

```swift
    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        guard let _ = (scene as? UIWindowScene) else { return }
        guard let windowScene = (scene as? UIWindowScene) else {return}
        window = UIWindow(frame:windowScene.coordinateSpace.bounds)
        window?.windowScene = windowScene
        window?.backgroundColor = UIColor.white
        window?.rootViewController = MainViewController()
        window?.makeKeyAndVisible()
    }
```

**Step 7：**将本课件Icon中四个文件夹拖拽至Assets.xcassets中，并在MainViewController内添加如下代码：

```swift
import UIKit
class MainViewController: UITabBarController {

    override func viewDidLoad() {
        super.viewDidLoad()
        self.tabBar.tintColor = UIColor(red: 0/255, green:169/255, blue:169/255, alpha:1)
        addNormalTabbar()
    }
    
    func addNormalTabbar() {
        //初始化
        let updateVc = StatisticsViewController.init()
        updateVc.view.backgroundColor = UIColor.red
        setupOneChildViewController(title: "统计", image: "Trends", seletedImage: "TrendsFilled", controller: StatisticsViewController.init())
        setupOneChildViewController(title: "概要", image: "Settings", seletedImage: "SettingsFilled", controller: ProfileViewController.init())
    }
    fileprivate func  setupOneChildViewController(title: String,image: String,seletedImage: String,controller: UIViewController){
        controller.tabBarItem.title = title
        controller.title = title
        //这里设置背景色 是每一个vc设置的都一样
        controller.view.backgroundColor = UIColor.white
        controller.tabBarItem.image = UIImage.init(named: image)
        controller.tabBarItem.selectedImage = UIImage.init(named: seletedImage)
        let naviController = navigationViewController.init(rootViewController: controller)
        addChild(naviController)
    }
}
```

**Step 8：**运行代码，即可见下图

<img src="/Users/mac/Desktop/iOSdev/大作业：Statistics in Time统计软件/img/6.png" alt="6" style="zoom:50%;" />





现在的页面仍然是一片空白，我们接下来的任务便是在`统计`页面填充入统计功能，在`概要`界面加入日历功能。



## Cocopods与第三方库

你可以在 [Awesome-swift](https://github.com/matteocrippa/awesome-swift) 查看Swift的一些优秀的第三方库，这些第三方库不仅可以帮我们简化功能实现（加入引导页，实现跑马灯功能等），也可以美化UI（更改Button外观、加入动画过渡），甚至帮助我们高效利用内存/缓存等。

本部分我们需要添加两个第三方库：

- **[ SigmaSwiftStatistics](https://github.com/evgenyneu/SigmaSwiftStatistics)**：数学计算第三方库
- [Charts](https://github.com/danielgindi/Charts)：数据可视化
- **[ CVCalendar](https://github.com/CVCalendar/CVCalendar)**：实现日历功能

添加过程如下：

1. 关闭Xcode，打开终端（Terminal），切换到Statistics in Time工程文件目录下

   ```bash
   cd Downloads/code/Statistics\ in\ Time/
   ```

2. Cocopods初始化：

   ```bash
   pod init
   ```

3. 打开Podfile文件

   ```swift
   open Podfile
   ```

4. 添加如下命令：

   ```swift
   pod 'CVCalendar', '~> 1.7.0'
   pod 'Charts'
   pod 'TinyConstraints'
   pod 'SigmaSwiftStatistics', '~> 9.0'
   ```

5. 关闭文本编辑器，回到终端，输入

   ```swift
   pod update
   ```

6. 等待片刻即可完成。

## 统计功能实现

在Statistics in Time目录下，打开`Statistics in Time.xcworkspace`，并添加如下代码

```swift
import SigmaSwiftStatistics
```

该计算库提供了一种科学计算的方法，然而这仅仅是通过数组进行计算。常规情况下我们一般用Textfiled来读取用户输入的内容，这是一种String格式。如何将String转化为Array？除了传统的算法外，我们能否想出一种办法利用Swift的特性进行转化？这里给出一种参考方式：

```swift
    @objc func btnClick(_:  Any){
        let inputNumber:String = String(textField.text!)
        let fullNumberArr = inputNumber.split{$0 == " "}.map(String.init)
        let arr = changeArray(textArray: fullNumberArr)
        resultLabel.text = String(Sigma.average(arr) ?? 0)
    }
    
    func changeArray(textArray: [String]) -> [Double] {
        textArray.compactMap { Double($0) }
    }
```

这一段代码的含义是什么？



## 统计功能参考代码

```swift
import UIKit
import SigmaSwiftStatistics
import TinyConstraints

class StatisticsViewController: UIViewController,UITextFieldDelegate {
    let textField = UITextField(frame: CGRect(x: 120, y: 140, width: 180, height: 40))
    let alertLabel = UILabel(frame: CGRect(x: 20, y: 140, width: 90, height: 40))
    var resultLabel = UILabel(frame: CGRect(x: 175, y: 200, width: 200, height: 40))
    var averLabel = UILabel(frame: CGRect(x: 105, y: 200, width: 90, height: 40))
    var resultLabel2 = UILabel(frame: CGRect(x: 175, y: 240, width: 200, height: 40))
    var centralMomentLabel = UILabel(frame:CGRect(x: 105, y: 240, width: 90, height: 40))
    var resultLabel3 = UILabel(frame: CGRect(x: 175, y: 280, width: 200, height: 40))
    var variancePopulationLabel = UILabel(frame: CGRect(x: 105, y: 280, width: 90, height: 40))
    var resultLabel4 = UILabel(frame:CGRect(x: 175, y: 320, width: 200, height: 40))
    var coefficientOfVariationLabel = UILabel(frame: CGRect(x: 95, y: 320, width: 90, height: 40))
    var resultLabel5 = UILabel(frame:CGRect(x: 175, y: 360, width: 200, height: 40))
    var kurtosisA = UILabel(frame: CGRect(x: 105, y: 360, width: 90, height: 40))
    var resultLabel6 = UILabel(frame:CGRect(x: 175, y: 400, width: 200, height: 40))
    var kurtosisB = UILabel(frame: CGRect(x: 105, y: 400, width: 90, height: 40))
    var resultLabel7 = UILabel(frame:CGRect(x: 175, y: 440, width: 200, height: 40))
    var skewnessLabel = UILabel(frame: CGRect(x: 105, y: 440, width: 90, height: 40))
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?) {
        textField.resignFirstResponder()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        textField.borderStyle = .roundedRect
        textField.textColor = UIColor.black
        alertLabel.text = "数据输入："
        alertLabel.textColor = UIColor.black
        resultLabel.text = " "
        resultLabel2.text = " "
        resultLabel3.text = " "
        resultLabel4.text = " "
        resultLabel5.text = " "
        resultLabel6.text = " "
        resultLabel7.text = " "
        averLabel.text = "平均值："
        centralMomentLabel.text = "标准误："
        variancePopulationLabel.text = "Var值："
        coefficientOfVariationLabel.text = "变异系数："
        kurtosisA.text = "峰度A："
        kurtosisB.text = "峰度B："
        skewnessLabel.text = "偏度："
        
        
        
        let btn:UIButton=UIButton.init(frame: CGRect(x:310,y:140, width:100,height:40))//创建按钮，并设置位置，宽度、高度
        btn.setTitle("开始统计", for: UIControl.State.normal)//设置按钮上的文字
        btn.backgroundColor = UIColor.systemBlue
        btn.addTarget(self, action:#selector(btnClick(_:)), for: UIControl.Event.touchDown)//为按钮添加touchDown事件(按下)
        self.view.addSubview(btn)//将标签添加到View中
        self.view.addSubview(textField)
        self.view.addSubview(alertLabel)
        self.view.addSubview(resultLabel)
        self.view.addSubview(averLabel)
        self.view.addSubview(centralMomentLabel)
        self.view.addSubview(resultLabel2)
        self.view.addSubview(variancePopulationLabel)
        self.view.addSubview(resultLabel3)
        self.view.addSubview(coefficientOfVariationLabel)
        self.view.addSubview(resultLabel4)
        self.view.addSubview(resultLabel5)
        self.view.addSubview(resultLabel6)
        self.view.addSubview(kurtosisA)
        self.view.addSubview(kurtosisB)
        self.view.addSubview(resultLabel7)
        self.view.addSubview(skewnessLabel)
        //textField.delegate = self
    }
    
    
    @objc func btnClick(_:  Any){
        let inputNumber:String = String(textField.text!)
        let fullNumberArr = inputNumber.split{$0 == " "}.map(String.init)
        let arr = changeArray(textArray: fullNumberArr)
        print(Sigma.average(arr) ?? 0)
        resultLabel.text = String(Sigma.average(arr) ?? 0)
        resultLabel2.text = String(Sigma.standardErrorOfTheMean(arr) ?? 0)
        resultLabel3.text = String(Sigma.varianceSample(arr) ?? 0)
        resultLabel4.text = String(Sigma.coefficientOfVariationSample(arr) ?? 0)
        resultLabel5.text = String(Sigma.kurtosisA(arr) ?? 0)
        resultLabel6.text = String(Sigma.kurtosisB(arr) ?? 0)
        resultLabel7.text = String(Sigma.skewnessA(arr) ?? 0)
    }
    
    func changeArray(textArray: [String]) -> [Double] {
        textArray.compactMap { Double($0) }
    }
}
```

## 日历功能的实现

在Statistics in Time目录下，打开`Statistics in Time.xcworkspace`，并添加如下代码

```swift
import CVCalendar
```

这是Github上一个优秀的第三方库，可以实现日历相关功能。不过，在实现功能之前，我们还需要手动添加一个文件：ColorsConfig，后面我们将在此文件中进行参数设置，参考代码如下：

```swift
import UIKit

struct ColorsConfig {
    static let selectedText = UIColor.white
    static let text = UIColor.black
    static let textDisabled = UIColor.purple
    static let selectionBackground = UIColor(red: 1, green: 0.647, blue: 0, alpha: 1.0)
    static let sundayText = UIColor(red: 1.0, green: 0.2, blue: 0.2, alpha: 1.0)
    static let sundayTextDisabled = UIColor(red: 1.0, green: 0.6, blue: 0.6, alpha: 1.0)
    static let sundaySelectionBackground = sundayText
}
```



## 日历功能代码

```swift
import UIKit
import CVCalendar

class ProfileViewController: UIViewController {
    private var menuView:CVCalendarMenuView!
    private var calendarView:CVCalendarView!
    private var monthLabel:UILabel! = UILabel.init(frame: CGRect(x:157,y:80, width:120,height:60))
    private var daysOutSwitch:UISwitch!
    private var removeCircleAndDot = UIButton.self
    private var randomNumberOfDotMarkersForDay = [Int]()
    private var shouldShowDaysOut = true
    private var animationFinished = true
    private var selectedDay: DayView!
    private var currentCalendar: Calendar?
    
    override func awakeFromNib() {
        let timeZoneBias = 480 // (UTC+08:00)
        currentCalendar = Calendar(identifier: .gregorian)
        currentCalendar?.locale = Locale(identifier: "fr_FR")
        if let timeZone = TimeZone(secondsFromGMT: -timeZoneBias * 60) {
            currentCalendar?.timeZone = timeZone
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        monthLabel = UILabel.init(frame: CGRect(x: 157, y: 100, width: 104, height: 20))
        monthLabel.text = "my Calendar"
        if let currentCalendar = currentCalendar {
            monthLabel.text = CVDate(date: Date(), calendar: currentCalendar).globalDescription
        }
        randomizeDotMarkers()
        self.menuView = CVCalendarMenuView(frame: CGRect(x: 10, y: 130, width: 394, height: 15))

        // CVCalendarView initialization with frame
        self.calendarView = CVCalendarView(frame: CGRect(x: 10, y: 145, width: 394, height: 310))
        self.calendarView.calendarAppearanceDelegate = self
        self.calendarView.animatorDelegate = self

        // Menu delegate [Required]
        self.menuView.menuViewDelegate = self

        // Calendar delegate [Required]
        self.calendarView.calendarDelegate = self
        
        calendarView.backgroundColor = UIColor.tertiarySystemGroupedBackground
        self.view.addSubview(calendarView)
        self.view.addSubview(menuView)
        self.view.addSubview(monthLabel)
    }
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        calendarView.commitCalendarViewUpdate()
        menuView.commitMenuViewUpdate()
    }
    private func randomizeDotMarkers() {
        randomNumberOfDotMarkersForDay = [Int]()
        for _ in 0...31 {
            randomNumberOfDotMarkersForDay.append(Int(arc4random_uniform(3) + 1))
        }
    }
    
}

extension ProfileViewController: CVCalendarViewDelegate, CVCalendarMenuViewDelegate {
    // MARK: Required methods
    func presentationMode() -> CalendarMode { return .monthView }
    func firstWeekday() -> Weekday { return .sunday }
    // MARK: Optional methods
    
    func calendar() -> Calendar? { return currentCalendar }
    func dayOfWeekTextColor(by weekday: Weekday) -> UIColor {
        return weekday == .sunday ? UIColor(red: 1.0, green: 0, blue: 0, alpha: 1.0) : UIColor.white
    }
    func shouldShowWeekdaysOut() -> Bool { return shouldShowDaysOut }
    // Defaults to true
    func shouldAnimateResizing() -> Bool { return true }
    private func shouldSelectDayView(dayView: DayView) -> Bool {
        return arc4random_uniform(3) == 0 ? true : false
    }
    
    func shouldAutoSelectDayOnMonthChange() -> Bool { return false }
    func didSelectDayView(_ dayView: CVCalendarDayView, animationDidFinish: Bool) {
        selectedDay = dayView
    }
    
    func shouldSelectRange() -> Bool { return true }
    func didSelectRange(from startDayView: DayView, to endDayView: DayView) {
        print("RANGE SELECTED: \(startDayView.date.commonDescription) to \(endDayView.date.commonDescription)")
    }
    func presentedDateUpdated(_ date: CVDate) {
        if monthLabel.text != date.globalDescription && self.animationFinished {
            let updatedMonthLabel = UILabel()
            updatedMonthLabel.textColor = monthLabel.textColor
            updatedMonthLabel.font = monthLabel.font
            updatedMonthLabel.textAlignment = .center
            updatedMonthLabel.text = date.globalDescription
            updatedMonthLabel.sizeToFit()
            updatedMonthLabel.alpha = 0
            updatedMonthLabel.center = self.monthLabel.center
            
            let offset = CGFloat(48)
            updatedMonthLabel.transform = CGAffineTransform(translationX: 0, y: offset)
            updatedMonthLabel.transform = CGAffineTransform(scaleX: 1, y: 0.1)
            
            UIView.animate(withDuration: 0.35, delay: 0, options: UIView.AnimationOptions.curveEaseIn, animations: {
                self.animationFinished = false
                self.monthLabel.transform = CGAffineTransform(translationX: 0, y: -offset)
                self.monthLabel.transform = CGAffineTransform(scaleX: 1, y: 0.1)
                self.monthLabel.alpha = 0
                
                updatedMonthLabel.alpha = 1
                updatedMonthLabel.transform = CGAffineTransform.identity
                
            }) { _ in
                
                self.animationFinished = true
                self.monthLabel.frame = updatedMonthLabel.frame
                self.monthLabel.text = updatedMonthLabel.text
                self.monthLabel.transform = CGAffineTransform.identity
                self.monthLabel.alpha = 1
                updatedMonthLabel.removeFromSuperview()
            }
            
            self.view.insertSubview(updatedMonthLabel, aboveSubview: self.monthLabel)
        }
    }
    
    func topMarker(shouldDisplayOnDayView dayView: CVCalendarDayView) -> Bool { return true }
    
    func shouldHideTopMarkerOnPresentedView() -> Bool {
        return true
    }
    
    func weekdaySymbolType() -> WeekdaySymbolType { return .short }
    
    func selectionViewPath() -> ((CGRect) -> (UIBezierPath)) {
        return { UIBezierPath(rect: CGRect(x: 0, y: 0, width: $0.width, height: $0.height)) }
    }
    
    func shouldShowCustomSingleSelection() -> Bool { return false }
    
    func preliminaryView(viewOnDayView dayView: DayView) -> UIView {
        let circleView = CVAuxiliaryView(dayView: dayView, rect: dayView.frame, shape: CVShape.circle)
        circleView.fillColor = .colorFromCode(0xCCCCCC)
        return circleView
    }
    
    func preliminaryView(shouldDisplayOnDayView dayView: DayView) -> Bool {
        if (dayView.isCurrentDay) {
            return true
        }
        return false
    }
    
    func supplementaryView(viewOnDayView dayView: DayView) -> UIView {
        
        dayView.setNeedsLayout()
        dayView.layoutIfNeeded()
        
        let π = Double.pi
        
        let ringLayer = CAShapeLayer()
        let ringLineWidth: CGFloat = 4.0
        let ringLineColour = UIColor.blue
        
        let newView = UIView(frame: dayView.frame)
        
        let diameter = (min(newView.bounds.width, newView.bounds.height))
        let radius = diameter / 2.0 - ringLineWidth
        
        newView.layer.addSublayer(ringLayer)
        
        ringLayer.fillColor = nil
        ringLayer.lineWidth = ringLineWidth
        ringLayer.strokeColor = ringLineColour.cgColor
        
        let centrePoint = CGPoint(x: newView.bounds.width/2.0, y: newView.bounds.height/2.0)
        let startAngle = CGFloat(-π/2.0)
        let endAngle = CGFloat(π * 2.0) + startAngle
        let ringPath = UIBezierPath(arcCenter: centrePoint,
                                    radius: radius,
                                    startAngle: startAngle,
                                    endAngle: endAngle,
                                    clockwise: true)
        
        ringLayer.path = ringPath.cgPath
        ringLayer.frame = newView.layer.bounds
        
        return newView
    }
    
    func supplementaryView(shouldDisplayOnDayView dayView: DayView) -> Bool {
        guard let currentCalendar = currentCalendar else { return false }
        
        let components = Manager.componentsForDate(Foundation.Date(), calendar: currentCalendar)
        
        /* For consistency, always show supplementaryView on the 3rd, 13th and 23rd of the current month/year.  This is to check that these expected calendar days are "circled". There was a bug that was circling the wrong dates. A fix was put in for #408 #411.
         
         Other month and years show random days being circled as was done previously in the Demo code.
         */
        var shouldDisplay = false
        if dayView.date.year == components.year &&
            dayView.date.month == components.month {
            
            if (dayView.date.day == 3 || dayView.date.day == 13 || dayView.date.day == 23)  {
                print("Circle should appear on " + dayView.date.commonDescription)
                shouldDisplay = true
            }
        } else if (Int(arc4random_uniform(3)) == 1) {
            shouldDisplay = true
        }
        
        return shouldDisplay
    }
    
    func dayOfWeekTextColor() -> UIColor { return .white }
    
    func dayOfWeekBackGroundColor() -> UIColor { return .orange }
    
    func disableScrollingBeforeDate() -> Date { return Date() }
    
    func maxSelectableRange() -> Int { return 14 }
    
    func earliestSelectableDate() -> Date { return Date() }
    
    func latestSelectableDate() -> Date {
        var dayComponents = DateComponents()
        dayComponents.day = 70
        let calendar = Calendar(identifier: .gregorian)
        if let lastDate = calendar.date(byAdding: dayComponents, to: Date()) {
            return lastDate
        }
        
        return Date()
    }
}


// MARK: - CVCalendarViewAppearanceDelegate

extension ProfileViewController: CVCalendarViewAppearanceDelegate {
    
    func dayLabelWeekdayDisabledColor() -> UIColor { return .lightGray }
    
    func dayLabelPresentWeekdayInitallyBold() -> Bool { return false }
    
    func spaceBetweenDayViews() -> CGFloat { return 0 }
    
    func dayLabelFont(by weekDay: Weekday, status: CVStatus, present: CVPresent) -> UIFont { return UIFont.systemFont(ofSize: 14) }
    
    func dayLabelColor(by weekDay: Weekday, status: CVStatus, present: CVPresent) -> UIColor? {
        switch (weekDay, status, present) {
        case (_, .selected, _), (_, .highlighted, _): return ColorsConfig.selectedText
        case (.sunday, .in, _): return ColorsConfig.sundayText
        case (.sunday, _, _): return ColorsConfig.sundayTextDisabled
        case (_, .in, _): return ColorsConfig.text
        default: return ColorsConfig.textDisabled
        }
    }
    
    func dayLabelBackgroundColor(by weekDay: Weekday, status: CVStatus, present: CVPresent) -> UIColor? {
        switch (weekDay, status, present) {
        case (.sunday, .selected, _), (.sunday, .highlighted, _): return ColorsConfig.sundaySelectionBackground
        case (_, .selected, _), (_, .highlighted, _): return ColorsConfig.selectionBackground
        default: return nil
        }
    }
}
// MARK: - IB Actions

// MARK: - Convenience API Demo
extension ProfileViewController {
    func toggleMonthViewWithMonthOffset(offset: Int) {
        guard let currentCalendar = currentCalendar else { return }
        var components = Manager.componentsForDate(Date(), calendar: currentCalendar) // from today
        components.month! += offset
        let resultDate = currentCalendar.date(from: components)!   
        self.calendarView.toggleViewWithDate(resultDate)
    }
    
    func didShowNextMonthView(_ date: Date) {
        guard let currentCalendar = currentCalendar else { return }
        let components = Manager.componentsForDate(date, calendar: currentCalendar) // from today
        print("Showing Month: \(components.month!)")
    }
    
    
    func didShowPreviousMonthView(_ date: Date) {
        guard let currentCalendar = currentCalendar else { return }
        let components = Manager.componentsForDate(date, calendar: currentCalendar) // from today        
        print("Showing Month: \(components.month!)")
    }

    func didShowNextWeekView(from startDayView: DayView, to endDayView: DayView) {
        print("Showing Week: from \(startDayView.date.day) to \(endDayView.date.day)")
    }
  
    func didShowPreviousWeekView(from startDayView: DayView, to endDayView: DayView) {
        print("Showing Week: from \(startDayView.date.day) to \(endDayView.date.day)")
    }
    
}
```



## 思考与优化

1. 在统计功能部分，我们在内存加载时添加了大量控件，这会造成系统极度卡顿，如何优化上文代码？如何优化整个系统的性能？如何在协议中拓展textFiled的代理功能？（你可以参考`懒加载`相关概念，并试着利用数组来代替大量而重复的控件代码。）
2. 在日历部分，我们并没有将所有的功能都放到主函数中，而是采用了`协议`进行拓展，这是面向协议编程的特性之一。请你试着思考：采取这种方式有什么优点？为什么我们在加载控件时采用private？这对整个程序有什么帮助？如何美化整个Calendar页面？（你可以参考一些精美的UI设计，并在Github相关专题上寻找优化程序的答案）

