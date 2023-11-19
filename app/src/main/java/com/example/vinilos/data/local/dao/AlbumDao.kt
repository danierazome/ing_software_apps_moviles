package com.example.vinilos.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.vinilos.data.local.entities.album.AlbumEntity
import com.example.vinilos.data.local.entities.album.AlbumWithTrackAndComment
import com.example.vinilos.data.local.entities.album.CommentEntity
import com.example.vinilos.data.local.entities.album.TrackEntity

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(albumEntity: AlbumEntity)

    @Transaction
    @Query("SELECT * FROM album WHERE id = :albumId")
    suspend fun getAlbumWithTrackAndComment(albumId: Int): AlbumWithTrackAndComment

}

@Dao
interface TrackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(trackEntity: TrackEntity)
}

@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(commentEntity: CommentEntity)
}