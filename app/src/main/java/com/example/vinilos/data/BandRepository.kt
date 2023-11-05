package com.example.vinilos.data

import com.example.vinilos.model.Album
import com.example.vinilos.model.Band
import com.example.vinilos.model.Collector
import com.example.vinilos.network.BandApiService
import com.example.vinilos.network.CollectorApiService

interface BandRepository {
    suspend fun getBands(): List<Band>
}

class NetworkBandRepository(private val bandApiService: BandApiService): BandRepository {
    override suspend fun getBands(): List<Band> {
        return bandApiService.getBands()
    }
}