package kt.spider


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Children(
    @SerialName("address")
    val address: String,
    @SerialName("location")
    val location: LocationXX,
    @SerialName("name")
    val name: String,
    @SerialName("show_name")
    val showName: String,
    @SerialName("tag")
    val tag: String,
    @SerialName("uid")
    val uid: String
)