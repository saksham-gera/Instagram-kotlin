package com.we.instagram.ui.feed

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.we.instagram.data.feed.FeedRepository
import com.we.instagram.data.feed.local.PostEntity
import com.we.instagram.util.NetworkUtils
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FeedViewModel(
    private val repository: FeedRepository,
    private val context: Context
) : ViewModel() {

    val feed: StateFlow<List<PostEntity>> =
        repository.getPosts()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                emptyList()
            )

    private val _isOffline = MutableStateFlow(
        !NetworkUtils.isNetworkAvailable(context)
    )
    val isOffline: StateFlow<Boolean> = _isOffline

    init {
        observeNetwork()
        refresh()
    }

    fun onLikeClicked(postId: String) {
        viewModelScope.launch {
            repository.toggleLike(postId)
        }
    }

    fun refresh() {
        if (_isOffline.value) return

        viewModelScope.launch {
            try {
                repository.refreshPosts()
            } catch (e: Exception) {
                // silent fail
            }
        }
    }

    /** 🔥 Network observer */
    private fun observeNetwork() {
        viewModelScope.launch {
            while (true) {
                val offlineNow = !NetworkUtils.isNetworkAvailable(context)
                if (_isOffline.value != offlineNow) {
                    _isOffline.value = offlineNow

                    // Auto-refresh when internet comes back
                    if (!offlineNow) {
                        refresh()
                    }
                }
                delay(2_000) // poll every 2s (simple & reliable)
            }
        }
    }
}