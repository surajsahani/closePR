package com.example.closepr.models

import com.squareup.moshi.Json

data class User(
    val id: Long,
    @Json(name = "login") val userName: String,
    @Json(name = "avatar_url") val imageUrl: String
)
