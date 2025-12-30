package com.we.instagram.data.reels

import com.we.instagram.data.reels.local.ReelDao
import com.we.instagram.data.reels.local.ReelEntity
import com.we.instagram.data.reels.mapper.toEntity
import com.we.instagram.data.reels.remote.ReelsApi
import kotlinx.coroutines.flow.Flow

class ReelsRepository(
    private val dao: ReelDao,
    private val api: ReelsApi
) {

    fun getReels(): Flow<List<ReelEntity>> = dao.getReels()

    suspend fun refresh() {
        val remote = api.getReels()
        dao.insertAll(remote.reels.map { it.toEntity() })
    }

    suspend fun toggleLike(reelId: String, liked: Boolean) {
        val delta = if (liked) 1 else -1
        dao.updateLike(reelId, liked, delta)

        try {
            val body = mapOf("reels_id" to reelId)
            if (liked) api.likeReel(body) else api.dislikeReel(body)
        } catch (e: Exception) {
            // rollback on failure
            dao.updateLike(reelId, !liked, -delta)
            throw e
        }
    }
}