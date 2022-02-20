package com.example.closepr.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class Pull(
    val id: Long,
    val title: String,
    @Json(name = "created_at") val createdDate: Date,
    @Json(name = "closed_at") val closedDate: Date,
    val user: User

)
