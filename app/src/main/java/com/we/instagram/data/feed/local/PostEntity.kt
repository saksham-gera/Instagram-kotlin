package com.we.instagram.data.feed.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey val id: String,
    val username: String,
    val imageUrl: String,
    val caption: String,
    val likes: Int,
    val isLiked: Boolean
)