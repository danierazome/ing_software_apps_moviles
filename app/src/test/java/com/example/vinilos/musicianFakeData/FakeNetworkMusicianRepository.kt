package com.example.vinilos.musicianFakeData

import com.example.vinilos.data.MusicianRepository
import com.example.vinilos.model.Musician

class FakeNetworkMusicianRepository: MusicianRepository {
    override suspend fun getMusicians(): List<Musician> {
        return MusicianFakeData.musiciansData
    }
}