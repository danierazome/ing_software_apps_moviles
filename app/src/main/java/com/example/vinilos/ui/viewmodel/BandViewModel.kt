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
import com.example.vinilos.data.repository.BandRepository
import com.example.vinilos.data.model.Band
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface BandUIState {
    data class Success(val bands: List<Band>): BandUIState
    object Loading: BandUIState
    object Error: BandUIState
}

class BandViewModel(private val bandRepository: BandRepository): ViewModel() {
    var bandUIState: BandUIState by mutableStateOf(BandUIState.Loading)
        private set

    init {
        getBands()
    }

    fun getBands(){
        viewModelScope.launch {
            bandUIState = BandUIState.Loading
            bandUIState = try {
                BandUIState.Success(bandRepository.getBands())
            } catch (e: IOException) {
                BandUIState.Error
            } catch (e: HttpException) {
                BandUIState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as VinylsApplication)
                val bandRepository = application.container.bandRepository
                BandViewModel(bandRepository = bandRepository)
            }
        }
    }

}

