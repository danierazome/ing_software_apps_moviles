package com.example.vinilos.data.model.musician

open class DetailedArtist(
    val description: String,
    val birthDate: String,
    val albumes:  List<Album>,
    val comments: List<PerformerPrizes>,
)
data class Album(val id: Int, val name: String, val description: String, val genre: String)
data class PerformerPrizes(val id: Int, val premiationDate: String)
