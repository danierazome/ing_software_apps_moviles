package com.example.vinilos.data.local.entities.album

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album")
data class AlbumEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "cover") val cover: String,
    @ColumnInfo(name = "releaseDate") val releaseDate: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "genre") val genre: String,
    @ColumnInfo(name = "recordLabel") val recordLabel: String,
)

@Entity(tableName = "comment")
data class CommentEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "rating") val rating: Int,
    @ColumnInfo(name = "albumId") val albumId: Int)


@Entity(tableName = "track")
data class TrackEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "duration") val duration: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "albumId") val albumId: Int)
