package com.example.vinilos.musicianFakeData

import com.example.vinilos.data.repository.MusicianRepository
import com.example.vinilos.data.model.musician.Musician

class FakeNetworkMusicianRepository: MusicianRepository {
    override suspend fun getMusicians(): List<Musician> {
        return MusicianFakeData.musiciansData
    }
}