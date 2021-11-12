# 基本权限与url访问

## 分享图片

我们的 app 肯定会用到各种权限，权限的申请也是开发者需要学习的一门课程，本章中提供了一些基本的权限请求方式，在后面的基础功能部分你将学习更多的内容。

此部分提供了分享照片的基本权限，我们可以通过一个 Button 来分享照片：

```swift
@IBOutlet var imageView: UIView!
@IBAction func shareButton(_ sender: Any) {
  guard let image = imageView else { return }
  let activityController = UIActivityViewController(activityItems:[image],applicationActivities: nil)
  activityController.popoverPresentationController?.sourceView = sender as? UIView present(activityController, animated: true, completion: nil)
}
```



## 网页链接

如果想要 `Button` 连接到浏览器，则需要对网页做出请求，在此前需要导入 `Safari Services` 头文件：

```swift
import SafariServices

@IBAction func Safari(_ sender: Any) {
  if let url = URL(string: "http://www.bilibili.com") {
    let safariViewController = SFSafariViewController(url:url) 
    present(safariViewController, animated: true,completion: nil)
  }
}
```



## 提示与弹窗

如果想要获得照片权限，你需要一个弹窗，不仅如此，在选择头像的功能中，你也需要弹窗提示：从照片中选择、拍摄照片和取消三个部分：

```swift
let alertController = UIAlertController(title: "Choose Image Source", message: nil, preferredStyle: .actionSheet)

let cancelAction = UIAlertAction(title: "Cancel", style: .cancel, handler: nil)
let cameraAction = UIAlertAction(title: "Camera", style: .default, handler: {action in print("User selected Camera action") })

let photoLibraryAction = UIAlertAction(title: "Photo Library", style: .default, handler: { action in print("User selected Photo Library action") })

alertController.addAction(cancelAction) 
alertController.addAction(cameraAction) alertController.addAction(photoLibraryAction)

alertController.popoverPresentationController?.sourceView = sender as? UIView present(alertController, animated: true, completion: nil)

let alertController = UIAlertController(title: "Choose Image Source", message: nil, preferredStyle: .actionSheet)

let cancelAction = UIAlertAction(title: "Cancel", style: .cancel, handler: nil)
let cameraAction = UIAlertAction(title: "Camera", style: .default, handler: { action in print("User selected Camera action") })
let photoLibraryAction = UIAlertAction(title: "Photo Library", style: .default, handler: { action in print("User selected Photo Library action") })

alertController.addAction(cancelAction) 
alertController.addAction(cameraAction) alertController.addAction(photoLibraryAction) alertController.popoverPresentationController?.sourceView = sender as? UIView present(alertController, animated: true, completion: nil)
```



## 请求相机权限

iOS对相机以及其他各种权限的设置较为复杂，目前通用有两种手段获取相机权限：第一种通过 `ImagePickerController` 来实现，智能识别部分用到的就是这个，但是设置起来十分繁琐。不过好在还有第二种，通过 `Info` 中设置相关参数，直接访问即可，在 `Info.plist` 文件中添加：

```swift
Privacy - Photo Library Additions Usage Description
```

赋值 `value`：

```swift
To share photos from camera or photo library
```

相机请求：

```swift
@IBAction func photosButtonTapped(_ sender: UIButton) {...}

func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [String : Any]) {
  if let selectedImage = info[UIImagePickerControllerOriginalImage] as? UIImage {
    imageView.image = selectedImage
    dismiss(animated: true, completion: nil)
  }
}
```

