package com.example.vinilos.network

import com.example.vinilos.model.Band
import retrofit2.http.GET

interface BandApiService {

    @GET("bands")
    suspend fun getBands(): List<Band>
}