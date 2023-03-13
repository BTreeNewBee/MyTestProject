package kt.spider.tencentMap


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Region(
    @SerialName("title")
    val title: String
)