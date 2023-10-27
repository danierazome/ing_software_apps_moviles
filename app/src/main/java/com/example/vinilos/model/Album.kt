package com.example.vinilos.model

import kotlinx.serialization.Serializable

@Serializable
data class Album(val name: String, val cover: String)