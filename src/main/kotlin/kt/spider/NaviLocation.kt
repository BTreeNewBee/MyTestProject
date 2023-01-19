package kt.spider


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NaviLocation(
    @SerialName("lat")
    val lat: Double,
    @SerialName("lng")
    val lng: Double
)