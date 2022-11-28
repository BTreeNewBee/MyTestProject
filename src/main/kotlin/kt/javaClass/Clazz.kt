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
    var s: String = ""

    var a = 1

    println(s.javaClass.isAssignableFrom(Comparable::class.java))

    val image: BufferedImage = ImageIO.read(
        File("C:\\bot\\1.png")
    )

    val g = image.graphics
    g.font = g.font.deriveFont(30f)
    g.drawString("测试测试\n我的龟龟123\n我的我的", 100, 100)

    g.dispose()

    ImageIO.write(image, "png", File("C:\\bot\\2.png"))


    val file = "C:\\bot\\1.png"
    val src: Mat = imread(file)
    val text = "JavaFX 2D shapes"
    val position = Point(50, 50)
    putText(src, text, position, 1, 2.0, Scalar(0.0, 255.0, 0.0, 2.0))

    imwrite("C:\\bot\\3.png", src)
    println("yes")

}