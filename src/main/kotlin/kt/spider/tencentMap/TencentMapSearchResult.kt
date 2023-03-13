package kt.spider.tencentMap


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TencentMapSearchResult(
    @SerialName("count")
    val count: Int,
    @SerialName("data")
    val `data`: List<Data>,
    @SerialName("message")
    val message: String,
    @SerialName("region")
    val region: Region,
    @SerialName("request_id")
    val requestId: String,
    @SerialName("status")
    val status: Int
)