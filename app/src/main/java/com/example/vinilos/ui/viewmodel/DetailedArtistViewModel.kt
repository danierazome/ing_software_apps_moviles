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
import com.example.vinilos.data.network.models.network.ArtistNetwork
import com.example.vinilos.data.repository.MusicianRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface DetailedArtistUIState {
    data class Success(val artist: ArtistNetwork): DetailedArtistUIState
    object Loading: DetailedArtistUIState
    object Error: DetailedArtistUIState
}


class DetailedArtistViewModel(private val artistRepository: MusicianRepository): ViewModel() {

    var detailedArtistUiState: DetailedArtistUIState by mutableStateOf(DetailedArtistUIState.Loading)
        private set

    var artistId: Int = -1
        private set

    init {
        Log.d("INIT", "ON INIT")
    }

    fun updateDetailedArtistUiState(id: Int) {
        Log.d("UPDATE DETAILED Artist STATE", "UPDATING")
        this.artistId = id
        viewModelScope.launch {
            Log.d("ARTIST", "KICKING OFF")
            detailedArtistUiState = DetailedArtistUIState.Loading
            detailedArtistUiState = try {
                Log.d("ARTIST", "OK")
                DetailedArtistUIState.Success(artistRepository.getDetailedArtist(id))
            } catch (e: IOException) {
                Log.d("ARTIST", e.toString())
                DetailedArtistUIState.Error
            } catch (e: HttpException) {
                Log.d("ARTIST", e.toString())
                DetailedArtistUIState.Error
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