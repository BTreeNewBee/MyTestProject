package kt.spider

import cn.hutool.core.io.FileUtil
import cn.hutool.core.text.csv.CsvUtil
import cn.hutool.http.HttpUtil
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import io.blackmo18.kotlin.grass.dsl.grass
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.io.File
import java.io.FileReader
import java.nio.charset.Charset

@OptIn(ExperimentalStdlibApi::class)
fun main() {
//    val csvContents = csvReader().readAllWithHeader(FileUtil.file("D:\\中山市-火炬区.csv"))
//    val dataClasses = grass<Community>().harvest(csvContents)
//    dataClasses.forEach { communit ->
//        searchList.forEach {
//            getMapDetailList(it.type,it.radius,"${communit.latitude},${communit.longitude}")
//        }
//    }

    getMapDetailList("科兴科学园", 500, "22.5301,113.938")

    val lng1 = 113.8967227936
    val lat1 = 22.5610342910
    val lng2 = 113.9388656616
    val lat2 = 22.5511657963
    val distance = getDistance(lng1, lat1, lng2, lat2)
    println(distance)

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
    val searchResult = Json.decodeFromJsonElement(MapSearchResult.serializer(), parseToJsonElement)

    searchResult.results.forEach {
        val distance = getDistance(
            it.location.lat,
            it.location.lng,
            location.split(",")[0].toDouble(),
            location.split(",")[1].toDouble()
        )
        MapDetail(it.name, distance, it.detailInfo.tag, it.detailInfo.naviLocation.lng, it.detailInfo.naviLocation.lat)
    }

    return emptyList()
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