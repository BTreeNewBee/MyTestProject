package kt.arknightSimulate


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Chars(
    @SerialName("elite")
    val elite: Int,
    @SerialName("id")
    val id: String,
    @SerialName("level")
    val level: Int,
    @SerialName("name")
    val name: String,
    @SerialName("own")
    val own: Boolean,
    @SerialName("potential")
    val potential: Int,
    @SerialName("rarity")
    val rarity: Int
)