package com.example.vinilos.ui.viewmodel

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.vinilos.VinylsApplication
import com.example.vinilos.data.repository.AlbumRepository
import java.util.Calendar
import androidx.lifecycle.viewModelScope
import com.example.vinilos.data.model.album.Album
import com.example.vinilos.data.model.album.Comment
import com.example.vinilos.data.model.album.Track
import com.example.vinilos.ui.utils.validateInput
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


class NewAlbumViewModel(private val albumRepository: AlbumRepository): ViewModel() {
    var name: String by mutableStateOf("")
    var cover: String by mutableStateOf("")
    var description: String by mutableStateOf("")
    var releaseDate: String by mutableStateOf("")
    var genre: String by mutableStateOf("")
    var recordLabel: String by mutableStateOf("")
    var tracks: List<Track> by mutableStateOf(listOf<Track>())
    var comments: List<Comment> by mutableStateOf(listOf<Comment>())
    val calendar = Calendar.getInstance()


    fun searchCalendar(snackBarHostState: SnackbarHostState){
        println("Vinilos - searchCalendar")
       // val datPicker = DatePickerFragment{day, month, year -> onDateSelected(day, month, year)}
//        datPicker.show((MainActivity).getSupportFragmentManager().beginTransaction(), "")
        /*
        val datePickerDialog = DatePickerDialog(
            this, { DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                // Create a new Calendar instance to hold the selected date
                val selectedDate = Calendar.getInstance()
                // Set the selected date using the values received from the DatePicker dialog
                selectedDate.set(year, monthOfYear, dayOfMonth)
                // Create a SimpleDateFormat to format the date as "dd/MM/yyyy"
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                // Format the selected date into a string
                val formattedDate = dateFormat.format(selectedDate.time)
                // Update the TextView to display the selected date with the "Selected Date: " prefix
                //tvSelectedDate.text = "Selected Date: $formattedDate"
                println("Selected Date: $formattedDate")
            },
            MainActivity.calendar.get(Calendar.YEAR),
            MainActivity.calendar.get(Calendar.MONTH),
            MainActivity.calendar.get(Calendar.DAY_OF_MONTH)
        )
        // Show the DatePicker dialog
        datePickerDialog.show()*/
    }

    fun saveAlbum(snackBarHostState: SnackbarHostState) {

        viewModelScope.launch {
            val (areValid, message) = validateInputs()
            println("vinilos - "+areValid)
            println("vinilos - "+message)
            if (!areValid) {
                snackBarHostState.showSnackbar(
                    message = message)
            }else {
                try {
                    println("vinilos - albumRepository")
                    albumRepository.saveAlbum(
                        album = Album(
                            id = 0,
                            name= name,
                            cover = cover,
                            description = description,
                            releaseDate = releaseDate,
                            genre = genre,
                            recordLabel = recordLabel,
                            tracks = tracks,
                            comments = comments,
                            )
                    )
                    snackBarHostState.showSnackbar(
                        message = "Album guardado exitosamente")
                } catch (e: IOException) {
                    snackBarHostState.showSnackbar(
                        message = "Error al guardar el album")
                } catch (e: HttpException) {
                    snackBarHostState.showSnackbar(
                        message = "Error en el servidor")
                }
            }
        }
    }
    private fun validateInputs(): Pair<Boolean, String> {
        if (!validateInput(20, 5, name)) return false to "5 Caracteres mínimo, 20 máximo para el nombre"
        if (!validateInput(200, 5, cover)) return false to "5 Caracteres mínimo, 200 máximo para la imagen"
        if (!validateInput(200, 5, description)) return false to " Caracteres mínimo, 200 máximo para la descripción"
        if (!validateInput(10, 10, releaseDate)) return false to "10 Caracteres mínimo, 10 máximo para la fecha"
        if (!validateInput(100, 5, genre)) return false to "5 Caracteres mínimo, 100 máximo para la género"
        if (!validateInput(100, 5, recordLabel)) return false to "5 Caracteres mínimo, 100 máximo para la record"
        return true to ""
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as VinylsApplication)
                val albumRepository = application.container.albumRepository
                NewAlbumViewModel(albumRepository = albumRepository)
            }
        }
    }
}



