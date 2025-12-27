package com.we.instagram.data.feed
import kotlinx.coroutines.flow.emitAll
import com.we.instagram.data.feed.local.PostDao
import com.we.instagram.data.feed.local.PostEntity
import com.we.instagram.data.feed.mapper.toEntity
import com.we.instagram.data.feed.remote.FeedApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

// FeedRepository.kt
class FeedRepository(
    private val postDao: PostDao,
    private val feedApi: FeedApi
) {
    // Return the infinite flow from Room directly
    fun getPosts(): Flow<List<PostEntity>> = postDao.getAllPosts()

    // Create a separate function for the network work
    suspend fun refreshPosts() {
        try {
            val remotePosts = feedApi.getPosts()
            val entities = remotePosts.map { it.toEntity() }
            postDao.clearPosts()
            postDao.insertPosts(entities)
        } catch (e: Exception) {
            // Log error
        }
    }
}