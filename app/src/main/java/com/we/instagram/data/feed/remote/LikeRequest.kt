package com.we.instagram.data.feed.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LikeRequest(
    val like: Boolean,
    @Json(name = "post_id") val postId: String
)