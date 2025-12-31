package com.we.instagram.ui.feed

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.we.instagram.data.AppDatabase
import com.we.instagram.data.feed.FeedRepository
import com.we.instagram.data.feed.remote.FeedApiProvider
import com.we.instagram.util.NetworkUtils

class FeedViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeedViewModel::class.java)) {

            val appContext = context.applicationContext

            val database = AppDatabase.getInstance(appContext)

            val repository = FeedRepository(
                postDao = database.postDao(),
                feedApi = FeedApiProvider.feedApi,
                network = NetworkUtils(appContext)
            )

            @Suppress("UNCHECKED_CAST")
            return FeedViewModel(
                repository = repository,
                context = appContext
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}