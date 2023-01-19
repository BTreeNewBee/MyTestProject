package kt.spider

import cn.hutool.core.text.csv.CsvUtil
import cn.hutool.http.HttpUtil
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.apache.poi.xssf.model.MapInfo
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File
import java.nio.charset.Charset


fun main() {

    val s = "{\"status\":0,\"result\":{\"location\":{\"lng\":113.49474012297535,\"lat\":22.539487844686876},\"precise\":1,\"confidence\":70,\"comprehension\":100,\"level\":\"地产小区\"}}"
    val json = Json.parseToJsonElement(s)
    val status = json.jsonObject["status"]?.jsonPrimitive?.int
    if (status != 0) {
        return
    }
    val geocoding = Json.decodeFromJsonElement(Geocoding.serializer(), json)

}

fun getCommunitiesDetail(communities: MutableList<Community>) {

    communities.forEach {
        println(it)
        val doc: Document = Jsoup.connect(it.href).get()
        val select = doc.select("div.infoBox").select("ul").select("li")
        val propertyFee = select[1].select("span.text_nr").text().replace("元","")
        val constructionArea = select[2].select("span.text_nr").text().replace("㎡","")
        val households = select[3].select("span.text_nr").text().replace("户","")
        val buildDate = select[4].select("span.text_nr").text()
        val parking = select[5].select("span.text_nr").text()
        val plotRatio = select[6].select("span.text_nr").text()
        val greeningRate = select[7].select("span.text_nr").text()

        val url = "https://api.map.baidu.com/geocoding/v3/"
        val map: MutableMap<String, Any> = mutableMapOf()
        map["address"] = it.address
        map["output"] = "json"
        map["ak"] = "52RquM9kvK6uHWqypFQUL3iHwsXAA9Nn"
        val result = HttpUtil.get(url, map)

        val json = Json.parseToJsonElement(result)
        val status = json.jsonObject["status"]?.jsonPrimitive?.int
        if (status != 0) {
            return
        }
        val geocoding = Json.decodeFromJsonElement(Geocoding.serializer(), json)
        geocoding.result.apply {
            it.longitude = location.lng
            it.latitude = location.lat
            it.comprehension = comprehension
            it.confidence = confidence
            it.level = level
            it.precise = precise

            it.propertyFee = propertyFee
            it.constructionArea = constructionArea
            it.households = households
            it.buildDate = buildDate
            it.parking = parking
            it.plotRatio = plotRatio
            it.greeningRate = greeningRate
        }
    }

}


fun getInfo(url: String) {

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
