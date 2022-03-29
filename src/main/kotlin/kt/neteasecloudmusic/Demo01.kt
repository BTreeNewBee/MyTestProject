package kt.neteasecloudmusic

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import top.yumbo.util.music.MusicEnum
import top.yumbo.util.music.musicImpl.netease.NeteaseCloudMusicInfo

fun main() {
    MusicEnum.setBASE_URL_163Music("http://192.168.50.185:3000")
    val neteaseCloudMusicInfo = NeteaseCloudMusicInfo()
//
//    val jsonObject = JSONObject()
//    jsonObject["phone"] = "17322352560"
//    jsonObject["password"] = "1479712atm"
//    val login = neteaseCloudMusicInfo.loginCellphone(jsonObject);

    val loginStatus = neteaseCloudMusicInfo.loginStatus()
    println(loginStatus)

//    val json = JSONObject()
//    json["uid"] = "59690957"
//    val userPlaylist = neteaseCloudMusicInfo.userPlaylist(json) // 发送/user/playlist请求
//    println(userPlaylist) // 将返回得到的json数据打印一下

    val json = JSONObject()
    json["id"] = "7008518343"
    val playlistDetail = neteaseCloudMusicInfo.playlistDetail(json)
    println(playlistDetail)

}