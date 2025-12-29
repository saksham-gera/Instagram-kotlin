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

    fun getPosts(): Flow<List<PostEntity>> =
        postDao.getAllPosts()

    suspend fun toggleLike(postId: String) {
        postDao.toggleLike(postId)

        // Later: sync with server
    }

    suspend fun refreshPosts() {
        val remotePosts = feedApi.getPosts()
        val entities = remotePosts.map { it.toEntity() }
        postDao.clearPosts()
        postDao.insertPosts(entities)
    }
}