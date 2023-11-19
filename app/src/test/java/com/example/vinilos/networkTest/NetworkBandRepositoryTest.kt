package com.example.vinilos.networkTest

import com.example.vinilos.bandFakeData.BandFakeData
import com.example.vinilos.bandFakeData.FakeBandApiService
import com.example.vinilos.data.repository.NetworkBandRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class NetworkBandRepositoryTest {

    @Test
    fun networkAlbumRepository_getBands() =
        runTest {
            val repository = NetworkBandRepository(
                bandApiService = FakeBandApiService()
            )
            Assert.assertEquals(BandFakeData.bandsData, repository.getBands())
        }
}