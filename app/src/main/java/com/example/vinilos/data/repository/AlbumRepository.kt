package com.example.vinilos.data.repository

import com.example.vinilos.data.mappers.asEntity
import com.example.vinilos.data.local.dao.AlbumDao
import com.example.vinilos.data.local.dao.CommentDao
import com.example.vinilos.data.local.dao.TrackDao
import com.example.vinilos.data.mappers.asUIDetailedModel
import com.example.vinilos.data.mappers.asUIModel
import com.example.vinilos.data.model.album.Album
import com.example.vinilos.data.model.album.DetailedAlbum
import com.example.vinilos.data.model.album.TrackRequest
import com.example.vinilos.data.network.dataSources.AlbumRemoteDataSource
import com.example.vinilos.data.network.models.albumNetwork.AddTrackRequest
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

interface IAlbumRepository {
    suspend fun getAlbums(): List<Album>
    suspend fun getDetailedAlbum(id: Int): DetailedAlbum
}

class AlbumRepository(
    private val albumRemoteDataSource: AlbumRemoteDataSource,
    private val albumDao: AlbumDao,
    private val trackDao: TrackDao,
    private val commentDao: CommentDao
): IAlbumRepository {

    private val albumsMutex = Mutex()
    private var albums = emptyList<Album>()

    override suspend fun getAlbums(): List<Album> {
        if (albums.isNotEmpty()) return albumsMutex.withLock { this.albums }
        albumsMutex.withLock {
            this.albums = albumRemoteDataSource.getAlbums().map { it.asUIModel() }
        }
        return albumsMutex.withLock { this.albums }
    }

    override suspend fun getDetailedAlbum(id: Int): DetailedAlbum {

        val albumEntity = albumDao.getAlbumWithTrackAndComment(id)

        if (albumEntity != null) return albumEntity.asUIModel()

        val albumNetwork = albumRemoteDataSource.getDetailedAlbum(id)

        albumDao.insert(albumEntity = albumNetwork.asEntity())
        albumNetwork.tracks
            .map { it.asEntity(albumNetwork.id) }
            .forEach { trackDao.insert(it) }
        albumNetwork.comments
            .map { it.asEntity(albumNetwork.id) }
            .forEach { commentDao.insert(it) }

        return albumNetwork.asUIDetailedModel()
    }

    suspend fun addTrackToAlbum(albumId: Int, name: String, duration: String): TrackRequest {
        try {
            val request = AddTrackRequest(name, duration)
            val trackRequest = albumRemoteDataSource.addTrackAlbum(albumId, request)
            return trackRequest.asUIModel()
        } catch (e: Exception) {
            throw e
        }
    }
}