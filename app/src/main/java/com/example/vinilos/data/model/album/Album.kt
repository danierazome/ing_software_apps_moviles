package com.example.vinilos.data.model.album

import kotlinx.serialization.Serializable

@Serializable
data class Album(val id: Int, val name: String, val cover: String)