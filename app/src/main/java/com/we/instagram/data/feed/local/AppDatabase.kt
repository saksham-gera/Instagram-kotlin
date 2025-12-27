package com.we.instagram.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.we.instagram.data.feed.local.PostDao
import com.we.instagram.data.feed.local.PostEntity

@Database(
    entities = [PostEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}