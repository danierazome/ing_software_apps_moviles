package com.example.vinilos.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.vinilos.VinylsApplication
import com.example.vinilos.data.AlbumRepository
import com.example.vinilos.model.Album
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface AlbumUIState {
    data class Success(val albums: List<Album>): AlbumUIState
    object Loading: AlbumUIState
    object Error: AlbumUIState
}


class AlbumViewModel(private val albumRepository: AlbumRepository): ViewModel() {

    var albumsUiState: AlbumUIState by mutableStateOf(AlbumUIState.Loading)
        private set

    init {
        getAlbums()
    }


    fun getAlbums() {
        viewModelScope.launch {
            albumsUiState = AlbumUIState.Loading
            albumsUiState = try {
                AlbumUIState.Success(albumRepository.getAlbums())
            } catch (e: IOException) {
                AlbumUIState.Error
            } catch (e: HttpException) {
                AlbumUIState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as VinylsApplication)
                val albumRepository = application.container.albumRepository
                AlbumViewModel(albumRepository = albumRepository)
            }
        }
    }
}