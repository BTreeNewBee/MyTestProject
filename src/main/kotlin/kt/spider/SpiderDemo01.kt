package kt.spider

import cn.hutool.core.text.csv.CsvUtil
import kotlinx.serialization.SerialName
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File
import java.nio.charset.Charset

val urlStr = "https://zs.loupan.com/community/huoju/"
val city = "中山市"


var county = ""

fun main() {
//    val urlStr = "https://zs.loupan.com/community/huoju/"
    println("正在爬分页: $urlStr")
    val doc: Document = Jsoup.connect(urlStr).get()
    val url = doc.connection().request().url()
    var pagenxtSelect = doc.select("a[href].pagenxt")
    val select = doc.select("a.cur").first()
    county = select?.text().toString()
    val communities = mutableListOf<Community>()
    getCommunities(doc, communities)

    while (pagenxtSelect.isNotEmpty()) {
        val pagenxt = pagenxtSelect.attr("href")
        println("正在爬分页: ${url.protocol}://${url.host}$pagenxt")
        val doc2: Document = Jsoup.connect("${url.protocol}://${url.host}$pagenxt").get()
        pagenxtSelect = doc2.select("a[href].pagenxt")
        getCommunities(doc2, communities)
    }
    getCommunitiesDetail(communities)
    CsvUtil.getWriter(File("D:\\$city-$county.csv"), Charset.forName("UTF-8")).writeBeans(communities)

}


fun getCommunities(doc: Document, communities: MutableList<Community>) {
    val list = doc.select("ul.list")[0]
    list.select("li").forEach {
        val select = it.select("div")
        val name = select.select("h2").text().replace(",", "").replace(" ", "")
        val price = select.select("div.price").text()
        val div_d = select.select("div.text").select("div.d")
        val addressDiv = div_d[1]
        var address = addressDiv.select("span").text()
        address = formateAddress(address, name)
        val href = addressDiv.select("a").attr("href")
        val m_bottom_10 = select.select("div.text").select("div.tags m_bottom_10")
        var type = "住宅"
        if (m_bottom_10.isNotEmpty()) {
            type = m_bottom_10.text()
        }
//        println("name:$name,address:$address,href:$href,type:$type,price:$price")
        val community = Community(city, county, name, address, href, type, price)
//        println(community)
        communities.add(community)
    }
}

fun formateAddress(address: String, name: String): String {
    val indexOf = address.indexOf("]")
    var address_new = address
        .substring(indexOf + 1)
        .replace(city, "")
        .replace(county, "")
        .replace("-", "")
        .replace("[", "")
        .replace("]", "")
        .replace(" ", "")
        .replace(".", "")

    address_new = city + county + address_new
    if (address_new.indexOf("(") != -1) {
        address_new = address_new.substring(0, address_new.indexOf("(")).replace(" ", "")
    }
    if (address_new.indexOf("（") != -1) {
        address_new = address_new.substring(0, address_new.indexOf("（")).replace(" ", "")
    }
    if (address_new.indexOf("，") != -1) {
        address_new = address_new.substring(0, address_new.indexOf("，")).replace(" ", "")
    }
    if (address_new.indexOf(",") != -1) {
        address_new = address_new.substring(0, address_new.indexOf(",")).replace(" ", "")
    }

    return address_new + name
}


data class Community(
    val city: String,
    val county: String,
    val name: String,
    val address: String,
    val href: String,
    val type: String,
    val price: String,

    var aroundInfo: AroundInfo? = null,

    var longitude: Double = 0.0,
    var latitude: Double = 0.0,
    var comprehension: Int = 0,
    var confidence: Int = 0,
    var level: String = "",
    var precise: Int = 0,

    var propertyFee: String = "",
    var constructionArea: String = "",
    var households: String = "",
    var buildDate: String = "",
    var parking: String = "",
    var plotRatio: String = "",
    var greeningRate: String = "",

)



data class AroundInfo(
    val info:Map<String,List<MapDetail>>,
)

data class MapDetail(
    val name: String,
    val distance: Double,
//    val detail: String,
    val type: String,
    val longitude: Double,
    val latitude: Double,
)