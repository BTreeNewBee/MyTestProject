package kt.arknightSimulate


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OperX(
    @SerialName("name")
    val name: String,
    @SerialName("skill")
    val skill: Int = 0,
    @SerialName("skill_usage")
    val skillUsage: Int = 0
)