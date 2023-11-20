package com.example.vinilos.data.local.entities.musician

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "musician")
data class MusicianEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "birthDate") val birthDate: String,
)

@Entity(tableName = "album")
data class AlbumEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "cover") val cover: String,
    @ColumnInfo(name = "releaseDate") val releaseDate: String,
)