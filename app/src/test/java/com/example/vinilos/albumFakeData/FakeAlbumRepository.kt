package com.example.vinilos.albumFakeData

import com.example.vinilos.data.mappers.asUIDetailedModel
import com.example.vinilos.data.mappers.asUIModel
import com.example.vinilos.data.model.album.Album
import com.example.vinilos.data.repository.IAlbumRepository
import com.example.vinilos.data.model.album.DetailedAlbum

class FakeAlbumRepository(): IAlbumRepository {
    override suspend fun getAlbums(): List<Album> {
        return AlbumFakeData.albumsNetworkData.map { it.asUIModel() }
    }

    override suspend fun getDetailedAlbum(id: Int): DetailedAlbum {
        return AlbumFakeData.albumsNetworkData[0].asUIDetailedModel()
    }
}