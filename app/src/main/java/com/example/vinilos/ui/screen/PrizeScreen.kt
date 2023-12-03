package com.example.vinilos.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vinilos.R
import com.example.vinilos.ui.viewmodel.PrizeViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PrizeScreen(snackBarHostState: SnackbarHostState, modifier: Modifier = Modifier) {

    val prizeViewModel: PrizeViewModel = viewModel(factory = PrizeViewModel.Factory)

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier.padding(top = 70.dp, start = 10.dp)
    ) {
        Text(
            text = stringResource(id = R.string.new_award),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(bottom = 10.dp)
        )

        TextField(
            value = prizeViewModel.name,
            onValueChange = { prizeViewModel.name= it },
            label = { Text(stringResource(id = R.string.prize_name)) },
            singleLine = true,
            modifier = modifier.padding(top = 10.dp)
        )

        TextField(
            value = prizeViewModel.organization,
            onValueChange = { prizeViewModel.organization= it },
            label = { Text(stringResource(id = R.string.prize_org)) },
            singleLine = true,
            modifier = modifier.padding(top = 10.dp)
        )

        TextField(
            value = prizeViewModel.description,
            onValueChange = { prizeViewModel.description= it },
            label = { Text(stringResource(id = R.string.prize_des)) },
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
            Text(stringResource(id = R.string.prize_save))
        }
    }


}