package com.example.vinilos.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vinilos.R
import com.example.vinilos.data.model.Prize
import com.example.vinilos.ui.enumIU.VinylsScreen
import com.example.vinilos.ui.viewmodel.AlbumUIState
import com.example.vinilos.ui.viewmodel.DetailedAlbumViewModel
import com.example.vinilos.ui.viewmodel.PrizeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PrizeScreen(snackBarHostState: SnackbarHostState, modifier: Modifier = Modifier) {

    val prizeViewModel: PrizeViewModel = viewModel(factory = PrizeViewModel.Factory)

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier.padding(top = 70.dp, start = 10.dp)
    ) {
        Text(
            text = "Nuevo Premio",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(bottom = 10.dp)
        )

        TextField(
            value = prizeViewModel.name,
            onValueChange = { prizeViewModel.name= it },
            label = { Text("Nombre") },
            singleLine = true,
            modifier = modifier.padding(top = 10.dp)
        )

        TextField(
            value = prizeViewModel.organization,
            onValueChange = { prizeViewModel.organization= it },
            label = { Text("Organización") },
            singleLine = true,
            modifier = modifier.padding(top = 10.dp)
        )

        TextField(
            value = prizeViewModel.description,
            onValueChange = { prizeViewModel.description= it },
            label = { Text("Descripción") },
            singleLine = false,
            modifier = modifier.padding(top = 10.dp)
        )

        Button(
            onClick =
            {
                keyboardController?.hide()
                prizeViewModel.savePrize(snackBarHostState = snackBarHostState)
            },
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 20.dp)
        ) {
            Text("Guardar")
        }
    }


}