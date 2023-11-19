package com.example.vinilos.data.repository

import android.util.Log
import com.example.vinilos.data.mappers.asEntity
import com.example.vinilos.data.local.dao.AlbumDao
import com.example.vinilos.data.local.dao.CommentDao
import com.example.vinilos.data.local.dao.TrackDao
import com.example.vinilos.data.local.entities.album.AlbumEntity
import com.example.vinilos.data.mappers.asUIDetailedModel
import com.example.vinilos.data.mappers.asUIModel
import com.example.vinilos.data.model.album.Album
import com.example.vinilos.data.model.album.DetailedAlbum
import com.example.vinilos.data.network.dataSources.AlbumRemoteDataSource

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

    override suspend fun getAlbums(): List<Album> {
        return albumRemoteDataSource.getAlbums()
    }

    override suspend fun getDetailedAlbum(id: Int): DetailedAlbum {

        val albumEntity = albumDao.getAlbumWithTrackAndComment(id)

        if (albumEntity != null) return albumEntity.asUIModel()

        val albumNetwork = albumRemoteDataSource.getDetailedAlbum(id)

        insertDetailedAlbum(albumNetwork.asEntity())
        albumNetwork.tracks
            .map { it.asEntity(albumNetwork.id) }
            .forEach { trackDao.insert(it) }
        albumNetwork.comments
            .map { it.asEntity(albumNetwork.id) }
            .forEach { commentDao.insert(it) }

        return albumNetwork.asUIDetailedModel()
    }

    private suspend fun insertDetailedAlbum(albumEntity: AlbumEntity) {
        albumDao.insert(albumEntity = albumEntity)
    }
}