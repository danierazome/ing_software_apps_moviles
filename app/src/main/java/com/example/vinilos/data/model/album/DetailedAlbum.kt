package com.example.vinilos.data.model.album
data class DetailedAlbum(
    val id: Int,
    val name: String,
    val cover: String,
    val releaseDate: String,
    val comments: List<Comment>,
    val tracks: List<Track>
)
data class Comment(val id: Int, val description: String, val rating: Int)
data class Track(val id: Int, val duration: String, val name: String)
data class TrackRequest(val  name: String, val duration: String)
