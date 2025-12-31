package com.we.instagram.data.reels.mapper

import com.we.instagram.data.reels.local.ReelEntity
import com.we.instagram.data.reels.remote.ReelDto

fun ReelDto.toEntity() = ReelEntity(
    id = id,
    userName = userName,
    userImage = userImage,
    videoUrl = reelVideo,
    likeCount = likeCount,
    isLiked = likedByUser
)