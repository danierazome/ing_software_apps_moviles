package com.example.vinilos.data.network.dataSources

import com.example.vinilos.data.model.musician.Album
import com.example.vinilos.data.network.apiServices.AlbumApiService
import com.example.vinilos.data.network.models.network.AlbumNetwork

class AlbumRemoteDataSource(private val albumApiService: AlbumApiService) {
    suspend fun getAlbums(): List<AlbumNetwork> {
        return albumApiService.getAlbums()
    }

    suspend fun getDetailedAlbum(id: Int): AlbumNetwork {
        return albumApiService.getDetailedAlbum(id)
    }

    suspend fun saveAlbum(album: AlbumNetwork) {
        println("vinilos - saveAlbum: "+album.toString())
        val a =albumApiService.saveAlbum(album)
        println("vinilos - saveAlbum: "+a)

        return a
    }
}