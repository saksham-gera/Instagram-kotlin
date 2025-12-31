package com.we.instagram.data.feed.remote

import retrofit2.http.*

interface FeedApi {

    @GET("feed")
    suspend fun getPosts(): FeedResponse

    @POST("like")
    suspend fun likePost(
        @Body body: LikeRequest
    )

    @DELETE("dislike")
    suspend fun dislikePost(
        @Query("post_id") postId: String
    )
}