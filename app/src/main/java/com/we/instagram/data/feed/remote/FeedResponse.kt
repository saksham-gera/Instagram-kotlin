package com.we.instagram.data.feed.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeedResponse(
    @Json(name = "feed")
    val feed: List<PostDto>
)