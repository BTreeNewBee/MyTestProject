package com.iguigui.process.dto.neteasecloudmusic.search


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ar(
    @SerialName("alia")
    val alia: List<String>,
    @SerialName("alias")
    val alias: List<String>,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("tns")
    val tns: List<String>
)