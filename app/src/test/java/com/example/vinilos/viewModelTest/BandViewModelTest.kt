package com.example.vinilos.viewModelTest

import com.example.vinilos.bandFakeData.BandFakeData
import com.example.vinilos.bandFakeData.FakeNetworkBandRepository
import com.example.vinilos.rules.TestDispatcherRule
import com.example.vinilos.ui.viewmodel.BandUIState
import com.example.vinilos.ui.viewmodel.BandViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class BandViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun getBands_verifyUiState_success() =
        runTest {
            val bandViewModel = BandViewModel(
                bandRepository = FakeNetworkBandRepository()
            )
            Assert.assertEquals(
                BandUIState.Success(BandFakeData.bandsData),
                bandViewModel.bandUIState
            )
        }
}