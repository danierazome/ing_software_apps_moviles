package com.example.vinilos.data.network.apiServices

import com.example.vinilos.data.network.models.network.AlbumNetwork
import com.example.vinilos.data.network.models.network.AddTrackRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AlbumApiService {

    @GET("albums")
    suspend fun getAlbums(): List<AlbumNetwork>

    @GET("albums/{id}")
    suspend fun getDetailedAlbum(@Path("id") id: Int): AlbumNetwork

    @POST("albums")
    suspend fun saveAlbum(@Body album: AlbumNetwork) : AlbumNetwork
    
    @POST("albums/{id}/tracks")
    suspend fun addTrackAlbum(
        @Path("id") id: Int,
        @Body request: AddTrackRequest
    ): AddTrackRequest
}