package com.example.vinilos.data.network.dataSources

import com.example.vinilos.data.model.musician.Musician
import com.example.vinilos.data.network.apiServices.MusicianApiService
import com.example.vinilos.data.network.models.network.ArtistNetwork

class ArtistRemoteDataSource(private val artistApiService: MusicianApiService) {
    suspend fun getMusician(): List<Musician> {
        return artistApiService.getMusicians()
    }

    suspend fun getDetailedArtist(id: Int): ArtistNetwork {
        return artistApiService.getDetailedArtist(id)
    }
}