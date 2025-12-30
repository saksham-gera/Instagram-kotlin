package com.we.instagram.data.feed.remote

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object FeedApiProvider {

    private const val BASE_URL = "https://dfbf9976-22e3-4bb2-ae02-286dfd0d7c42.mock.pstmn.io/user/"

    val feedApi: FeedApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(FeedApi::class.java)
}