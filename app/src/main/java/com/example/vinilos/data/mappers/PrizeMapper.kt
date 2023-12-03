package com.example.vinilos.data.mappers

import com.example.vinilos.data.model.Prize
import com.example.vinilos.data.network.models.PrizeNetwork

fun Prize.asNetworkModel() = PrizeNetwork(
    name = name,
    description = description,
    organization = organization
)