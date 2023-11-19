package com.example.vinilos.bandFakeData

import com.example.vinilos.data.model.Band
import com.example.vinilos.data.network.apiServices.BandApiService

class FakeBandApiService: BandApiService {

    override suspend fun getBands(): List<Band> {
        return BandFakeData.bandsData
    }
}