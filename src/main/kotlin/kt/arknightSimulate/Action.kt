package kt.arknightSimulate


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Action(
    @SerialName("direction")
    val direction: String? = null,
    @SerialName("location")
    val location: List<Int>? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("type")
    val type: String
)