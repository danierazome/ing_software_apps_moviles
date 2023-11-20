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
import com.example.vinilos.data.local.entities.musician.MusicianEntity

@Dao
interface MusicianDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(albumEntity: AlbumEntity)
    @Transaction
    @Query("SELECT * FROM musician WHERE id = :musicianId")
    suspend fun getArtistWithAlbum(musicianId: Int): MusicianEntity

}

