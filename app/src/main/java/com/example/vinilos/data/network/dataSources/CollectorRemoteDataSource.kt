package com.example.vinilos.data.network.dataSources

import com.example.vinilos.data.network.apiServices.CollectorApiService
import com.example.vinilos.data.network.models.collectorNetwork.CollectorNetwork

class CollectorRemoteDataSource(private val collectorApiService: CollectorApiService) {
    suspend fun getCollectors(): List<CollectorNetwork> {
        return collectorApiService.getCollectors()
    }

    suspend fun getCollector(id: Int): CollectorNetwork {
        return collectorApiService.getCollector(id)
    }
}