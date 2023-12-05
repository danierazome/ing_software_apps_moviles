package com.example.vinilos.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vinilos.R
import com.example.vinilos.ui.viewmodel.NewAlbumViewModel

@Composable
@OptIn(ExperimentalComposeUiApi::class)
fun NewAlbum(snackBarHostState: SnackbarHostState, modifier: Modifier = Modifier) {

    val newAlbumViewModel: NewAlbumViewModel = viewModel(factory = NewAlbumViewModel.Factory)

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier.padding(top = 70.dp, start = 10.dp)
    ) {
        Text(
            text = stringResource(id = R.string.new_album),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(bottom = 10.dp)
        )

        TextField(
            value = newAlbumViewModel.name,
            onValueChange = { newAlbumViewModel.name= it },
            label = { Text(stringResource(id = R.string.album_name)) },
            singleLine = false,
            modifier = modifier
                .padding(top = 10.dp)
        )

        TextField(
            value = newAlbumViewModel.cover,
            onValueChange = { newAlbumViewModel.cover= it },
            label = { Text(stringResource(id =  R.string.album_cover)) },
            singleLine = false,
            modifier = modifier
                .padding(top = 10.dp)
        )

        TextField(
            value = newAlbumViewModel.description,
            onValueChange = { newAlbumViewModel.description= it },
            label = { Text(stringResource(id = R.string.album_description)) },
            singleLine = false,
            modifier = modifier
                .padding(top = 10.dp)
        )
        TextField(
            value = newAlbumViewModel.releaseDate,
            onValueChange = {
                if (it.length < 11)
                    newAlbumViewModel.releaseDate = it
                if (it.length % 2 == 0 && it.length == 2) {
                    newAlbumViewModel.releaseDate = newAlbumViewModel.releaseDate+ "/"
                }
                if (it.length % 4 == 0 && it.length == 4)
                    newAlbumViewModel.releaseDate = newAlbumViewModel.releaseDate+ "/"
            } ,
            label = { Text(stringResource(id = R.string.album_releaseDate)) },
            placeholder = { Text(("DD/MM/YYYY")) },
            singleLine = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        TextField(
            value = newAlbumViewModel.recordLabel,
            onValueChange = { newAlbumViewModel.recordLabel= it },
            label = { Text(stringResource(id = R.string.album_recordLabel)) },
            singleLine = false,
            modifier = modifier
                .padding(top = 10.dp)
        )
        TextField(
            value = newAlbumViewModel.genre,
            onValueChange = { newAlbumViewModel.genre= it },
            label = { Text(stringResource(id = R.string.album_genre)) },
            singleLine = false,
            modifier = modifier
                .padding(top = 10.dp)
        )

        Button(
            onClick =
            {
                keyboardController?.hide()
                newAlbumViewModel.saveAlbum(snackBarHostState = snackBarHostState)
            },
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 20.dp)
        ) {
            Text(stringResource(id = R.string.prize_save))
        }
    }
}