# iOS端智能识别水果

## 参考教程

本部分参考自浙江大学Swift创新导论：

https://www.icourse163.org/course/ZJU-1450024180?from=searchPage

你可以查看相应章节，对应代码如下：

```swift
import UIKit
class ViewController: UIViewController{
  var pickerController = UIImagePickerController()
  @IBOutlet weak var nameLabel: UILabel! 
  @IBOutlet weak var confidenceLabel: UILabel! 
  @IBOutlet weak var imageView: UIImageView! 
  @IBAction func recognaizeButton(_ sender: Any) {
    pickerController.sourceType = .photoLibrary 
    self.present(pickerController, animated:true, completion:nil)
  }
  let model = fruitRecognizer_1()
  override func viewDidLoad() { 
    super.viewDidLoad() 
    pickerController.delegate = self
  } 
}

extension ViewController: UINavigationControllerDelegate, UIImagePickerControllerDelegate {
  func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey: Any]){
    guard let image = info[.originalImage]as? UIImage else{
      fatalError("Expected a dictionary containing an image, but was provided the following:\(info)")
    }
    imageView.image = image
    
    guard let fruitRecognizer_1Output = try? model.prediction(image: buffer(from:image)!)else{
      fatalError("Unexpected runtime error") 
    }
    
    nameLabel.text = "Fruit Name:\(fruitRecognizer_1Output.classLabel)"
    confidenceLabel.text = "Possibility:\(fruitRecognizer_1Output.classLabelProbs[fruitRecognizer_1Output. classLabel]!)"
    pickerController.dismiss(animated:true, completion: nil) 
  }
  
  func buffer(from image: UIImage) ->CVPixelBuffer?{
    let attrs = [kCVPixelBufferCGImageCompatibilityKey: kCFBooleanTrue, kCVPixelBufferCGBitmapContextCompatibilityKey: kCFBooleanTrue]as CFDictionary
    var pixelBuffer : CVPixelBuffer?
    let status = CVPixelBufferCreate(kCFAllocatorDefault,
kCVPixelFormatType_32ARGB,attrs,&pixelBuffer) 
    guard(status == kCVReturnSuccess)else {
      return nil
    }
    
    CVPixelBufferLockBaseAddress(pixelBuffer!,CVPixelBufferLockFlags(rawValue:0))
    let pixelData = CVPixelBufferGetBaseAddress(pixelBuffer!)
    let rgbColorSpace = CGColorSpaceCreateDeviceRGB() 
    let context = CGContext(data: pixelData, width:Int (image.size.width), height: Int(image.size.height), bitsPerComponent: 8, bytesPerRow: CVPixelBufferGetBytesPerRow(pixelBuffer!),space: rgbColorSpace,bitmapInfo: CGImageAlphaInfo.noneSkipFirst.rawValue)
    context?.translateBy(x: 0, y: image.size.height)
    context?.scaleBy(x: 1.0, y: -1.0) 
    UIGraphicsPushContext(context!)
    image.draw(in:CGRect(x:0,y:0, width:image.size.width,
height:image.size.height))
    UIGraphicsPushContext(context!)
    image.draw(in: CGRect(x: 0, y: 0, width: image.size.width, height: image.size.height))
    UIGraphicsPopContext()
    CVPixelBufferUnlockBaseAddress(pixelBuffer!, CVPixelBufferLockFlags(rawValue: 0))
    return pixelBuffer 
  }
}
```

