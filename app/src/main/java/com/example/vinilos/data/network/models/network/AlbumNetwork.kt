package com.example.vinilos.data.network.models.albumNetwork

import kotlinx.serialization.Serializable

@Serializable
data class AlbumNetwork(
    val id: Int,
    val name: String,
    val cover: String,
    val releaseDate: String,
    val comments: List<CommentNetwork>,
    val tracks: List<TrackNetwork>
)

@Serializable
data class CommentNetwork(val id: Int, val description: String, val rating: Int)

@Serializable
data class TrackNetwork(val id: Int, val duration: String, val name: String)