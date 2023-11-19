package com.example.vinilos.collectorFakeData

import com.example.vinilos.data.model.Collector
import com.example.vinilos.data.network.apiServices.CollectorApiService

class FakeCollectorApiService: CollectorApiService {
    override suspend fun getCollectors(): List<Collector> {
        return CollectorFakeData.collectorsData
    }
}