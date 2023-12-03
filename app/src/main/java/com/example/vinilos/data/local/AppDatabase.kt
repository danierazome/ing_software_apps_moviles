package com.example.vinilos.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vinilos.data.local.dao.AlbumDao
import com.example.vinilos.data.local.dao.CommentDao
import com.example.vinilos.data.local.dao.TrackDao
import com.example.vinilos.data.local.entities.album.AlbumEntity
import com.example.vinilos.data.local.entities.album.CommentEntity
import com.example.vinilos.data.local.entities.album.TrackEntity

@Database(
    entities = [AlbumEntity::class, TrackEntity::class, CommentEntity::class],
    version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun albumDao(): AlbumDao
    abstract fun trackDao(): TrackDao
    abstract fun commentDao(): CommentDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "vinyls"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
        }
    }
}