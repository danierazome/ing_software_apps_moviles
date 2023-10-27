package com.example.vinilos.network

import com.example.vinilos.model.Album
import retrofit2.http.GET

interface AlbumApiService {

    @GET("albums")
    suspend fun getAlbums(): List<Album>
}