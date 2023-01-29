package kt.mongodbs

import cn.hutool.core.io.FileUtil
import com.mongodb.MongoException
import com.mongodb.client.MongoClients
import org.bson.Document
import java.nio.charset.Charset


@OptIn(ExperimentalStdlibApi::class)
fun main() {
    val uri = "mongodb://localhost:27017/?maxPoolSize=20&w=majority"
    MongoClients.create(uri).use { mongoClient ->
        val database = mongoClient.getDatabase("map")
        try {
            val collection = database.getCollection("community")
            FileUtil.readLines("D:\\中山市-火炬区-周边.csv", Charset.forName("UTF-8")).forEach {
                //parse json string to document
                val document = Document.parse(it)
                collection.insertOne(document)
            }
        } catch (me: MongoException) {
            System.err.println("An error occurred while attempting to run a command: $me")
        }
    }
}

@kotlinx.serialization.Serializable
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
    var score:Double = 0.0,
    var scores:MutableMap<String,Double> = mutableMapOf(),
)


@kotlinx.serialization.Serializable
data class AroundInfo(
    var info:MutableMap<String,List<MapDetail>>,
    var count:MutableMap<String,Int>,
)


@kotlinx.serialization.Serializable
data class MapDetail(
    val name: String,
    val distance: Int,
//    val detail: String,
    val type: String,
    val longitude: Double,
    val latitude: Double,
)