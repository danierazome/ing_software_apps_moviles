package com.example.vinilos.networkTest

import com.example.vinilos.albumFakeData.AlbumFakeData
import com.example.vinilos.albumFakeData.FakeAlbumApiService
import com.example.vinilos.data.NetworkAlbumRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkAlbumRepositoryTest {

    @Test
    fun networkAlbumRepository_getAlbums() =
        runTest {
            val repository = NetworkAlbumRepository(
                albumApiService = FakeAlbumApiService()
            )
            assertEquals(AlbumFakeData.albumsData, repository.getAlbums())
        }
}