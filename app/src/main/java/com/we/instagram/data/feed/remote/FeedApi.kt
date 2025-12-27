package com.we.instagram.data.feed.remote

import retrofit2.http.GET

interface FeedApi {

    @GET("posts")
    suspend fun getPosts(): List<PostDto>
}