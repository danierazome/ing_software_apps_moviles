package com.example.vinilos.albumFakeData

import com.example.vinilos.data.repository.IAlbumRepository
import com.example.vinilos.data.model.album.AlbumNetwork

class FakeNetworkIAlbumRepository: IAlbumRepository {
    override suspend fun getAlbums(): List<AlbumNetwork> {
        return AlbumFakeData.albumsData
    }
}