package com.we.instagram.data.reels.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ReelsApiProvider {

    private const val BASE_URL =
        "https://dfbf9976-22e3-4bb2-ae02-286dfd0d7c42.mock.pstmn.io/user/"

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory()) // 🔥 REQUIRED
        .build()

    val api: ReelsApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ReelsApi::class.java)
    }
}