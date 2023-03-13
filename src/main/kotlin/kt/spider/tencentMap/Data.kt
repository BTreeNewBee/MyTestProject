package kt.spider.tencentMap


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("ad_info")
    val adInfo: AdInfo,
    @SerialName("address")
    val address: String,
    @SerialName("category")
    val category: String,
    @SerialName("_distance")
    val distance: Double,
    @SerialName("id")
    val id: String,
    @SerialName("location")
    val location: Location,
    @SerialName("tel")
    val tel: String,
    @SerialName("title")
    val title: String,
    @SerialName("type")
    val type: Int
)