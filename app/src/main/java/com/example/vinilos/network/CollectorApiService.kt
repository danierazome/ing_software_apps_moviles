package com.example.vinilos.network

import com.example.vinilos.model.Collector
import retrofit2.http.GET

interface CollectorApiService {

    @GET("collectors")
    suspend fun getCollectors(): List<Collector>
}