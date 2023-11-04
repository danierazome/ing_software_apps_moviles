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
import com.example.vinilos.data.CollectorRepository
import com.example.vinilos.model.Collector
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface CollectorUIState {
    data class Success(val collectors: List<Collector>): CollectorUIState
    object Loading: CollectorUIState
    object Error: CollectorUIState
}

class CollectorViewModel(private val collectorRepository: CollectorRepository): ViewModel() {

    var collectorUiState: CollectorUIState by mutableStateOf(CollectorUIState.Loading)
        private set

    private val avatars = listOf(
        "https://static.vecteezy.com/system/resources/previews/002/002/297/non_2x/beautiful-woman-avatar-character-icon-free-vector.jpg",
        "https://icons.iconarchive.com/icons/iconarchive/incognito-animals/512/Bear-Avatar-icon.png",
        "https://cdn.icon-icons.com/icons2/2438/PNG/512/boy_avatar_icon_148455.png",
        "https://cdn4.iconfinder.com/data/icons/avatars-xmas-giveaway/128/batman_hero_avatar_comics-512.png"
    )

    init {
        getCollectors()
    }

    fun getCollectors() {
        viewModelScope.launch {
            collectorUiState = CollectorUIState.Loading
            collectorUiState = try {
                CollectorUIState.Success(collectorRepository.getCollectors())
            } catch (e: IOException) {
                CollectorUIState.Error
            } catch (e: HttpException) {
                CollectorUIState.Error
            }
        }
    }

    fun randomAvatar(): String {
        return avatars.random()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as VinylsApplication)
                val collectorRepository = application.container.collectorRepository
                CollectorViewModel(collectorRepository = collectorRepository)
            }
        }
    }

}