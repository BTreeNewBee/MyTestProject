package kt.spider


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName("comprehension")
    val comprehension: Int,
    @SerialName("confidence")
    val confidence: Int,
    @SerialName("level")
    val level: String,
    @SerialName("location")
    val location: Location,
    @SerialName("precise")
    val precise: Int
)