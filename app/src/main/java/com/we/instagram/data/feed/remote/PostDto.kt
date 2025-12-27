package com.we.instagram.data.feed.remote
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostDto(
    val id: String,
    val username: String,
    val imageUrl: String,
    val caption: String,
    val likes: Int
)