package com.example.vinilos.networkTest

import com.example.vinilos.collectorFakeData.CollectorFakeData
import com.example.vinilos.collectorFakeData.FakeCollectorApiService
import com.example.vinilos.data.NetworkCollectorRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkCollectorRepositoryTest {

    @Test
    fun networkCollectorRepository_getCollectors() =
        runTest {
            val repository = NetworkCollectorRepository(
                collectorApiService = FakeCollectorApiService()
            )
            assertEquals(CollectorFakeData.collectorsData, repository.getCollectors())
        }
}