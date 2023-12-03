package com.example.vinilos.data.repository

import com.example.vinilos.data.local.dao.CollectorCommentDao
import com.example.vinilos.data.local.dao.CollectorDao
import com.example.vinilos.data.local.dao.PerformerDao
import com.example.vinilos.data.mappers.asEntity
import com.example.vinilos.data.mappers.asUIDetailedModel
import com.example.vinilos.data.mappers.asUIModel
import com.example.vinilos.data.model.Collector
import com.example.vinilos.data.model.collector.DetailedCollector
import com.example.vinilos.data.network.dataSources.CollectorRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

interface CollectorRepository {
    suspend fun getCollectors(): List<Collector>
    suspend fun getCollector(id: Int): DetailedCollector
}

class NetworkCollectorRepository(
    private val collectorRemoteDataSource: CollectorRemoteDataSource,
    private val collectorDao: CollectorDao,
    private val performerDao: PerformerDao,
    private val collectorCommentDao: CollectorCommentDao
):
    CollectorRepository {

    private val collectorsMutex = Mutex()
    private var collectors = emptyList<Collector>()
    override suspend fun getCollectors(): List<Collector> {
        if (collectors.isNotEmpty()) return collectorsMutex.withLock { this.collectors }
        collectorsMutex.withLock {
            this.collectors = collectorRemoteDataSource.getCollectors().map { it.asUIModel() }
        }
        return collectorsMutex.withLock { this.collectors }
    }

    override suspend fun getCollector(id: Int): DetailedCollector {
        val collectorNetwork = collectorRemoteDataSource.getCollector(id)

        return collectorNetwork.asUIDetailedModel()
    }
}