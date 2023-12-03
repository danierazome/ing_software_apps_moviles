package com.example.vinilos.data.network.apiServices

import com.example.vinilos.data.network.models.collectorNetwork.CollectorNetwork
import retrofit2.http.GET
import retrofit2.http.Path

interface CollectorApiService {

    @GET("collectors")
    suspend fun getCollectors(): List<CollectorNetwork>
    @GET("collectors/{id}")
    suspend fun getCollector(@Path("id") id: Int): CollectorNetwork
}