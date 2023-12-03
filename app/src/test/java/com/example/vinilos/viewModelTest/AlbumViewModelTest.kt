package com.example.vinilos.viewModelTest

import com.example.vinilos.albumFakeData.AlbumFakeData
import com.example.vinilos.albumFakeData.FakeAlbumRepository
import com.example.vinilos.rules.TestDispatcherRule
import com.example.vinilos.ui.viewmodel.AlbumUIState
import com.example.vinilos.ui.viewmodel.AlbumViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertEquals

class AlbumViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun getAlbums_verifyUiState_success() =
        runTest {
            val albumViewModel = AlbumViewModel(
                albumRepository = FakeAlbumRepository()
            )
            assertEquals(
                AlbumUIState.Success(AlbumFakeData.albumsData),
                albumViewModel.albumsUiState
            )
        }
}