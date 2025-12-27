package com.we.instagram.ui.feed

import com.we.instagram.data.feed.FeedRepository
import com.we.instagram.data.feed.local.PostEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class FeedViewModelTest {

    private lateinit var viewModel: FeedViewModel
    private val repository: FeedRepository = mock(FeedRepository::class.java)
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        // Set the Main dispatcher for Coroutines testing
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `feed state should emit posts from repository`() = runTest {
        // Arrange
        val mockPosts = listOf(
            PostEntity("1", "user1", "url1", "cap1", 5, false),
            PostEntity("2", "user2", "url2", "cap2", 5, true)
        )
        whenever(repository.getPosts()).thenReturn(flowOf(mockPosts))

        // Act
        viewModel = FeedViewModel(repository)

        // Start collection in the backgroundScope (automatically cancelled when test ends)
        backgroundScope.launch(UnconfinedTestDispatcher()) {
            viewModel.feed.collect {}
        }

        // Assert
        assertEquals(2, viewModel.feed.value.size)
        assertEquals("user1", viewModel.feed.value[0].username)
    }
}