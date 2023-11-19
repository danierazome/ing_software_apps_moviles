package com.example.vinilos.data.network.apiServices

import com.example.vinilos.data.model.Band
import retrofit2.http.GET

interface BandApiService {

    @GET("bands")
    suspend fun getBands(): List<Band>
}