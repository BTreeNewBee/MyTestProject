package kt.freemarker

import freemarker.template.Configuration
import freemarker.template.Template
import freemarker.template.TemplateExceptionHandler
import org.apache.commons.math3.stat.descriptive.summary.Product
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.io.Writer


fun main() {


    val cfg = Configuration(Configuration.VERSION_2_3_31)
    //get current directory
    val currentDir = System.getProperty("user.dir")
    println(currentDir)
    cfg.setDirectoryForTemplateLoading(File(currentDir))
    cfg.setDefaultEncoding("UTF-8")
    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER)
    cfg.setLogTemplateExceptions(false)
    cfg.setWrapUncheckedExceptions(true)
    cfg.setFallbackOnNullLoopVariable(false)

    val year = 2020
    val datLeft = 364
    val date = "2020-01-01"
    val messageCount = 100
    val arrayListOf = arrayListOf(
        RankInfo(1, "", "我的龟龟", 100), RankInfo(2, "", "我的牛牛", 100), RankInfo(3, "", "我的小龟龟", 100)
    )

    var rate = datLeft / 365.0 ;
    val red = (255 * rate).toInt() shl 16
    val green = (255 *  (1 - rate)).toInt() shl 8
    val hex = Integer.toHexString(red or green)
    println(hex)
    val color = "#$hex"

    val root = MessageRank(year, date, datLeft, rate * 100, color, messageCount, arrayListOf)


    /* Get the template (uses cache internally) */
    val temp: Template = cfg.getTemplate("sort_template.html")

    /* Merge data-model with template */

    /* Merge data-model with template */
    val out: Writer = OutputStreamWriter(System.out)
    FileOutputStream("test.html").use { fos ->
        OutputStreamWriter(fos).use { osw ->
            temp.process(root, osw)
        }
    }
    temp.process(root, out)
    // Note: Depending on what `out` is, you may need to call `out.close()`.
    // This is usually the case for file output, but not for servlet output.


}
