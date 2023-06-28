package kt.arknightSimulate


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MaaCopilotFile(
//    @SerialName("actions")
//    val actions: List<Action>,
//    @SerialName("difficulty")
//    val difficulty: Int?,
    @SerialName("doc")
    val doc: Doc,
    @SerialName("groups")
    val groups: List<Group>? = null,
    @SerialName("minimum_required")
    val minimumRequired: String,
    @SerialName("opers")
    val opers: List<OperX>,
    @SerialName("stage_name")
    val stageName: String
)