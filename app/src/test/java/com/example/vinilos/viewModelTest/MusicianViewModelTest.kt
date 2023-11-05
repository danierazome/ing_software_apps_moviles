package com.example.vinilos.viewModelTest

import com.example.vinilos.musicianFakeData.FakeNetworkMusicianRepository
import com.example.vinilos.musicianFakeData.MusicianFakeData
import com.example.vinilos.rules.TestDispatcherRule
import com.example.vinilos.ui.viewmodel.MusicianUIState
import com.example.vinilos.ui.viewmodel.MusicianViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertEquals

class MusicianViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun getMusicians_verifyUiState_success() =
        runTest {
            val musicianViewModel = MusicianViewModel(
                musicianRepository = FakeNetworkMusicianRepository()
            )
            assertEquals(
                MusicianUIState.Success(MusicianFakeData.musiciansData),
                musicianViewModel.musicianUIState
            )
        }
}