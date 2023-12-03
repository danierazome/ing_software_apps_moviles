package com.example.vinilos.data.model.collector

data class DetailedCollector(
    val id: Int,
    val name: String,
    val email: String,
    val telephone: String,
    val collectorComments: List<CollectorComment>,
    val performers: List<Performer>
)
data class CollectorComment(val id: Int, val description: String, val rating: Int)
data class Performer(val id: Int, val description: String, val name: String, val image: String)
