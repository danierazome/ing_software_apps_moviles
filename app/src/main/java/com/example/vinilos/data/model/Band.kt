package com.example.vinilos.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Band (val id: Int, val name: String, val image: String)