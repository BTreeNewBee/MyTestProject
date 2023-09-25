package kt.draws

import java.awt.Color
import java.awt.Font
import java.awt.FontMetrics
import java.awt.RenderingHints
import java.awt.font.TextLayout
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_INT_RGB
import java.io.File
import javax.imageio.ImageIO

import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.image.WritableImage
import javafx.scene.control.Label
import javafx.scene.image.Image

// ...



fun main() {

    var str = "龙王排行榜\n" +
            "今天是2022-12-12日，今年的第346天，您的2022年使用进度条：\n" +
            "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░ 94.79% \n" +
            "本群2022-12-12日消息总量：187条\n" +
            "第1名：ForeverNuclear ，59条消息\n" +
            "第2名：ncturneon ，44条消息\n" +
            "第3名：我的龟龟🐢🐢，31条消息\n" +
            "第4名：中森榨菜(快递难产版) ，29条消息\n" +
            "第5名：我凭什么受啊我！ ，10条消息\n" +
            "第6名：卢小白 ，7条消息\n" +
            "第7名：书桓 ，5条消息\n" +
            "第8名：床垫被分解了分线完成、该去完成主线任务了 ，1条消息\n" +
            "第9名：真的我宁可听你唱你干嘛哈哈哎哟 ，1条消息"


    var strList: List<String> = arrayListOf(
        "测试测试我测试测试我测试测试",
        "测试测试我",
        "测试",
        "测试测试我",
        "测试测试我",
        "123456789",
        "ABCDIIIILLLiii",
        "测试测试我测试测试我测试测试",
        "iiii1111..??``"
    )

    strList = str.split("\n")

    var fontSize = 40f

//    val deriveFont =
//        Font.createFont(Font.TRUETYPE_FONT, File("D:\\font\\SourceHanSansHWSC-Regular.otf")).deriveFont(fontSize)
//    val fontMetrics = BufferedImage(1, 1, TYPE_INT_RGB).createGraphics().getFontMetrics(deriveFont)
//
//    var width = strList.map { it.width(fontMetrics) }.maxByOrNull { it } ?: 0
//    width += (fontSize * 2f).toInt()
//    val height = (strList.size * fontSize * 1.5 + fontSize).toInt()

    var width = 406
    var height = 1000

    var image = BufferedImage(width, height, TYPE_INT_RGB)
    val g = image.createGraphics()
//    g.font = deriveFont

    //fill as white
    g.fillRect(0, 0, width, height)

    g.color = Color.BLACK

    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
    var tmpY = fontSize * 1.5f
    var yStep = fontSize
    strList.forEach {
        val tl = TextLayout(it, g.font, g.fontRenderContext)
        tl.draw(g, fontSize, tmpY)
        tmpY += yStep
//        yStep += 10
    }

    g.dispose()

    ImageIO.write(image, "png", File("C:\\bot\\2.png"))



}


fun String.width(fm: FontMetrics): Int {
    return fm.stringWidth(this)
}