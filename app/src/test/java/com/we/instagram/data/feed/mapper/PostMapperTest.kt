package com.we.instagram.data.feed.mapper

import com.we.instagram.data.feed.remote.PostDto
import org.junit.Assert.assertEquals
import org.junit.Test

class PostMapperTest {

    @Test
    fun `toEntity should correctly map PostDto to PostEntity`() {
        // Arrange
        val dto = PostDto(
            id = "123",
            username = "test_user",
            imageUrl = "https://image.com",
            caption = "Hello World",
            likes = 10
        )

        // Act
        val entity = dto.toEntity()

        // Assert
        assertEquals(dto.id, entity.id)
        assertEquals(dto.username, entity.username)
        assertEquals(dto.imageUrl, entity.imageUrl)
        assertEquals(dto.caption, entity.caption)
        assertEquals(dto.likes, entity.likes)
        assertEquals(false, entity.isLiked) // Default value check
    }
}