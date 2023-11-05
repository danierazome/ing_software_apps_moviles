package com.example.vinilos.networkTest

import com.example.vinilos.data.NetworkMusicianRepository
import com.example.vinilos.musicianFakeData.FakeMusicianApiService
import com.example.vinilos.musicianFakeData.MusicianFakeData
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkMusicianRepositoryTest {

    @Test
    fun networkMusicianRepository_getMusicians() =
        runTest {
            val repository = NetworkMusicianRepository(
                musicianApiService = FakeMusicianApiService()
            )
            assertEquals(MusicianFakeData.musiciansData, repository.getMusicians())
        }
}