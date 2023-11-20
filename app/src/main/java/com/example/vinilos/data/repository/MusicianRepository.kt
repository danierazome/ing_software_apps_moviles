package com.example.vinilos.data.repository

import com.example.vinilos.data.model.Musician.Musician
import com.example.vinilos.data.network.apiServices.MusicianApiService

interface MusicianRepository {
    suspend fun getMusicians(): List<Musician>
}

class NetworkMusicianRepository(private val musicianApiService: MusicianApiService):
    MusicianRepository {
    override suspend fun getMusicians(): List<Musician> {
        return musicianApiService.getMusicians()
    }
}