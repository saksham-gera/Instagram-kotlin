package com.we.instagram.data.reels

import com.we.instagram.data.reels.local.ReelDao
import com.we.instagram.data.reels.remote.ReelLikeRequest
import com.we.instagram.data.reels.remote.ReelsApi
import com.we.instagram.util.NetworkUtils

class ReelsRepository(
    private val dao: ReelDao,
    private val api: ReelsApi,
    private val networkUtils: NetworkUtils
) {

    fun getReels() = dao.getReels()

    suspend fun toggleLike(reelId: String) {

        // 1️⃣ Get current reel
        val reel = dao.getReelById(reelId)

        val newLikedState = !reel.isLiked
        val delta = if (newLikedState) 1 else -1

        // 2️⃣ Optimistic DB update
        dao.updateLike(
            reelId = reelId,
            liked = newLikedState,
            delta = delta
        )

        // 3️⃣ Offline → stop here (pendingSync already marked)
        if (!networkUtils.isNetworkAvailable()) return

        // 4️⃣ API call
        try {
            if (newLikedState) {
                api.likeReel(ReelLikeRequest(true, reelId))
            } else {
                api.dislikeReel(reelId)
            }

        } catch (e: Exception) {
            // 5️⃣ Revert on failure
            dao.updateLike(
                reelId = reelId,
                liked = reel.isLiked,
                delta = -delta
            )
            throw e
        }
    }
}