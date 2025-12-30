package com.we.instagram.ui.reels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.we.instagram.data.AppDatabase
import com.we.instagram.data.reels.ReelsRepository
import com.we.instagram.data.reels.remote.ReelsApi
import com.we.instagram.data.reels.remote.ReelsApiProvider

class ReelsViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReelsViewModel::class.java)) {

            // Room
            val db = AppDatabase.getInstance(context)
            val reelDao = db.reelDao()

            // Retrofit
            val reelsApi: ReelsApi = ReelsApiProvider.api

            // Repository
            val repository = ReelsRepository(
                dao = reelDao,
                api = reelsApi
            )

            return ReelsViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}