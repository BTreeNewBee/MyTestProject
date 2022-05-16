package com.iguigui.process.dto.neteasecloudmusic

import com.alibaba.fastjson.JSONObject
import top.yumbo.util.music.MusicEnum
import top.yumbo.util.music.musicImpl.netease.NeteaseCloudMusicInfo

fun main() {
    MusicEnum.setBASE_URL_163Music("http://192.168.50.185:3000")
    val neteaseCloudMusicInfo = NeteaseCloudMusicInfo()
//
//    val login = JSONObject()
//    login["phone"] = "17322352560"
//    login["phone"] = "7008518343"
//    neteaseCloudMusicInfo.loginCellphone(login)
    //歌单获取
//    val json = JSONObject()
//    json["id"] = "7008518343"
//    println(neteaseCloudMusicInfo.playlistDetail(json))
//    val playlistDetail = neteaseCloudMusicInfo.playlistDetail(json).toJavaObject(PlaylistDetail::class.java)

//    获取歌曲详情傻逼网易云居然要一首一首的获取
    val json = JSONObject()
    json["ids"] = "601391"
    val songDetail = neteaseCloudMusicInfo.songDetail(json)
//    val toJavaObject = songDetail.toJavaObject(SongDetail::class.java)
    println(songDetail)

//    json["id"] = "601391"
//    val songUrl = neteaseCloudMusicInfo.songUrl(json)
//    println(songUrl)


    //歌曲搜索
//    val json = JSONObject()
//    json["keywords"] = "中森明菜"
//    println(neteaseCloudMusicInfo.cloudsearch(json))


}


suspend fun getTrackInfo() {

}