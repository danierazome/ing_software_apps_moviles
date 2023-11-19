package com.example.vinilos.data.repository

import com.example.vinilos.data.model.Band
import com.example.vinilos.data.network.apiServices.BandApiService

interface BandRepository {
    suspend fun getBands(): List<Band>
}

class NetworkBandRepository(private val bandApiService: BandApiService): BandRepository {
    override suspend fun getBands(): List<Band> {
        return bandApiService.getBands()
    }
}