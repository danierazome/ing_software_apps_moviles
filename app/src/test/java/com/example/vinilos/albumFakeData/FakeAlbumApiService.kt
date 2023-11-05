package com.example.vinilos.albumFakeData

import com.example.vinilos.model.Album
import com.example.vinilos.network.AlbumApiService

class FakeAlbumApiService: AlbumApiService {
    override suspend fun getAlbums(): List<Album> {
        return AlbumFakeData.albumsData
    }
}