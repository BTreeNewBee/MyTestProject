package kt.freemarker

import cn.hutool.core.codec.Base64
import cn.hutool.core.io.FileUtil
import com.ruiyun.jvppeteer.core.Puppeteer
import com.ruiyun.jvppeteer.core.browser.Browser
import com.ruiyun.jvppeteer.core.browser.BrowserFetcher
import com.ruiyun.jvppeteer.core.page.Page
import com.ruiyun.jvppeteer.options.*
import freemarker.template.Configuration
import freemarker.template.Template
import freemarker.template.TemplateExceptionHandler
import kotlinx.coroutines.*
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors


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


    BrowserFetcher.downloadIfNotExist(null)
    val arrayList = ArrayList<String>()
    val options: LaunchOptions = LaunchOptionsBuilder().withArgs(arrayList).withHeadless(true).build()
    arrayList.add("--no-sandbox")
    arrayList.add("--disable-setuid-sandbox")
    val browser: Browser = Puppeteer.launch(options)


    val template: Template = cfg.getTemplate("sort_template.html")

    val root = getRankInfo()

    val threadPool = Executors.newFixedThreadPool(10)

    for (x in 1 .. 20) {

        val currentTimeMillis = System.currentTimeMillis()
        val countDownLatch = CountDownLatch(x)
        for (i in 1..x) {
            threadPool.execute({
                val file = File.createTempFile("wodeguigui$i", ".html")
                FileOutputStream(file).use { fos ->
                    OutputStreamWriter(fos).use { osw ->
                        template.process(root, osw)
                    }
                }

                val page: Page = browser.newPage()
                page.goTo(file.absolutePath)

                val deviceScaleFactor = 4.0

                //set page size
                page.setViewport(Viewport(500, 800, deviceScaleFactor, false, false, false))

                var height = 240.0
                height += 40 * 6
                height += 40

                val screenshotOptions = ScreenshotOptions()
                //设置截图范围
                val clip = Clip(0.0, 0.0, 500.0, height)
                screenshotOptions.clip = clip
                //设置存放的路径
                screenshotOptions.path = "test$i.png"

                page.screenshot(screenshotOptions)
                page.close()
                countDownLatch.countDown()
            })
        }

        countDownLatch.await()
        println("截图量${x}用时${System.currentTimeMillis() - currentTimeMillis}")
    }

    browser.close();
    threadPool.shutdown()
}


fun getRankInfo(): MessageRank {
    val year = 2020
    val datLeft = 350
    val date = "2020-01-01"
    val messageCount = 100
    val arrayListOf = arrayListOf(
        RankInfo(1, Base64.encode(FileUtil.readBytes("C:\\git\\java\\MyTestProject\\avatar1.bmp")), "傻逼爱抖露", 100),
        RankInfo(2, Base64.encode(FileUtil.readBytes("C:\\git\\java\\MyTestProject\\avatar1.bmp")), "我的牛牛", 100),
        RankInfo(3, Base64.encode(FileUtil.readBytes("C:\\git\\java\\MyTestProject\\avatar2.bmp")), "我的龟龟", 100),
        RankInfo(4, Base64.encode(FileUtil.readBytes("C:\\git\\java\\MyTestProject\\avatar2.bmp")), "我的小牛牛", 100),
        RankInfo(5, Base64.encode(FileUtil.readBytes("C:\\git\\java\\MyTestProject\\avatar2.bmp")), "傻逼倒爷", 100)
    )


    val rate = datLeft / 365.0
    val hue = (120 * (1 - rate)).toInt()
    val color = "hsl($hue, 80%, 45%)"

    val root = MessageRank(year, date, datLeft, rate * 100, color, messageCount, arrayListOf)
    return root
}