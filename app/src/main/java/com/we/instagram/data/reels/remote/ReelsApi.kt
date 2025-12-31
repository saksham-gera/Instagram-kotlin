package com.we.instagram.data.reels.remote

import retrofit2.http.*

interface ReelsApi {

    @GET("reels")
    suspend fun getReels(): ReelsResponse

    @POST("like")
    suspend fun likeReel(
        @Body body: ReelLikeRequest
    )

    @DELETE("dislike")
    suspend fun dislikeReel(
        @Query("reels_id") reelId: String
    )
}