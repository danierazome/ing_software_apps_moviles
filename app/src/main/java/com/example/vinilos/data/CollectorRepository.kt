package com.example.vinilos.data

import com.example.vinilos.model.Collector
import com.example.vinilos.network.CollectorApiService

interface CollectorRepository {
    suspend fun getCollectors(): List<Collector>
}

class NetworkCollectorRepository(private val collectorApiService: CollectorApiService): CollectorRepository {
    override suspend fun getCollectors(): List<Collector> {
        return collectorApiService.getCollectors()
    }
}