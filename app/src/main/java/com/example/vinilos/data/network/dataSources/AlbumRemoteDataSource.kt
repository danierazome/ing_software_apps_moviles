package com.example.vinilos.data.network.dataSources

import com.example.vinilos.data.model.album.Album
import com.example.vinilos.data.network.apiServices.AlbumApiService
import com.example.vinilos.data.network.models.albumNetwork.AlbumNetwork

class AlbumRemoteDataSource(private val albumApiService: AlbumApiService) {
    suspend fun getAlbums(): List<Album> {
        return albumApiService.getAlbums()
    }

    suspend fun getDetailedAlbum(id: Int): AlbumNetwork {
        return albumApiService.getDetailedAlbum(id)
    }
}