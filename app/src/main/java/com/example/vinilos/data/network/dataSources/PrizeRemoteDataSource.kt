package com.example.vinilos.data.network.dataSources

import com.example.vinilos.data.network.apiServices.PrizeApiService
import com.example.vinilos.data.network.models.PrizeNetwork

class PrizeRemoteDataSource(private val prizeApiService: PrizeApiService) {

    suspend fun savePrize(prize: PrizeNetwork): PrizeNetwork {
        return prizeApiService.savePrize(prize=prize)
    }
}