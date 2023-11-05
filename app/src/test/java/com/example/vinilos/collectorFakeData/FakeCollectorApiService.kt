package com.example.vinilos.collectorFakeData

import com.example.vinilos.model.Collector
import com.example.vinilos.network.CollectorApiService

class FakeCollectorApiService: CollectorApiService {
    override suspend fun getCollectors(): List<Collector> {
        return CollectorFakeData.collectorsData
    }
}