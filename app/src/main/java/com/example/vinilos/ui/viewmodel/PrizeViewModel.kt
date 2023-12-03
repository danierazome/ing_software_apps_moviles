package com.example.vinilos.ui.viewmodel

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.vinilos.VinylsApplication
import com.example.vinilos.data.model.Prize
import com.example.vinilos.data.repository.PrizeRepository
import com.example.vinilos.ui.utils.validateInput
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException



class PrizeViewModel(private val prizeRepository: PrizeRepository): ViewModel() {

    var name: String by mutableStateOf("")
    var description: String by mutableStateOf("")
    var organization: String by mutableStateOf("")


    fun savePrize(snackBarHostState: SnackbarHostState) {

        viewModelScope.launch {
            val (areValid, message) = validateInputs()
            if (!areValid) {
                snackBarHostState.showSnackbar(
                    message = message)
            }else {
                try {
                    prizeRepository.savePrize(
                        prize= Prize(
                            name= name,
                            organization = organization,
                            description = description
                        )
                    )
                    snackBarHostState.showSnackbar(
                        message = "Premio guardado exitozamente")
                } catch (e: IOException) {
                    snackBarHostState.showSnackbar(
                        message = "Error al guardar el premio")
                } catch (e: HttpException) {
                    snackBarHostState.showSnackbar(
                        message = "Error en el servidor")
                }
            }
        }
    }

    private fun validateInputs(): Pair<Boolean, String> {
        if (!validateInput(20, 5, name)) return false to "5 Caracteres mínimo, 20 máximo para el nombre"
        if (!validateInput(100, 5, description)) return false to "5 Caracteres mínimo, 100 máximo para la descripción"
        if (!validateInput(20, 5, organization)) return false to "5 Caracteres mínimo, 20 máximo para la organización"
        return true to ""
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as VinylsApplication)
                val prizeRepository = application.container.prizeRepository
                PrizeViewModel(prizeRepository = prizeRepository)
            }
        }
    }
}