package kt.freemarker

data class MessageRank(
    val year:Int,
    val date:String,
    val dayLeft:Int,
    val rate:Double,
    val color:String,
    val messageCount:Int,
    val rankInfos:List<RankInfo>
)



data class RankInfo(
    val rank:Int,
    val avatar:String,
    val nickName:String,
    val messageCount:Int,
)
