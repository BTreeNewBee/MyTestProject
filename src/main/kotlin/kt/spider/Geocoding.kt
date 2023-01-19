package kt.spider


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Geocoding(
    @SerialName("result")
    val result: Result,
    @SerialName("status")
    val status: Int
)