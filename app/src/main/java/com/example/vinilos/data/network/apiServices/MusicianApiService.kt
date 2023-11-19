package com.example.vinilos.data.network.apiServices

import com.example.vinilos.data.model.Collector
import com.example.vinilos.data.model.Musician
import retrofit2.http.GET

interface MusicianApiService {

    @GET("musicians")
    suspend fun getMusicians(): List<Musician>
}