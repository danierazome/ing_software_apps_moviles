package com.example.vinilos.data.model.musician

import kotlinx.serialization.Serializable

@Serializable
data class Musician (val id: Int, val name: String, val image: String)