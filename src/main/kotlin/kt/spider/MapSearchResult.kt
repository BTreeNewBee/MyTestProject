package kt.spider


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MapSearchResult(
    @SerialName("message")
    val message: String,
    @SerialName("result_type")
    val resultType: String,
    @SerialName("results")
    val results: List<ResultX>,
    @SerialName("status")
    val status: Int,
    @SerialName("total")
    val total: Int
)