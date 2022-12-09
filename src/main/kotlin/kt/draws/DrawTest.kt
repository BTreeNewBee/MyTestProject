package kt.draws

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


    val image: BufferedImage = ImageIO.read(
        File("C:\\bot\\template.png")
    )

    val g = image.graphics
    g.font = g.font.deriveFont(30f)

    g.drawString("测试测试\n我的龟龟123\n我的我的", 100, 100)

    g.dispose()

    ImageIO.write(image, "png", File("C:\\bot\\2.png"))

}