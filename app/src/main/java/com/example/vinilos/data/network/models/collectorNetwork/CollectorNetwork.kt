package com.example.vinilos.data.network.models.collectorNetwork

import kotlinx.serialization.Serializable

@Serializable
data class CollectorNetwork(
    val id: Int,
    val name: String,
    val telephone: String,
    val email: String,
    val comments: List<CollectorCommentNetwork>,
    val favoritePerformers: List<PerformerNetwork>
)

@Serializable
data class CollectorCommentNetwork(val id: Int, val description: String, val rating: Int)

@Serializable
data class PerformerNetwork(val id: Int, val description: String, val name: String, val image: String)