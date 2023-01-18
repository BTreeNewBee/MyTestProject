package kt.spider

import cn.hutool.core.text.csv.CsvUtil
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File
import java.nio.charset.Charset


fun main() {

    getInfo("https://zs.loupan.com/community/8681667.html")

}

fun getCommunitiesDetail(communities: MutableList<Community>) {

    communities.forEach {
        val doc: Document = Jsoup.connect(it.href).get()
        val select = doc.select("div.infoBox").select("ul").select("li")
        val propertyFee = select[1].select("span.text_nr").text()
        val constructionArea = select[2].select("span.text_nr").text()
        val households = select[3].select("span.text_nr").text()
        val  buildDate = select[4].select("span.text_nr").text()
        val parking = select[5].select("span.text_nr").text()
        val plotRatio = select[6].select("span.text_nr").text()
        val greeningRate = select[7].select("span.text_nr").text()
        DetailInfo(propertyFee, constructionArea, households, buildDate, parking, plotRatio, greeningRate).apply {
            it.detailInfo = this
        }
    }

}


fun getInfo(url:String) {

    val doc: Document = Jsoup.connect(url).get()
    val select = doc.select("div.infoBox").select("ul").select("li")
    println(select)
    println(select[1].select("span.text_nr").text())
    println(select[2].select("span.text_nr").text())
    println(select[3].select("span.text_nr").text())
    println(select[4].select("span.text_nr").text())
    println(select[5].select("span.text_nr").text())
    println(select[6].select("span.text_nr").text())
    println(select[7].select("span.text_nr").text())


}
