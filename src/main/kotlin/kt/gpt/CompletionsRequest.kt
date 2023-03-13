package kt.gpt


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompletionsRequest(
    @SerialName("max_tokens")
    val maxTokens: Int,
    @SerialName("model")
    val model: String,
    @SerialName("prompt")
    val prompt: String,
    @SerialName("temperature")
    val temperature: Int
)