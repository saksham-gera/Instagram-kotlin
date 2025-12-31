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
            likeCount = likeCount + CASE 
                WHEN likedByUser = 0 THEN 1 
                ELSE -1 
            END,
            likedByUser = NOT likedByUser
        WHERE postId = :postId
    """)
    suspend fun toggleLike(postId: String)

    @Query("SELECT * FROM posts WHERE postId = :postId LIMIT 1")
    suspend fun getPostById(postId: String): PostEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<PostEntity>)

    @Query("DELETE FROM posts")
    suspend fun clearPosts()
}