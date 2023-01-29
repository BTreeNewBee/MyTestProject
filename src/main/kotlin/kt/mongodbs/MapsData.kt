package kt.mongodbs

import cn.hutool.core.io.FileUtil
import kotlinx.serialization.json.Json
import kt.spider.PoiType
import java.nio.charset.Charset

val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
    encodeDefaults = true
    allowStructuredMapKeys = true
    prettyPrint = true
    useArrayPolymorphism = true
    classDiscriminator = "type"
}


fun main() {
    val associateBy = searchList.associateBy { it.type }

    val readLines = FileUtil.readLines("D:\\中山市-火炬区-周边.csv", Charset.forName("UTF-8"))
    val map = readLines.map {
        //parse json string to Community
        val community = json.decodeFromString(Community.serializer(), it)
        community.aroundInfo?.let {
            it.count.forEach { count ->
                val poiType = associateBy[count.key]
                poiType?.let {
                    var score = count.value * poiType.coefficient
                    if (score > poiType.maxScore) {
                        score = poiType.maxScore
                    }
                    community.score += score
                    community.scores[count.key] = score
                }
            }
        }
        community
    }
    map.sortedByDescending { it.score }.forEach {
        println("${it.name} ${it.score} ${it.scores}")
    }
}


val searchList = listOf(
    PoiType("美食", 1000, 0.5, 10.0),
    PoiType("购物", 2000, 0.1, 10.0),
    PoiType("生活服务", 1000, 0.5, 10.0),
    PoiType("旅游景点", 1000, 0.5, 10.0),
    PoiType("幼儿园", 1000, 1.0, 10.0),
    PoiType("小学", 2000, 2.0, 10.0),
    PoiType("中学", 3000, 3.0, 10.0),
    PoiType("医疗", 5000, 0.01, 10.0),
    PoiType("地铁站", 2000, 5.0, 10.0),
    PoiType("公交车站", 1000, 1.0, 10.0),
)