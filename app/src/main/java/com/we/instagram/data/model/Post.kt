package com.we.instagram.data.model

data class Post(
    val postId: String,
    val userName: String,
    val userImage: String,
    val postImage: String,
    val likeCount: Int,
    val likedByUser: Boolean
)