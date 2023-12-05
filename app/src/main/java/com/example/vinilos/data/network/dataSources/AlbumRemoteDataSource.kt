package com.example.vinilos.data.network.dataSources

import com.example.vinilos.data.model.musician.Album
import com.example.vinilos.data.network.apiServices.AlbumApiService
import com.example.vinilos.data.network.models.network.AlbumNetwork
import com.example.vinilos.data.network.models.network.AddTrackRequest

class AlbumRemoteDataSource(private val albumApiService: AlbumApiService) {
    suspend fun getAlbums(): List<AlbumNetwork> {
        return albumApiService.getAlbums()
    }

    suspend fun getDetailedAlbum(id: Int): AlbumNetwork {
        return albumApiService.getDetailedAlbum(id)
    }

    suspend fun saveAlbum(album: AlbumNetwork): AlbumNetwork {
        println("vinilos - saveAlbum:  $album")
        return albumApiService.saveAlbum(album)
    }

    suspend fun addTrackAlbum(id: Int, request: AddTrackRequest): AddTrackRequest {
        println("El id es $id, y el request es $request")
        return albumApiService.addTrackAlbum(id, request)
    }
}
