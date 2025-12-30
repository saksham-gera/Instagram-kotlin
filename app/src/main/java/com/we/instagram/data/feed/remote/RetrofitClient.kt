package com.we.instagram.data.remote

import com.we.instagram.data.feed.remote.FeedApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://dfbf9976-22e3-4bb2-ae02-286dfd0d7c42.mock.pstmn.io/user/"

    val feedApi: FeedApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(FeedApi::class.java)
    }
}