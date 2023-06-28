package kt.javaClass

import org.bytedeco.opencv.opencv_core.*
import org.bytedeco.opencv.opencv_imgproc.*
import org.bytedeco.opencv.global.opencv_core.*
import org.bytedeco.opencv.global.opencv_highgui.imshow
import org.bytedeco.opencv.global.opencv_imgproc.*
import org.bytedeco.opencv.global.opencv_imgcodecs.*

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO


fun main() {

    func("yes")

}


operator fun Any.invoke() {
    println("invoke")
}

fun func(any:Any){
    any()
}