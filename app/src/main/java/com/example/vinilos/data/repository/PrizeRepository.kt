package com.example.vinilos.data.repository

import com.example.vinilos.data.mappers.asNetworkModel
import com.example.vinilos.data.model.Prize
import com.example.vinilos.data.network.dataSources.PrizeRemoteDataSource

interface IPrizeRepository {
    suspend fun savePrize(prize: Prize)
}

class PrizeRepository(private val prizeRemoteDataSource: PrizeRemoteDataSource): IPrizeRepository {

    override suspend fun savePrize(prize: Prize) {
        prizeRemoteDataSource.savePrize(prize=prize.asNetworkModel())
    }

}