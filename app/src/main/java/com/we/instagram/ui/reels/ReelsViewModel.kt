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
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                emptyList()
            )

    private val _error = MutableSharedFlow<String>()
    val error: SharedFlow<String> = _error.asSharedFlow()

    fun onLikeClicked(reel: ReelEntity) {
        viewModelScope.launch {
            try {
                repository.toggleLike(reel.id)
            } catch (e: Exception) {
                _error.emit("Failed to update reel like")
            }
        }
    }
}