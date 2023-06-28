package kt.arknightSimulate


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Group(
    @SerialName("name")
    val name: String,
    @SerialName("opers")
    val opers: List<OperX>
)