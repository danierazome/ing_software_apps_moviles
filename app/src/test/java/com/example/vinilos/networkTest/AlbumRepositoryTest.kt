package com.example.vinilos.networkTest

import com.example.vinilos.albumFakeData.AlbumFakeData
import com.example.vinilos.albumFakeData.FakeAlbumApiService
import com.example.vinilos.data.repository.AlbumRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class AlbumRepositoryTest {

    @Test
    fun networkAlbumRepository_getAlbums() =
        runTest {
            val repository = AlbumRepository(
                albumApiService = FakeAlbumApiService()
            )
            assertEquals(AlbumFakeData.albumsData, repository.getAlbums())
        }
}