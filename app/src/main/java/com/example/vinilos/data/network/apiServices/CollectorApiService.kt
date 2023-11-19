package com.example.vinilos.data.network.apiServices

import com.example.vinilos.data.model.Collector
import retrofit2.http.GET

interface CollectorApiService {

    @GET("collectors")
    suspend fun getCollectors(): List<Collector>
}