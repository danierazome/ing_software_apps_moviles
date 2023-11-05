package com.example.vinilos.albumFakeData

import com.example.vinilos.data.AlbumRepository
import com.example.vinilos.model.Album

class FakeNetworkAlbumRepository: AlbumRepository {
    override suspend fun getAlbums(): List<Album> {
        return AlbumFakeData.albumsData
    }
}