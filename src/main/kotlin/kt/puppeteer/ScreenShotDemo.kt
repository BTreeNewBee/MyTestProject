package kt.puppeteer

import com.ruiyun.jvppeteer.core.Puppeteer
import com.ruiyun.jvppeteer.core.browser.Browser
import com.ruiyun.jvppeteer.core.browser.BrowserFetcher
import com.ruiyun.jvppeteer.core.page.Page
import com.ruiyun.jvppeteer.options.Clip
import com.ruiyun.jvppeteer.options.LaunchOptions
import com.ruiyun.jvppeteer.options.LaunchOptionsBuilder
import com.ruiyun.jvppeteer.options.ScreenshotOptions


fun main() {
    BrowserFetcher.downloadIfNotExist(null)
    val arrayList = ArrayList<String>()
    val options: LaunchOptions = LaunchOptionsBuilder().withArgs(arrayList).withHeadless(true).build()
    arrayList.add("--no-sandbox")
    arrayList.add("--disable-setuid-sandbox")
    val browser: Browser = Puppeteer.launch(options)
    val page: Page = browser.newPage()
//    page.goTo("https://www.baidu.com/?tn=98012088_10_dg&ch=3")
    page.goTo("D:\\nodejs\\ImageServer\\sort.html")
    

    val screenshotOptions = ScreenshotOptions()
    //设置截图范围
    //设置截图范围
    val clip = Clip(1.0, 1.56, 1024.0, 1024.0)
    screenshotOptions.clip = clip
    //设置存放的路径
    screenshotOptions.path = "test.png"


    val currentTimeMillis = System.currentTimeMillis()
    page.screenshot(screenshotOptions)
    println("截图用时${System.currentTimeMillis() - currentTimeMillis}")
    page.close();
    browser.close();
}