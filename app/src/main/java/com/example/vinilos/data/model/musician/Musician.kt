package com.example.vinilos.data.model.musician

import com.example.vinilos.data.model.album.Album
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Musician (
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String,
)