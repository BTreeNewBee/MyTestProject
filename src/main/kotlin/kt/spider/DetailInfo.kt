package kt.spider


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailInfo(
//    @SerialName("checkin_num")
//    val checkinNum: String,
//    @SerialName("children")
//    val children: List<Children>,
//    @SerialName("comment_num")
//    val commentNum: String,
//    @SerialName("detail_url")
//    val detailUrl: String,
    @SerialName("distance")
    val distance: Int,
    @SerialName("tag")
    val tag: String,
//    @SerialName("facility_rating")
//    val facilityRating: String,
//    @SerialName("favorite_num")
//    val favoriteNum: String,
//    @SerialName("hygiene_rating")
//    val hygieneRating: String,
//    @SerialName("image_num")
//    val imageNum: String,
//    @SerialName("navi_location")
//    val naviLocation: NaviLocation,
//    @SerialName("overall_rating")
//    val overallRating: String,
//    @SerialName("service_rating")
//    val serviceRating: String,
//    @SerialName("shop_hours")
//    val shopHours: String,
//    @SerialName("type")
//    val type: String
)