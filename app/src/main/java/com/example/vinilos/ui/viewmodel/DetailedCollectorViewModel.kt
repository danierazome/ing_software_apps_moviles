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
import com.example.vinilos.data.model.collector.DetailedCollector
import com.example.vinilos.data.repository.CollectorRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface DetailedCollectorUIState {
    data class Success(val collector: DetailedCollector): DetailedCollectorUIState
    object Loading: DetailedCollectorUIState
    object Error: DetailedCollectorUIState
}

class DetailedCollectorViewModel(private val collectorRepository: CollectorRepository): ViewModel() {
    var detailedCollectorUiState: DetailedCollectorUIState by mutableStateOf(DetailedCollectorUIState.Loading)
        private set

    var collectorId: Int = -1
        private set

    init {
        Log.d("DetailedCollectorView", "Initialized Collector ViewModel")
    }

    fun updateDetailedCollectorUiState(id: Int) {
        Log.d("UPDATE DETAILED COLLECTOR STATE", "UPDATING")
        this.collectorId = id
        viewModelScope.launch {
            Log.d("COLLECTOR", "KICKING OFF")
            detailedCollectorUiState = DetailedCollectorUIState.Loading
            detailedCollectorUiState = try {
                Log.d("COLLECTOR", "OK")
                DetailedCollectorUIState.Success(collectorRepository.getCollector(id))
            } catch (e: IOException) {
                Log.d("COLLECTOR", e.toString())
                DetailedCollectorUIState.Error
            } catch (e: HttpException) {
                Log.d("COLLECTOR", e.toString())
                DetailedCollectorUIState.Error
            }
        }
    }



    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as VinylsApplication)
                val collectorRepository = application.container.collectorRepository
                DetailedCollectorViewModel(collectorRepository = collectorRepository)
            }
        }
    }
}