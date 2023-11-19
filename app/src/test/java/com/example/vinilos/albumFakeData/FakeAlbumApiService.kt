package com.example.vinilos.albumFakeData

import com.example.vinilos.data.network.apiServices.AlbumApiService
import com.example.vinilos.data.network.models.albumNetwork.AlbumNetwork

class FakeAlbumApiService: AlbumApiService {
    override suspend fun getAlbums(): List<AlbumNetwork> {
        return AlbumFakeData.albumsNetworkData
    }

    override suspend fun getDetailedAlbum(id: Int): AlbumNetwork {
        return AlbumFakeData.albumsNetworkData[0]
    }
}