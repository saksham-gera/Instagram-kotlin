package com.we.instagram.data.feed.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey
    val postId: String,

    val userName: String,
    val userImage: String,
    val postImage: String,

    val likeCount: Int,
    val likedByUser: Boolean
)