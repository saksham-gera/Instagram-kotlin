package com.we.instagram.data.reels.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReelLikeRequest(
    val like: Boolean,
    @Json(name = "reels_id") val reelId: String
)