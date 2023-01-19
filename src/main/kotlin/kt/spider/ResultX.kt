package kt.spider


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultX(
    @SerialName("address")
    val address: String,
    @SerialName("area")
    val area: String,
    @SerialName("city")
    val city: String,
    @SerialName("detail")
    val detail: Int,
    @SerialName("detail_info")
    val detailInfo: DetailInfo,
    @SerialName("location")
    val location: LocationXX,
    @SerialName("name")
    val name: String,
    @SerialName("province")
    val province: String,
    @SerialName("street_id")
    val streetId: String,
    @SerialName("telephone")
    val telephone: String,
    @SerialName("uid")
    val uid: String
)