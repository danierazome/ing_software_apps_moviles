package com.example.vinilos.albumFakeData

import com.example.vinilos.data.model.album.AlbumNetwork
import com.example.vinilos.data.network.apiServices.AlbumApiService

class FakeAlbumApiService: AlbumApiService {
    override suspend fun getAlbums(): List<AlbumNetwork> {
        return AlbumFakeData.albumsData
    }
}