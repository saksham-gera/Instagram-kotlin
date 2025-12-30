package com.we.instagram.ui.reels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.we.instagram.data.reels.ReelsRepository
import com.we.instagram.data.reels.local.ReelEntity
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ReelsViewModel(
    private val repository: ReelsRepository
) : ViewModel() {

    val reels: StateFlow<List<ReelEntity>> =
        repository.getReels()
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
        viewModelScope.launch {
            repository.refresh()
        }
    }

    fun onLikeClicked(reel: ReelEntity) {
        viewModelScope.launch {
            repository.toggleLike(reel.id, !reel.isLiked)
        }
    }
}