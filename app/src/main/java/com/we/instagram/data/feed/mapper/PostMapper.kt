package com.we.instagram.data.feed.mapper

import com.we.instagram.data.feed.local.PostEntity
import com.we.instagram.data.feed.remote.PostDto

fun PostDto.toEntity(): PostEntity {
    return PostEntity(
        postId = postId,
        userName = userName,
        userImage = userImage,
        postImage = postImage,
        likeCount = likeCount,
        likedByUser = likedByUser
    )
}