package com.iguigui.process.dto.neteasecloudmusic.songsDetail


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class L(
    @SerialName("br")
    val br: Int,
    @SerialName("fid")
    val fid: Int,
    @SerialName("size")
    val size: Int,
    @SerialName("vd")
    val vd: Int
)