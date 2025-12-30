package com.we.instagram.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.we.instagram.data.feed.local.PostDao
import com.we.instagram.data.feed.local.PostEntity
import com.we.instagram.data.reels.local.ReelDao
import com.we.instagram.data.reels.local.ReelEntity

@Database(
    entities = [
        PostEntity::class,
        ReelEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao
    abstract fun reelDao(): ReelDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context.applicationContext).also {
                    INSTANCE = it
                }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "instagram_db"
            )
                // ⚠️ For now (safe during development)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}