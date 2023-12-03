package com.example.vinilos.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class PrizeNetwork(val name: String, val description: String, val organization: String)