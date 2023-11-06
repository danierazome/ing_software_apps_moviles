package com.example.vinilos.bandFakeData

import com.example.vinilos.data.BandRepository
import com.example.vinilos.model.Band

class FakeNetworkBandRepository: BandRepository {
    override suspend fun getBands(): List<Band> {
        return BandFakeData.bandsData
    }
}