package kt.spider

import cn.hutool.core.io.FileUtil
import cn.hutool.core.text.csv.CsvUtil
import cn.hutool.http.HttpUtil
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import io.blackmo18.kotlin.grass.dsl.grass
import java.io.File
import java.io.FileReader
import java.nio.charset.Charset

@OptIn(ExperimentalStdlibApi::class)
fun main() {
    val csvContents = csvReader().readAllWithHeader(FileUtil.file("D:\\中山市-火炬区.csv"))
    val dataClasses = grass<Community>().harvest(csvContents)
    dataClasses.forEach { communit ->
        searchList.forEach {
            getMapDetailList(it.type,it.radius,"${communit.latitude},${communit.longitude}")
        }
    }
}


fun getMapDetailList(query:String,radius: Int,location:String) {
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

    println(HttpUtil.get(url, map))

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
    PoiType("火车站",1000),
//    PoiType("地铁站",2000),
//    PoiType("公交车站",1000),
)