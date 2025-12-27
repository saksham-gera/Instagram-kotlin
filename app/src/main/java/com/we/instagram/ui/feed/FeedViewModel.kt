package com.we.instagram.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.we.instagram.data.feed.FeedRepository
import com.we.instagram.data.feed.local.PostEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// FeedViewModel.kt
class FeedViewModel(private val repository: FeedRepository) : ViewModel() {

    val feed: StateFlow<List<PostEntity>> = repository.getPosts()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        viewModelScope.launch {
            repository.refreshPosts()
        }
    }
}