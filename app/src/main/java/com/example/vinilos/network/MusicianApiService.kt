package com.example.vinilos.network

import com.example.vinilos.model.Collector
import com.example.vinilos.model.Musician
import retrofit2.http.GET

interface MusicianApiService {

    @GET("musicians")
    suspend fun getMusicians(): List<Musician>
}