package com.example.vinilos.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.vinilos.VinylsApplication
import com.example.vinilos.data.MusicianRepository
import com.example.vinilos.model.Musician
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface MusicianUIState {
    data class Success(val musicians: List<Musician>): MusicianUIState
    object Loading: MusicianUIState
    object Error: MusicianUIState
}

class MusicianViewModel(private val musicianRepository: MusicianRepository): ViewModel() {

    var musicianUIState: MusicianUIState by mutableStateOf(MusicianUIState.Loading)
        private set

    init {
        getMusicians()
    }

    fun getMusicians() {
        viewModelScope.launch {
            musicianUIState = MusicianUIState.Loading
            musicianUIState = try {
                MusicianUIState.Success(musicianRepository.getMusicians())
            } catch (e: IOException) {
                MusicianUIState.Error
            } catch (e: HttpException) {
                MusicianUIState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as VinylsApplication)
                val musicianRepository = application.container.musicianRepository
                MusicianViewModel(musicianRepository = musicianRepository)
            }
        }
    }
}