package com.example.vinilos.bandFakeData

import com.example.vinilos.data.repository.BandRepository
import com.example.vinilos.data.model.Band

class FakeNetworkBandRepository: BandRepository {
    override suspend fun getBands(): List<Band> {
        return BandFakeData.bandsData
    }
}