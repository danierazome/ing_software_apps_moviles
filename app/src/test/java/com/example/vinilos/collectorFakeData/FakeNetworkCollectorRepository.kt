package com.example.vinilos.collectorFakeData

import com.example.vinilos.data.CollectorRepository
import com.example.vinilos.model.Collector

class FakeNetworkCollectorRepository: CollectorRepository {
    override suspend fun getCollectors(): List<Collector> {
        return CollectorFakeData.collectorsData
    }
}