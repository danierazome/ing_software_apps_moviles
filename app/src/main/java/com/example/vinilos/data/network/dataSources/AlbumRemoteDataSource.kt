package com.example.vinilos.data.network.dataSources

import com.example.vinilos.data.network.apiServices.AlbumApiService
import com.example.vinilos.data.network.models.albumNetwork.AddTrackRequest
import com.example.vinilos.data.network.models.albumNetwork.AlbumNetwork

class AlbumRemoteDataSource(private val albumApiService: AlbumApiService) {
    suspend fun getAlbums(): List<AlbumNetwork> {
        return albumApiService.getAlbums()
    }

    suspend fun getDetailedAlbum(id: Int): AlbumNetwork {
        return albumApiService.getDetailedAlbum(id)
    }

    suspend fun addTrackAlbum(id: Int, request: AddTrackRequest): AddTrackRequest {
        println("El id es $id, y el request es $request")
        return albumApiService.addTrackAlbum(id, request)
    }
}