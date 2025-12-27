package com.we.instagram.data.model

data class Post(
    val id: String,
    val username: String,
    val imageUrl: String,
    val caption: String,
    val likes: Int,
    val isLiked: Boolean
)