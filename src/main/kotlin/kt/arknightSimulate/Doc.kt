package kt.arknightSimulate


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Doc(
    @SerialName("details")
    val details: String,
    @SerialName("title")
    val title: String
)