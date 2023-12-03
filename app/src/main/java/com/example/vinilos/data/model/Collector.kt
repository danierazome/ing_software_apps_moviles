package com.example.vinilos.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Collector (val name: String, val comments: List<Comment>, val id: Int)

@Serializable
class Comment(val id: Int, val description: String, val rating: Int)

