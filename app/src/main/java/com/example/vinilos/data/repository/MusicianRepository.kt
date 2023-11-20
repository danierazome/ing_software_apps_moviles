package com.example.vinilos.data.repository

import com.example.vinilos.data.local.dao.MusicianDao
import com.example.vinilos.data.model.musician.DetailedArtist
import com.example.vinilos.data.model.musician.Musician
import com.example.vinilos.data.network.dataSources.ArtistRemoteDataSource
import com.example.vinilos.data.network.models.network.ArtistNetwork

interface IMusicianRepository {
    suspend fun getMusicians(): List<Musician>

    suspend fun getDetailedArtist(id: Int): ArtistNetwork
}

class MusicianRepository(
    private val artistRemoteDataSource: ArtistRemoteDataSource,
    private val musicianDao: MusicianDao,
): IMusicianRepository {

    override suspend fun getMusicians(): List<Musician> {
        return artistRemoteDataSource.getMusician()
    }

    override suspend fun getDetailedArtist(id: Int): ArtistNetwork {
       return artistRemoteDataSource.getDetailedArtist(id)
    }
}