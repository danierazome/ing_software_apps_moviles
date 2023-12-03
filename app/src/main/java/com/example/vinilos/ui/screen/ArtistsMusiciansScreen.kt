package com.example.vinilos.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vinilos.R
import com.example.vinilos.data.model.musician.Musician
import com.example.vinilos.ui.component.CroppedImage
import com.example.vinilos.ui.component.ErrorOnRetrieveData
import com.example.vinilos.ui.component.LoadingData
import com.example.vinilos.ui.enumIU.VinylsScreen
import com.example.vinilos.ui.viewmodel.MusicianUIState
import com.example.vinilos.ui.viewmodel.MusicianViewModel

@Composable
fun Artists(
    navigateTo: (String) -> Unit,
    modifier: Modifier = Modifier) {

    val musicianViewModel: MusicianViewModel = viewModel(factory = MusicianViewModel.Factory)
    val state = musicianViewModel.musicianUIState
    Column {
        Text(
            text = stringResource(id = R.string.my_artists),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(top = 70.dp, bottom = 20.dp)
        )
        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            when (state) {
                is MusicianUIState.Loading -> {
                    item {
                        LoadingData()
                    }
                }
                is MusicianUIState.Success -> {
                    state.musicians.forEach { musician ->
                        item {
                            MusicianItemGrid(artist = musician, navigateTo = navigateTo)
                        }
                    }
                }
                is MusicianUIState.Error -> {
                    item {
                        ErrorOnRetrieveData("Artistas no disponibles")
                    }
                }
            }
        }
    }
}

@Composable
fun MusicianItemGrid(
    artist: Musician,
    navigateTo: (String) -> Unit,
    modifier: Modifier = Modifier) {
    val imageModifier = Modifier
        .size(dimensionResource(R.dimen.image_small_size))
        .padding(dimensionResource(R.dimen.padding_small))
        .clip(RoundedCornerShape(25.dp))

    Column (modifier = modifier.clickable {
        navigateTo("${VinylsScreen.Artist.name}/${artist.id}")
    }){
        CroppedImage(image = artist.image, modifier = imageModifier)
        Text(
            text = artist.name,
            style = MaterialTheme.typography.labelSmall,
            overflow = TextOverflow.Visible,
            textAlign = TextAlign.Center,
            maxLines = 2,
            modifier = Modifier.width(100.dp)
        )
    }
}