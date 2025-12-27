package com.we.instagram.data.feed.mapper

import com.we.instagram.data.feed.local.PostEntity
import com.we.instagram.data.feed.remote.PostDto

fun PostDto.toEntity(): PostEntity {
    return PostEntity(
        id = id,
        username = username,
        imageUrl = imageUrl,
        caption = caption,
        likes = likes,
        isLiked = false
    )
}