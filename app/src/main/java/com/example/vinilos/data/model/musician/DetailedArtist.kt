package com.example.vinilos.data.model.musician

open class DetailedArtist(
    val description: String,
    val birthDate: String,
    val albumes:  List<Album>,
    val comments: List<PerformerPrizes>,
)
data class Album(
    val name: String,
    val cover: String,
    val releaseDate: String,
    val description: String,
    val genre: String,
    val recordLabel: String)

data class PerformerPrizes(val id: Int, val premiationDate: String)
