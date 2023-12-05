package com.example.vinilos.data.model.album

data class Album(
    val id: Int,
    val name: String,
    val cover: String,
    val releaseDate: String,
    val description: String,
    val genre: String,
    val recordLabel: String,
    val comments: List<Comment>,
    val tracks: List<Track>
)
