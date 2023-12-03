package com.example.vinilos.data.repository

import com.example.vinilos.data.model.Band
import com.example.vinilos.data.model.musician.DetailedArtist
import com.example.vinilos.data.model.musician.Musician
import com.example.vinilos.data.network.apiServices.BandApiService
import com.example.vinilos.data.network.apiServices.MusicianApiService
import com.example.vinilos.data.network.dataSources.ArtistRemoteDataSource

interface IMusicianRepository {
    suspend fun getMusicians(): List<Musician>

    suspend fun getDetailedArtist(id: Int): Musician
}
class NetworkMusicianRepository(private val musicianApiService: MusicianApiService): IMusicianRepository {
    override suspend fun getMusicians(): List<Musician> {
        return musicianApiService.getMusicians()
    }

    override suspend fun getDetailedArtist(id: Int): Musician {
        return musicianApiService.getDetailedArtist(id)
    }
}