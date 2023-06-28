package kt.puppeteer

import com.ruiyun.jvppeteer.core.Puppeteer
import com.ruiyun.jvppeteer.core.browser.Browser
import com.ruiyun.jvppeteer.core.browser.BrowserFetcher
import com.ruiyun.jvppeteer.core.page.Page
import com.ruiyun.jvppeteer.core.page.Request
import com.ruiyun.jvppeteer.core.page.Response
import com.ruiyun.jvppeteer.options.*
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    while (true) {
        Scanner(System.`in`).next()
        test()
    }
}


val browser: Browser by lazy {
    BrowserFetcher.downloadIfNotExist(null)
    val arrayList = ArrayList<String>()
    val options: LaunchOptions = LaunchOptionsBuilder().withArgs(arrayList).withHeadless(true).build()
    arrayList.add("--no-sandbox")
    arrayList.add("--disable-setuid-sandbox")
    arrayList.add("--proxy-server=http://192.168.50.185:7890")
    arrayList.add("--data-path=C:\\Puppeteer\\cache\\")
    arrayList.add("--disk-cache-dir=C:\\Puppeteer\\cache\\")
    Puppeteer.launch(options)
}


val page: Page by lazy {
    browser.newPage()
}

fun test() {


    println(LocalDateTime.now())

    //'image',
    //    'media',
    //    'font',
    //    'texttrack',
    //    'object',
    //    'beacon',
    //    'csp_report',
    //    'imageset',
    page.setCacheEnabled(true)
    page.setRequestInterception(true)
    page.onRequest { request: Request ->
        try {
            val url = request.url()
            when (request.resourceType()) {
                "image" -> {
                    request.abort()
                }
                "media" -> {
                    request.abort()
                }
                "font" -> {
                    request.abort()
                }
                "texttrack" -> {
                    request.abort()
                }
                "object" -> {
                    request.abort()
                }
                "beacon" -> {
                    request.abort()
                }
                "csp_report" -> {
                    request.abort()
                }
                "imageset" -> {
                    request.abort()
                }
                "xhr" -> {
                    request.continueRequest()
                }
                else -> {
                    println("resourceType " + request.resourceType())
                    println("use cache " + request.fromMemoryCache())
                    request.continueRequest()
                }
            }
            println(LocalDateTime.now().toString() + " " + url)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    page.onResponse { response: Response ->
        try {
            val url = response.url()
            println(LocalDateTime.now().toString() + " " + response.status() + " " + url )
            if (url == "https://api.mercari.jp/v2/entities:search" && response.text().isNotEmpty()) {
                val text = response.text()
                println(LocalDateTime.now().toString()  + " " + text)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    page.goTo("https://jp.mercari.com/search?keyword=本田美奈子&sort=created_time&order=desc&status=on_sale&price_min=300&price_max=10000")
    //set page size
    page.setViewport(Viewport(1920, 1080, 1.0, false, false, false))
//    page.close()

    println("finish")

}