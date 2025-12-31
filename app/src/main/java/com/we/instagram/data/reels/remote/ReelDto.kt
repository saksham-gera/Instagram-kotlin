package com.we.instagram.data.reels.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReelDto(

    @Json(name = "reel_id")
    val id: String,

    @Json(name = "user_name")
    val userName: String,

    @Json(name = "user_image")
    val userImage: String,

    @Json(name = "reel_video")
    val reelVideo: String,

    @Json(name = "like_count")
    val likeCount: Int,

    @Json(name = "liked_by_user")
    val likedByUser: Boolean
)