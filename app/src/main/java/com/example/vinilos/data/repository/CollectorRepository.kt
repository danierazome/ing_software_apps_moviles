package com.example.vinilos.data.repository

import com.example.vinilos.data.model.Collector
import com.example.vinilos.data.network.apiServices.CollectorApiService

interface CollectorRepository {
    suspend fun getCollectors(): List<Collector>
}

class NetworkCollectorRepository(private val collectorApiService: CollectorApiService):
    CollectorRepository {
    override suspend fun getCollectors(): List<Collector> {
        return collectorApiService.getCollectors()
    }
}