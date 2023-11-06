package com.example.vinilos.bandFakeData

import com.example.vinilos.model.Band
import com.example.vinilos.network.BandApiService

class FakeBandApiService: BandApiService {

    override suspend fun getBands(): List<Band> {
        return BandFakeData.bandsData
    }
}