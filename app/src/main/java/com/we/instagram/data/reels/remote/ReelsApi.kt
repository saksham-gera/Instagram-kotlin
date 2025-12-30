package com.we.instagram.data.reels.remote

import retrofit2.http.*

interface ReelsApi {

    @GET("user/reels")
    suspend fun getReels(): ReelsResponse

    @POST("user/like")
    suspend fun likeReel(@Body body: Map<String, String>)

    @DELETE("user/dislike")
    suspend fun dislikeReel(@Body body: Map<String, String>)
}