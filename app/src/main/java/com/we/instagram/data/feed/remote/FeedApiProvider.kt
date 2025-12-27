package com.we.instagram.data.feed.remote

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object FeedApiProvider {

    private const val BASE_URL = "https://custom-json.vercel.app/api/"

    val feedApi: FeedApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(FeedApi::class.java)
}