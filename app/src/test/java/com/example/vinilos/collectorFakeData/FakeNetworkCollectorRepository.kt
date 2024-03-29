package com.example.vinilos.collectorFakeData

import com.example.vinilos.data.repository.CollectorRepository
import com.example.vinilos.data.model.Collector

class FakeNetworkCollectorRepository: CollectorRepository {
    override suspend fun getCollectors(): List<Collector> {
        return CollectorFakeData.collectorsData
    }
}