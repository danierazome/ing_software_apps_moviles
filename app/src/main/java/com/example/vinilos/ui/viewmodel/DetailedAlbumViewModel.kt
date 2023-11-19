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
import com.example.vinilos.data.model.album.DetailedAlbum
import com.example.vinilos.data.repository.AlbumRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface DetailedAlbumUIState {
    data class Success(val album: DetailedAlbum): DetailedAlbumUIState
    object Loading: DetailedAlbumUIState
    object Error: DetailedAlbumUIState
}


class DetailedAlbumViewModel(private val albumRepository: AlbumRepository): ViewModel() {

    var detailedAlbumUiState: DetailedAlbumUIState by mutableStateOf(DetailedAlbumUIState.Loading)
        private set

    var albumId: Int = -1
        private set

    init {
        Log.d("ON INIT ZORRO", "ON INIIIT MI PERRO")
    }

    fun updateDetailedAlbumUiState(id: Int) {
        Log.d("UPDATE DETAILED ALBUM STATE", "UPDATING")
        this.albumId = id
        viewModelScope.launch {
            Log.d("ALBUM", "KICKING OFF")
            detailedAlbumUiState = DetailedAlbumUIState.Loading
            detailedAlbumUiState = try {
                Log.d("ALBUM", "OK")
                DetailedAlbumUIState.Success(albumRepository.getDetailedAlbum(id))
            } catch (e: IOException) {
                Log.d("ALBUM", e.toString())
                DetailedAlbumUIState.Error
            } catch (e: HttpException) {
                Log.d("ALBUM", e.toString())
                DetailedAlbumUIState.Error
            }
        }
    }



    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as VinylsApplication)
                val albumRepository = application.container.albumRepository
                DetailedAlbumViewModel(albumRepository = albumRepository)
            }
        }
    }
}