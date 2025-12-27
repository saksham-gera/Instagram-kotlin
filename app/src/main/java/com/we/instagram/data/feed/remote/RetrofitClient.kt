package com.we.instagram.data.remote

import com.we.instagram.data.feed.remote.FeedApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://custom-json.vercel.app/api/"

    val feedApi: FeedApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(FeedApi::class.java)
    }
}