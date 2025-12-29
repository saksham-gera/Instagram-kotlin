package com.we.instagram.data.feed.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Query("SELECT * FROM posts")
    fun getAllPosts(): Flow<List<PostEntity>>

    @Query("""
        UPDATE posts
        SET 
            isLiked = NOT isLiked,
            likes = CASE
                WHEN isLiked THEN likes - 1
                ELSE likes + 1
            END
        WHERE id = :postId
    """)
    suspend fun toggleLike(postId: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<PostEntity>)

    @Query("DELETE FROM posts")
    suspend fun clearPosts()
}