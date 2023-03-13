package kt.spider.tencentMap


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdInfo(
    @SerialName("adcode")
    val adcode: Int,
    @SerialName("city")
    val city: String,
    @SerialName("district")
    val district: String,
    @SerialName("province")
    val province: String
)