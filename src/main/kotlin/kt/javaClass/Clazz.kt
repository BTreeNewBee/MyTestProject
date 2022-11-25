package kt.javaClass

import java.awt.Font
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO


fun main() {
    var s:String = ""

    var a = 1

    println(s.javaClass.isAssignableFrom(Comparable::class.java))

    val image: BufferedImage = ImageIO.read(
        File("C:\\bot\\1.png")
    )

    val g = image.graphics
    g.font = g.font.deriveFont(30f)
    g.drawString("Hello World1111111!", 100, 100)

    g.dispose()

    ImageIO.write(image, "png", File("C:\\bot\\2.png"))

}