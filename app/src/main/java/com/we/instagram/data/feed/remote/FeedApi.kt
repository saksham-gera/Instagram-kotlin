package com.we.instagram.data.feed.remote

import retrofit2.http.GET

interface FeedApi {

    @GET("feed")
    suspend fun getPosts(): FeedResponse
}