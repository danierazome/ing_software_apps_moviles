package com.example.vinilos.data.repository

import com.example.vinilos.data.model.Band
import com.example.vinilos.data.model.musician.DetailedArtist
import com.example.vinilos.data.model.musician.Musician
import com.example.vinilos.data.network.apiServices.BandApiService
import com.example.vinilos.data.network.apiServices.MusicianApiService
import com.example.vinilos.data.network.dataSources.ArtistRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

interface MusicianRepository {
    suspend fun getMusicians(): List<Musician>

    suspend fun getDetailedArtist(id: Int): Musician
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

    override suspend fun getDetailedArtist(id: Int): Musician {
        return musicianApiService.getDetailedArtist(id)
    }
}