package com.iguigui.process.dto.neteasecloudmusic.songsDetail


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//歌手信息
@Serializable
data class Ar(
    @SerialName("alias")
    val alias: List<String>,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("tns")
    val tns: List<String>
)