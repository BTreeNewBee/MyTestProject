package kt.json

import cn.hutool.core.io.FileUtil
import cn.hutool.http.HttpUtil
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject

fun main() {
    val readBytes = FileUtil.readBytes("C:\\Users\\atmzx\\Desktop\\mylist4.txt")
    val string = String(readBytes)
//    val string = HttpUtil.get("https://music.163.com/api/playlist/detail?id=7008518343")
//    val string = HttpUtil.get("https://music.163.com/api/playlist/detail?id=546125074")
    val jsonObject = JSON.parseObject(string).getJSONObject("result")
    val artists = ArrayList<JSONObject>()
    val ja = jsonObject.getJSONArray("tracks")
    val jsonArray = ja.toJavaList(JSONObject::class.java)
    println("歌单作者:${jsonObject.getJSONObject("creator").getString("nickname")},歌单ID:${jsonObject.getString("id")},歌单名称:${jsonObject.getString("name")},曲目数量:${jsonArray.size}")
    jsonArray.map { it.getJSONArray("artists").toJavaList(JSONObject::class.java) }.forEach { it.forEach { artists.add(it) } }
    println("最爱艺人前10")
    artists.groupingBy { it.getString("name") }.eachCount().toList().sortedBy { it.second }.reversed().subList(0,10).forEach { println("${it.first }:${it.second}首,占歌单比例${String.format("%.2f",it.second * 100.0 / jsonArray.size)}%") }
//    jsonArray.forEach { println(it.getString("name") + " " + it.getJSONObject("album").getString("size")) }
    println("最爱专辑前10")
    data class Album(var name:String,var count:Int,var likeCount:Int,var likeRate:Double)

    jsonArray.map {  }
    val groupingBy = jsonArray.groupBy {
        it.getJSONObject("album")
            .getJSONArray("artists")
            .toJavaList(JSONObject::class.java)
            .map { it.getString("name") }
            .joinToString() + ":" +
                it.getJSONObject("album")
                    .getString("name")
    }

    groupingBy.map { (k, v) -> Album(k, v.get(0).getJSONObject("album").getIntValue("size"), v.size, v.size * 1.0 / v.get(0).getJSONObject("album").getIntValue("size")) }
        .sortedBy { it.likeCount }
        .reversed()
        .subList(0,30)
        .sortedBy { it.likeRate }
        .reversed()
        .subList(0,10)
        .forEach { println("专辑名称:${it.name},专辑曲目数量:${it.count},红心数量:${it.likeCount},占比例:${String.format("%.2f",it.likeRate * 100.0)}%") }



}