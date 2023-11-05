package com.example.vinilos.viewModelTest

import com.example.vinilos.collectorFakeData.CollectorFakeData
import com.example.vinilos.collectorFakeData.FakeNetworkCollectorRepository
import com.example.vinilos.rules.TestDispatcherRule
import com.example.vinilos.ui.viewmodel.CollectorUIState
import com.example.vinilos.ui.viewmodel.CollectorViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertEquals

class CollectorViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun getCollectors_verifyUiState_success() =
        runTest {
            val collectorViewModel = CollectorViewModel(
                collectorRepository = FakeNetworkCollectorRepository()
            )
            assertEquals(
                CollectorUIState.Success(CollectorFakeData.collectorsData),
                collectorViewModel.collectorUiState
            )
        }
}