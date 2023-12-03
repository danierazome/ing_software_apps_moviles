package com.example.vinilos.model

import kotlinx.serialization.Serializable

@Serializable
data class Collector (val name: String, val comments: List<Comment>?)

@Serializable
class Comment(val id: Int, val description: String, val rating: Int)