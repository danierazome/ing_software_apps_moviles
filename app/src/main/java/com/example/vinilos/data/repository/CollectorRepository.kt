package com.example.vinilos.data.repository

import com.example.vinilos.data.model.Collector
import com.example.vinilos.data.network.apiServices.CollectorApiService
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

interface CollectorRepository {
    suspend fun getCollectors(): List<Collector>
}

class NetworkCollectorRepository(private val collectorApiService: CollectorApiService):
    CollectorRepository {

    private val collectorsMutex = Mutex()
    private var collectors = emptyList<Collector>()
    override suspend fun getCollectors(): List<Collector> {
        if (collectors.isNotEmpty()) return collectorsMutex.withLock { this.collectors }
        collectorsMutex.withLock {
            this.collectors = collectorApiService.getCollectors()
        }
        return collectorsMutex.withLock { this.collectors }
    }
}