package com.example.vinilos.data.network.apiServices

import com.example.vinilos.data.network.models.PrizeNetwork
import retrofit2.http.Body
import retrofit2.http.POST

interface PrizeApiService {
    @POST("prizes")
    suspend fun savePrize(@Body prize: PrizeNetwork): PrizeNetwork
}