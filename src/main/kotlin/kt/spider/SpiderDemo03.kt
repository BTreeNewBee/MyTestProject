package kt.spider

import cn.hutool.core.io.FileUtil
import cn.hutool.http.HttpUtil
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import com.google.common.util.concurrent.RateLimiter
import io.blackmo18.kotlin.grass.dsl.grass
import kotlinx.serialization.json.*
import kt.spider.tencentMap.TencentMapSearchResult
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

fun main() {

    getMapDetailListTencent("美食", 1000, "39.915,116.404")
}

val akList = FileUtil.readLines("D:/maps/BaiduMapKeys.txt", Charset.defaultCharset())

val tencentKeyList = FileUtil.readLines("D:/maps/TencentMapKeys.txt", Charset.defaultCharset())


fun getTencentKey(): () -> String {
    var index = 0
    //guvav 令牌桶
    val rateLimiter: RateLimiter = RateLimiter.create((tencentKeyList.size * 4.5).toDouble(), 1, TimeUnit.SECONDS)
    return fun(): String {
        rateLimiter.acquire()
        index++
        return tencentKeyList[index % tencentKeyList.size]
    }
}


class TencentKey {
    var index = 0
    operator fun invoke(): String {
        index++
        return tencentKeyList[index % tencentKeyList.size]
    }
}


@OptIn(ExperimentalStdlibApi::class)
fun getGeoDetail(fileName: String) {

    val csvContents = csvReader().readAllWithHeader(FileUtil.file("D:\\$fileName.csv"))
    val dataClasses = grass<Community>().harvest(csvContents)
    var i = 0
    dataClasses.parallelStream().forEach { communit ->
        val aroundInfo = AroundInfo(mutableMapOf(), mutableMapOf())
        searchList.parallelStream().forEach {
            val pair = getMapDetailListTencent(it.type, it.radius, "${communit.latitude},${communit.longitude}")
            aroundInfo.info[it.type] = pair.first
            aroundInfo.count[it.type] = pair.second
        }
        communit.aroundInfo = aroundInfo
        i++
        println("当前处理第:${i}个,进度:${String.format("%.2f",i.toDouble() / dataClasses.size.toDouble() * 100)}%")
    }

    //use jackson encode list to array
    val objectMapper = ObjectMapper()

//    val writer = CsvUtil.getWriter(File("D:\\中山市-火炬区-周边.csv"), Charset.forName("UTF-8"))
    FileUtil.del("D:\\$fileName-周边.csv")
    val file1 = FileUtil.file("D:\\$fileName-周边.csv")
    file1.createNewFile()
    dataClasses.forEach {
        file1.appendText(objectMapper.writeValueAsString(it) + "\n")
    }
}

val json = Json {
    ignoreUnknownKeys = true
}

fun getMapDetailListBaidu(query: String, radius: Int, location: String): Pair<List<MapDetail>, Int> {
    val url = "https://api.map.baidu.com/place/v2/search"
    val map: MutableMap<String, Any> = mutableMapOf()
    map["radius_limit"] = "false"
    map["output"] = "json"
    map["scope"] = "2"
    map["filter"] = "sort_name:distance;sort_rule:1"
    map["page_size"] = "20"
    map["page_num"] = "0"
    map["ak"] = ""
    map["query"] = query
    map["location"] = location
    map["radius"] = radius

    val get = HttpUtil.get(url, map)
    println(get)
    val parseToJsonElement = Json.parseToJsonElement(get)
    val int = parseToJsonElement.jsonObject["status"]?.jsonPrimitive?.int
    if (int == 4) {
        println("ak过期")
        return Pair(emptyList(), 0)
    }
    if (int != 0) {
        println("请求失败")
        return Pair(emptyList(), 0)
    }
    val searchResult = json.decodeFromJsonElement(MapSearchResult.serializer(), parseToJsonElement)
    return Pair(searchResult.results.map {
        MapDetail(it.uid, it.name, it.detailInfo.distance, it.detailInfo.tag, it.location.lng, it.location.lat)
    }, searchResult.total)
}

val getKey = getTencentKey()
val getKey1 = TencentKey()

fun getMapDetailListTencent(query: String, radius: Int, location: String): Pair<List<MapDetail>, Int> {
    val url = "https://apis.map.qq.com/ws/place/v1/search"
    val key: String = getKey()
    val map: MutableMap<String, Any> = mutableMapOf()
    map["key"] = key
    map["keyword"] = query
    map["boundary"] = "nearby($location,$radius,0)"
    map["orderby"] = "_distance"
    map["page_size"] = "20"
    map["page_index"] = "1"
    map["filter"] = "category=$query"

    val get = HttpUtil.get(url, map)
//    println(get)
    val parseToJsonElement = Json.parseToJsonElement(get)
    val int = parseToJsonElement.jsonObject["status"]?.jsonPrimitive?.int
    if (int == 4) {
        println("ak过期")
        return Pair(emptyList(), 0)
    }
    if (int != 0) {
        println("请求失败")
        println(get)
        return Pair(emptyList(), 0)
    }
    val searchResult = json.decodeFromJsonElement(TencentMapSearchResult.serializer(), parseToJsonElement)
    return Pair(searchResult.data.map {
        MapDetail(it.id, it.title, (it.distance * 1000).toInt(), it.category, it.location.lng, it.location.lat)
    }, searchResult.count)
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


data class PoiType(
    val type: String,
    val radius: Int,
    val coefficient: Double,
    val maxScore: Double,
)


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
    PoiType("美食", 1000, 0.5, 10.0),
    PoiType("购物", 2000, 0.5, 10.0),
    PoiType("生活服务", 1000, 0.5, 10.0),
    PoiType("幼儿园", 1000, 1.0, 10.0),
    PoiType("小学", 2000, 2.0, 10.0),
    PoiType("中学", 3000, 3.0, 10.0),
    PoiType("地铁站", 2000, 5.0, 10.0),
    PoiType("公交车站", 1000, 1.0, 10.0),
)