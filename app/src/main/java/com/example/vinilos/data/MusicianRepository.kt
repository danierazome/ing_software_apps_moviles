package com.example.vinilos.data

import com.example.vinilos.model.Musician
import com.example.vinilos.network.MusicianApiService

interface MusicianRepository {
    suspend fun getMusicians(): List<Musician>
}

class NetworkMusicianRepository(private val musicianApiService: MusicianApiService): MusicianRepository {
    override suspend fun getMusicians(): List<Musician> {
        return musicianApiService.getMusicians()
    }
}