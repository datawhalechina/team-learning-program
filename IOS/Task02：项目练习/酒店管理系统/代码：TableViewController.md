# 代码：TableViewController

```swift
class TableViewController: UITableViewController,RoomTypeTableViewControllerDelegate{
  //定义基本输入量
  @IBOutlet weak var nameLabel: UITextField! 
  @IBOutlet weak var telNumberLabel: UITextField! 
  @IBOutlet weak var IDCardLabel: UITextField!
  @IBOutlet weak var checkInDateLabel: UILabel! 
  @IBOutlet weak var checkOutDateLabel: UILabel! 
  @IBOutlet weak var checkInDatePicker: UIDatePicker! 
  @IBOutlet weak var checkOutDatePicker: UIDatePicker! 
  @IBOutlet weak var roomTypeSelected: UILabel!
  var roomType: RoomType?
  
  //继承后的传值种类
  func didSelect(roomType: RoomType) { 
    self.roomType = roomType updateRoomType()
  }
  
  //取消当前页面(动画)
  @IBAction func cancelOfCurrentPage(_ sender: UIBarButtonItem) { 
    dismiss(animated: true, completion: nil)
  }
  
  //Done 按钮的设置，按下时对当前页面信息进行保存
  @IBAction func doneBarButtonTapped(_ sender: Any) { 
    let name = nameLabel.text ?? ""
    let telNumber = telNumberLabel.text ?? ""
    let IDNumber = IDCardLabel.text ?? ""
    let checkInDate = checkInDatePicker.date
    let checkOutDate = checkOutDatePicker.date
    let numberOfAdults = Int(numberOfAdultsStepper.value) 
    let numberOfChildren = Int(numberOfKidsStepper.value) 
    let hasWifi = wifiSwitch.isOn
    let roomChoice = roomType?.name
    
    print("信息录入:")
    print("姓名: \(name)")
    print("电话: \(telNumber)")
    print("身份证号: \(IDNumber)")
    print("入住日期: \(checkInDate)")
    print("退房日期: \(checkOutDate)")
    print("成人: \(numberOfAdults)")
    print("儿童: \(numberOfChildren)")
    print("wifi: \(hasWifi)")
    print("房间类型: \(String(describing: roomChoice))")
  }
  
  //以下对成人、儿童数量进行统计
  @IBOutlet weak var numberOfAdultsLabel: UILabel! 
  @IBOutlet weak var numberOfKidsLabel: UILabel! 
  @IBOutlet weak var numberOfKidsStepper: UIStepper!
  @IBOutlet weak var numberOfAdultsStepper: UIStepper!
  
  //更新客人数量
  func updateNumberOfGuests() {
    numberOfAdultsLabel.text = "\(Int(numberOfAdultsStepper.value))"
    numberOfKidsLabel.text = "\(Int(numberOfKidsStepper.value))"
  }
  
  //更新房间类型
  func updateRoomType() {
    if let roomType = roomType {
      roomTypeSelected.text = roomType.name 
    }else{
      roomTypeSelected.text = "请选择"
      //tips:若此处值为空，则后面无论如何都不会显示
    } 
  }
  
  //检测 stepper 是否变化，若变化则进行实时动态更新
  @IBAction func stepperDidChanged(_ sender: UIStepper) { 
    updateNumberOfGuests()
  }
  
  //以下为 WI-FI 内容设置
  @IBOutlet weak var wifiSwitch: UISwitch! 
  @IBAction func WiFiSwitch(_ sender: UISwitch) {
    //自定义一些内容
  }
  
  //在此处进行两个动态更新:1.更新顾客数量，2.更新房间种类
  override func viewDidLoad() { 
    super.viewDidLoad() 
    updateNumberOfGuests() 
    updateRoomType()
  }
  
  //设置单元格 section
  override func numberOfSections(in tableView: UITableView) -> Int { 
    return 5
  }
  
  //设置每个 section 的 cell 数量
  override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
    if section == 0{ 
      return 3
    }else if section == 1{ 
      return 4
    }else if section == 2{ 
      return 2
    }else if section == 3{ 
      return 1
    }else { 
      return 1
    } 
  }
  
  //代码初始化入住日期、退房日期，此处若与 storyboard 中不同，程序会崩溃
  let checkInDatePickerCellIndexPath = IndexPath(row: 1, section: 1) 
  let checkOutDatePickerCellIndexPath = IndexPath(row: 3, section: 1) 
  //入住日期
  var isCheckInDatePickerShown: Bool = false {
    didSet {
      checkInDatePicker.isHidden = !isCheckInDatePickerShown
    } 
  }
  
  //退房日期
  var isCheckOutDatePickerShown: Bool = false { 
    didSet {
      checkOutDatePicker.isHidden = !isCheckOutDatePickerShown 
    }
  }
  
  //设置 cell 高度，此处若与 storyboard 中不同，程序会崩溃
  override func tableView(_ tableView: UITableView,heightForRowAt indexPath: IndexPath) -> CGFloat {
    //通过 switch 选择 Bool 值初始化 static cell
    switch (indexPath.section, indexPath.row) { 
      case (checkInDatePickerCellIndexPath.section, checkInDatePickerCellIndexPath.row): 
      if isCheckInDatePickerShown { 
        return 216.0
      }else{ 
        return 0.0
      }
      case (checkOutDatePickerCellIndexPath.section, checkOutDatePickerCellIndexPath.row): if isCheckOutDatePickerShown {
        return 216.0 
      }else{
        return 0.0 
      }
      default: return 44.0
    } 
  }
  
  //选中后隐藏
  override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath){ 
    tableView.deselectRow(at: indexPath, animated: true)
    //选中后第二个 section 的 row 减 1，对 Bool 值进行赋值
    switch (indexPath.section, indexPath.row) {
      case (
        checkInDatePickerCellIndexPath.section, checkInDatePickerCellIndexPath.row - 1):
      if isCheckInDatePickerShown {
        isCheckInDatePickerShown = false 
      } else if isCheckOutDatePickerShown { 
        isCheckOutDatePickerShown = false
        isCheckInDatePickerShown = true 
      }else{
        isCheckInDatePickerShown = true 
      }
      tableView.beginUpdates() 
      tableView.endUpdates()
      
      case (checkOutDatePickerCellIndexPath.section,
            checkOutDatePickerCellIndexPath.row - 1):
      if isCheckOutDatePickerShown { 
        isCheckOutDatePickerShown = false
      } else if isCheckInDatePickerShown { 
        isCheckInDatePickerShown = false 
        isCheckOutDatePickerShown = true
      }else{
        isCheckOutDatePickerShown = true
      }
      tableView.beginUpdates() 
      tableView.endUpdates() 
      default:
      break
    }
    
    //转换 Date 格式，将 Date 变为 String，此处代码放到以上设置前会报错，程序崩溃
    let dateFormatter = DateFormatter() 
    dateFormatter.dateStyle = .medium 
    /*dateStyle 中:
    .none:无
    .short:“11/23/37” 
    .medium:"Nov 23, 1937" 
    .long:"Novermber 23, 1937" 
    .full:"Tuesday,April 12,1952" */
    
    /*timeStyle 中:
    .none:无
    .short:"3:30 PM"
    .medium:"3:30:32 PM"
    .long:"3:30:32 PM PST"
    .full:"3:30:42 PM Pacific Standard Time"*/
    
    checkInDateLabel.text = dateFormatter.string(from: checkInDatePicker.date)
    checkOutDateLabel.text = dateFormatter.string(from:checkOutDatePicker.date)
  }
  
  override func prepare(for segue: UIStoryboardSegue, sender: Any?) { 
    if segue.identifier == "SelectRoomType" {
      let destinationViewController = segue.destination as? RoomTypeTableViewController 
      destinationViewController?.delegate = self
      destinationViewController?.roomType = roomType
    } 
  }
  
  //1-3 页面传值
  var registration: Registration? {
    guard let roomType = roomType else { 
      return nil } 
    let name = nameLabel.text ?? ""
    let telNumber = telNumberLabel.text ?? ""
    let IDCard = IDCardLabel.text ?? ""
    let checkInDate = checkInDatePicker.date
    let checkOutDate = checkOutDatePicker.date
    let numberOfAdults = Int(numberOfAdultsStepper.value) 
    let numberOfChildren = Int(numberOfKidsStepper.value)
    let hasWifi = wifiSwitch.isOn
    return Registration(name: name, telNumber: telNumber, IDNumber: IDCard, checkInDate: checkInDate, checkOutDate: checkOutDate, numberOfAdults: numberOfAdults, numberOfChildren: numberOfChildren, roomType: roomType, wifi: hasWifi)
  } 
}
```

