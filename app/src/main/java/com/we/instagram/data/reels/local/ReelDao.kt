package com.we.instagram.data.reels.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ReelDao {

    @Query("SELECT * FROM reels")
    fun getReels(): Flow<List<ReelEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(reels: List<ReelEntity>)

    @Query("""
        UPDATE reels 
        SET isLiked = :liked, 
            likeCount = likeCount + :delta,
            pendingSync = 1
        WHERE id = :reelId
    """)
    suspend fun updateLike(reelId: String, liked: Boolean, delta: Int)

    @Query("SELECT * FROM reels WHERE pendingSync = 1")
    suspend fun getPendingSync(): List<ReelEntity>
}