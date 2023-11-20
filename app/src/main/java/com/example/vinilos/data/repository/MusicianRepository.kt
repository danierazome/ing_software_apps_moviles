package com.example.vinilos.data.repository

import com.example.vinilos.data.model.Musician
import com.example.vinilos.data.network.apiServices.MusicianApiService
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

interface MusicianRepository {
    suspend fun getMusicians(): List<Musician>
}

class NetworkMusicianRepository(private val musicianApiService: MusicianApiService):
    MusicianRepository {

    private val musiciansMutex = Mutex()
    private var musician = emptyList<Musician>()
    override suspend fun getMusicians(): List<Musician> {
        if (musician.isNotEmpty()) return musiciansMutex.withLock { this.musician }
        musiciansMutex.withLock {
            this.musician = musicianApiService.getMusicians()
        }
        return musiciansMutex.withLock { this.musician }
    }
}