# 代码：RoomTypeTableViewController

```swift
protocol RoomTypeTableViewControllerDelegate:class { 
  //协议传值，自定义 RoomType 类型
  func didSelect(roomType: RoomType)
}

//定义结构体roomtType，static func若放到结构体外会报错
struct RoomType: Equatable { 
  var id: Intvar name: String
  var shortName: String
  var price: Int
  static func ==(lhs: RoomType, rhs: RoomType) -> Bool {
    return lhs.id == rhs.id 
  }
}

class RoomTypeTableViewController: UITableViewController { 
  weak var delegate: RoomTypeTableViewControllerDelegate? 
  static var all: [RoomType] { 
    return
    [RoomType(id: 0, name: "单人间", shortName: "1 人住", price: 179),
     RoomType(id: 1, name: "双人间", shortName: "2 人住", price: 209), 
     RoomType(id: 2, name: "总统套房", shortName: "3人住", price:309)]
  }
  
  var currentRoomT:String = "" 
  override func viewDidLoad() {
    super.viewDidLoad() 
  }
  
  //新页面只需要一个 section
  override func numberOfSections(in tableView: UITableView) -> Int {
    return 1 
  }
  //该 section 共有 all.count 个 row
  override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
    return RoomTypeTableViewController.all.count 
  }
  //接下来定义 cell
  override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
    //value1 为 style 中的 Right detail
    let cell:UITableViewCell = UITableViewCell.init(style: UITableViewCell.CellStyle.value1, reuseIdentifier: nil)
    //将数组导入定义中
    let roomType = RoomTypeTableViewController.all[indexPath.row]
    cell.textLabel?.text = roomType.name 
    cell.detailTextLabel?.text = "$ \(roomType.price)"
    
    //选中后出现Right
    if roomType == self.roomType { 
      cell.accessoryType = .checkmark 
      self.currentRoomT = roomType.name
    }else {
      cell.accessoryType = .none
    }
    return cell 
  }
  var roomType: RoomType?
  //1-2 传值
  override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
    tableView.deselectRow(at: indexPath, animated: true) 
    roomType = RoomTypeTableViewController.all[indexPath.row]
    delegate?.didSelect(roomType: roomType!) 
    tableView.reloadData()
  } 
}
```

