package com.example.vinilos.musicianFakeData

import com.example.vinilos.data.repository.MusicianRepository
import com.example.vinilos.data.model.Musician

class FakeNetworkMusicianRepository: MusicianRepository {
    override suspend fun getMusicians(): List<Musician> {
        return MusicianFakeData.musiciansData
    }
}