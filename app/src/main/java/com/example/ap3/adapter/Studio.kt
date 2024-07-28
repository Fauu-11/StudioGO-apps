package com.example.ap3.adapter

data class Studio(
    val name: String = "",
    val picUrl: String = "",
    val description: String = "",
    val address: String = "",
    val price: String = "",
    val rate: Float = 0f,
    val facilities: List<String> = listOf(),
    val type: String = "" // "new" or "hot"
)
