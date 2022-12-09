package kt.draws

import java.awt.Color
import java.awt.Font
import java.awt.RenderingHints
import java.awt.font.TextLayout
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO


fun main() {
    val image: BufferedImage = ImageIO.read(
        File("D:\\bot\\template.png")
    )

    val g = image.createGraphics()
    val deriveFont = Font.createFont(Font.TRUETYPE_FONT, File("D:\\font\\SourceHanSansSC-Light-2.otf")).deriveFont(60f)
    g.font = deriveFont
    g.color = Color.BLACK

//    val txt = JTextArea()
//    txt.font = Font.createFont(Font.TRUETYPE_FONT, File("D:\\font\\SourceHanSansCN-VF-2.otf")).deriveFont(20f)
//    txt.foreground = Color.black

    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
    val tl = TextLayout("测试测试我的龟龟我的测试测试我的龟龟我的测试测试我的龟龟我的测试测试我的龟龟我的", deriveFont, g.fontRenderContext)
//    g.drawString("测试测试我的龟龟我的", 200, 200)
    tl.draw(g, 200f, 200f)
    g.dispose()

    ImageIO.write(image, "png", File("D:\\bot\\2.png"))

}