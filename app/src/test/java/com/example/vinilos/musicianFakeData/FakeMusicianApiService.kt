package com.example.vinilos.musicianFakeData

import com.example.vinilos.model.Musician
import com.example.vinilos.network.MusicianApiService

class FakeMusicianApiService: MusicianApiService {
    override suspend fun getMusicians(): List<Musician> {
        return MusicianFakeData.musiciansData
    }
}