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


    var strList : List<String> = str.split("\n")

    var fontSize = 80f
////
//    val deriveFont =
//        Font.createFont(Font.TRUETYPE_FONT, File("D:\\font\\SourceHanSansHWSC-Regular.otf")).deriveFont(fontSize)
//    val deriveFont = Font("Mono",Font.BOLD,60)
    val deriveFont =
        Font.createFont(Font.TRUETYPE_FONT, File("D:\\font\\NotoColorEmoji-emojicompat.ttf")).deriveFont(fontSize)
    val fontMetrics = BufferedImage(1, 1, TYPE_INT_RGB).createGraphics().getFontMetrics(deriveFont)

    var width = strList.map { it.width(fontMetrics) }.maxByOrNull { it } ?: 0
    width += (fontSize * 2f).toInt()
    val height = (strList.size * fontSize * 1.5 + fontSize).toInt()



    var image = BufferedImage(width, height, TYPE_INT_RGB)
    val g = image.createGraphics()
    g.font = deriveFont

    //fill as white
    g.fillRect(0, 0, width, height)

    g.color = Color.BLACK

    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
    var tmpY = fontSize * 1.5f
    var yStep = fontSize * 1.5f
    strList.forEach {
        val toCharArray = it.toCharArray()
        //check character is emoji
        toCharArray.forEach { c ->

        }

//        val tl = TextLayout(it, deriveFont, g.fontRenderContext)
//        tl.draw(g, fontSize, tmpY)
//        tmpY += yStep
    }

    g.dispose()

    ImageIO.write(image, "png", File("D:\\bot\\2.png"))
}


fun String.width(fm: FontMetrics): Int {
    return fm.stringWidth(this)
}