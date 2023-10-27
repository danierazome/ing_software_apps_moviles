package com.example.vinilos.data

import com.example.vinilos.model.Album
import com.example.vinilos.network.AlbumApiService

interface AlbumRepository {
    suspend fun getAlbums(): List<Album>
}

class NetworkAlbumRepository(private val albumApiService: AlbumApiService): AlbumRepository {

    override suspend fun getAlbums(): List<Album> {
        return albumApiService.getAlbums()
    }
}