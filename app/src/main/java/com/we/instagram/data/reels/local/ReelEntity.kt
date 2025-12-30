package com.we.instagram.data.reels.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reels")
data class ReelEntity(
    @PrimaryKey val id: String,
    val userName: String,
    val userImage: String,
    val videoUrl: String,
    val likeCount: Int,
    val isLiked: Boolean,
    val pendingSync: Boolean = false // 🔥 for offline like/unlike
)