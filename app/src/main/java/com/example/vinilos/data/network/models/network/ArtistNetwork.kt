package com.example.vinilos.data.network.models.network

import kotlinx.serialization.Serializable

@Serializable
data class ArtistNetwork(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String,
    val albums: List<AlbumNetwork>,
)