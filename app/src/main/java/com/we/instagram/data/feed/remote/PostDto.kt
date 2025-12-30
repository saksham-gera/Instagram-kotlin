package com.we.instagram.data.feed.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostDto(

    @Json(name = "post_id")
    val postId: String,

    @Json(name = "user_name")
    val userName: String,

    @Json(name = "user_image")
    val userImage: String,

    @Json(name = "post_image")
    val postImage: String,

    @Json(name = "like_count")
    val likeCount: Int,

    @Json(name = "liked_by_user")
    val likedByUser: Boolean
)