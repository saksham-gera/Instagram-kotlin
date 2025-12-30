package com.we.instagram.data.reels.remote

import com.squareup.moshi.Json

data class ReelsResponse(
    @Json(name = "reels")
    val reels: List<ReelDto>
)