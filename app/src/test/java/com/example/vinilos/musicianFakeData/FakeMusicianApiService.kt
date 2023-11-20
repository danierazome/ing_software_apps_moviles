package com.example.vinilos.musicianFakeData

import com.example.vinilos.data.model.musician.Musician
import com.example.vinilos.data.network.apiServices.MusicianApiService

class FakeMusicianApiService: MusicianApiService {
    override suspend fun getMusicians(): List<Musician> {
        return MusicianFakeData.musiciansData
    }
}