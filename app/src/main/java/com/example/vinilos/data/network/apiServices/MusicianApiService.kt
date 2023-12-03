package com.example.vinilos.data.network.apiServices

import com.example.vinilos.data.model.musician.Musician
import retrofit2.http.GET
import retrofit2.http.Path

interface MusicianApiService {

    @GET("musicians")
    suspend fun getMusicians(): List<Musician>

    @GET("musicians/{id}")
    suspend fun getDetailedArtist(@Path("id") id: Int): Musician
}