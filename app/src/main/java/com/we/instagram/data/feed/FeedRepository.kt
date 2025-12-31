package com.we.instagram.data.feed
import kotlinx.coroutines.flow.emitAll
import com.we.instagram.data.feed.local.PostDao
import com.we.instagram.data.feed.local.PostEntity
import com.we.instagram.data.feed.mapper.toEntity
import com.we.instagram.data.feed.remote.FeedApi
import com.we.instagram.data.feed.remote.LikeRequest
import com.we.instagram.util.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

// FeedRepository.kt
class FeedRepository(
    private val postDao: PostDao,
    private val feedApi: FeedApi,
    private val network: NetworkUtils
) {

    fun getPosts(): Flow<List<PostEntity>> =
        postDao.getAllPosts()

    suspend fun toggleLike(postId: String) {
        postDao.toggleLike(postId)

        if (!network.isNetworkAvailable()) return

        try {
            val post = postDao.getPostById(postId)

            if (post.likedByUser) {
                feedApi.likePost(LikeRequest(true, postId))
            } else {
                feedApi.dislikePost(postId)
            }

        } catch (e: Exception) {
            // 3️⃣ Revert on failure
            postDao.toggleLike(postId)
            throw e
        }
    }

    suspend fun refreshPosts() {
        try {
            val remotePosts = feedApi.getPosts()
            val entities = remotePosts.feed.map { it.toEntity() }
            postDao.clearPosts()
            postDao.insertPosts(entities)
            println("Success: Fetched ${entities.size} posts")
        } catch (e: Exception) {
            println("Error fetching posts: ${e.message}")
        }
    }
}