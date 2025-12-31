package com.we.instagram.data.reels.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ReelDao {

    @Query("SELECT * FROM reels")
    fun getReels(): Flow<List<ReelEntity>>

    @Query("SELECT * FROM reels WHERE id = :reelId LIMIT 1")
    suspend fun getReelById(reelId: String): ReelEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(reels: List<ReelEntity>)

    @Query("""
        UPDATE reels 
        SET isLiked = :liked,
            likeCount = likeCount + :delta,
            pendingSync = 1
        WHERE id = :reelId
    """)
    suspend fun updateLike(
        reelId: String,
        liked: Boolean,
        delta: Int
    )

    @Query("SELECT * FROM reels WHERE pendingSync = 1")
    suspend fun getPendingSync(): List<ReelEntity>
}