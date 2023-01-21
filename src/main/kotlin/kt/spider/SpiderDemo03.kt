package kt.spider

import cn.hutool.core.io.FileUtil
import cn.hutool.core.text.csv.CsvUtil
import cn.hutool.http.HttpUtil
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import io.blackmo18.kotlin.grass.dsl.grass
import kotlinx.serialization.json.*
import org.springframework.http.codec.json.Jackson2JsonEncoder
import java.io.File
import java.io.FileReader
import java.nio.charset.Charset

@OptIn(ExperimentalStdlibApi::class)
fun main() {

    val csvContents = csvReader().readAllWithHeader(FileUtil.file("D:\\中山市-火炬区1.csv"))
    val dataClasses = grass<Community>().harvest(csvContents)
    dataClasses.forEach { communit ->
        val aroundInfo = AroundInfo(mutableMapOf())
        searchList.forEach {
            val mapDetailList = getMapDetailList(it.type, it.radius, "${communit.latitude},${communit.longitude}")
            aroundInfo.info[it.type] = mapDetailList
        }
        communit.aroundInfo = aroundInfo
    }

    //use jackson encode list to array
    val objectMapper = ObjectMapper()

//    val writer = CsvUtil.getWriter(File("D:\\中山市-火炬区-周边.csv"), Charset.forName("UTF-8"))
    FileUtil.del("D:\\中山市-火炬区-周边.csv")
    val file1 = FileUtil.file("D:\\中山市-火炬区-周边.csv")
    file1.createNewFile()
    dataClasses.forEach {
        file1.appendText(objectMapper.writeValueAsString(it) + "\n")
    }




}


val json = Json {
    ignoreUnknownKeys = true
}

fun getMapDetailList(query: String, radius: Int, location: String): List<MapDetail> {
    val url = "https://api.map.baidu.com/place/v2/search"
    val map: MutableMap<String, Any> = mutableMapOf()
    map["radius_limit"] = "false"
    map["output"] = "json"
    map["scope"] = "2"
    map["filter"] = "sort_name:distance;sort_rule:1"
    map["page_size"] = "10"
    map["page_num"] = "0"
    map["ak"] = "52RquM9kvK6uHWqypFQUL3iHwsXAA9Nn"
    map["query"] = query
    map["location"] = location
    map["radius"] = radius

    val get = HttpUtil.get(url, map)
    println(get)
    val parseToJsonElement = Json.parseToJsonElement(get)
    val int = parseToJsonElement.jsonObject["status"]?.jsonPrimitive?.int
    if (int != 0) {
        println("请求失败")
        return emptyList()
    }
    val searchResult = json.decodeFromJsonElement(MapSearchResult.serializer(), parseToJsonElement)
    return searchResult.results.map {
//        val distance = getDistance(
//            it.location.lat,
//            it.location.lng,
//            location.split(",")[0].toDouble(),
//            location.split(",")[1].toDouble()
//        )
        MapDetail(it.name, it.detailInfo.distance, it.detailInfo.tag, it.location.lng, it.location.lat)
    }
}

//计算两个经纬度之间的距离
fun getDistance(lat1: Double, lng1: Double, lat2: Double, lng2: Double): Double {
    val radLat1 = lat1 * Math.PI / 180.0
    val radLat2 = lat2 * Math.PI / 180.0
    val a = radLat1 - radLat2
    val b = lng1 * Math.PI / 180.0 - lng2 * Math.PI / 180.0
    var s = 2 * Math.asin(
        Math.sqrt(
            Math.pow(
                Math.sin(a / 2),
                2.0
            ) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2.0)
        )
    )
    s *= 6378.137
    s = Math.round(s * 10000) / 10000.0
    return s
}


data class PoiType(val type: String, val radius: Int)


/**
 *
data class AroundInfo(
val bus: List<MapDetail>,
val metro: List<MapDetail>,
val school: List<MapDetail>,
val hospital: List<MapDetail>,
val bank: List<MapDetail>,
val market: List<MapDetail>,
val restaurant: List<MapDetail>,
val park: List<MapDetail>
)
 */

val searchList = listOf(
//    PoiType("美食",1000),
//    PoiType("购物",2000),
//    PoiType("生活服务",1000),
//    PoiType("旅游景点",1000),
//    PoiType("幼儿园",1000),
//    PoiType("小学",2000),
//    PoiType("中学",3000),
//    PoiType("医疗",5000),
//    PoiType("火车站",10000),
//    PoiType("地铁站",2000),
    PoiType("公交车站", 1000),
)