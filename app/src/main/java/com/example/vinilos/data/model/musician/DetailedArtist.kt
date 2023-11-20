package com.example.vinilos.data.model.musician

import com.example.vinilos.data.model.album.Album

data class DetailedArtist(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String,
    val albums: List<Album>,
)
data class Track(val id: Int, val duration: String, val name: String)
