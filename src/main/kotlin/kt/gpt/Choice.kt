package kt.gpt


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Choice(
    @SerialName("finish_reason")
    val finishReason: String,
    @SerialName("index")
    val index: Int,
    @SerialName("logprobs")
    val logprobs: String?,
    @SerialName("text")
    val text: String
)