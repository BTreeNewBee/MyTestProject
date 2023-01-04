package kt.puppeteer

import com.ruiyun.jvppeteer.core.Puppeteer
import com.ruiyun.jvppeteer.core.browser.Browser
import com.ruiyun.jvppeteer.core.browser.BrowserFetcher
import com.ruiyun.jvppeteer.core.page.Page
import com.ruiyun.jvppeteer.options.*
import java.io.File


fun main() {
    BrowserFetcher.downloadIfNotExist(null)
    val arrayList = ArrayList<String>()
    val options: LaunchOptions = LaunchOptionsBuilder().withArgs(arrayList).withHeadless(true).build()
    arrayList.add("--no-sandbox")
    arrayList.add("--disable-setuid-sandbox")
    val browser: Browser = Puppeteer.launch(options)
    val page: Page = browser.newPage()
//    page.goTo("https://www.baidu.com/?tn=98012088_10_dg&ch=3")
    page.goTo("D:\\nodejs\\ImageServer\\sort1.html")



    val deviceScaleFactor = 4.0

    //set page size
    page.setViewport(Viewport(500, 800, deviceScaleFactor, false, false, false))

    var height = 240.0
    height += 40 * 6
    height += 40
//    height *= deviceScaleFactor

    val screenshotOptions = ScreenshotOptions()
    //设置截图范围
    val clip = Clip(0.0, 0.0, 500.0, height)
    screenshotOptions.clip = clip
    //设置存放的路径
    screenshotOptions.path = "test1.png"


    val currentTimeMillis = System.currentTimeMillis()
    page.screenshot(screenshotOptions)
    println("截图用时${System.currentTimeMillis() - currentTimeMillis}")
    page.close();
    browser.close();
}