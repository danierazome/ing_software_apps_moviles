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
import com.example.vinilos.data.model.album.Album
import com.example.vinilos.ui.component.CroppedImage
import com.example.vinilos.ui.component.ErrorOnRetrieveData
import com.example.vinilos.ui.component.LoadingData
import com.example.vinilos.ui.enumIU.VinylsScreen
import com.example.vinilos.ui.viewmodel.AlbumUIState
import com.example.vinilos.ui.viewmodel.AlbumViewModel

@Composable
fun Albums(
    navigateTo: (String) -> Unit,
    modifier: Modifier = Modifier) {

    val albumViewModel: AlbumViewModel = viewModel(factory = AlbumViewModel.Factory)
    val state = albumViewModel.albumsUiState

    Column {
        Text(
            text = stringResource(id = R.string.my_albums),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(top = 70.dp, bottom = 20.dp)
        )
        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            when (state) {
                is AlbumUIState.Loading -> {
                    item {
                        LoadingData()
                    }
                }
                is AlbumUIState.Success -> {
                    state.albums.forEach { album ->
                        item {
                            AlbumItemGrid(album = album, navigateTo = navigateTo)
                        }
                    }
                }
                is AlbumUIState.Error -> {
                    item {
                        ErrorOnRetrieveData("Albums no disponibles")
                    }
                }
            }
        }
    }
}

@Composable
fun AlbumItemGrid(
    album: Album,
    navigateTo: (String) -> Unit,
    modifier: Modifier = Modifier) {
    val imageModifier = Modifier
        .size(dimensionResource(R.dimen.image_small_size))
        .padding(dimensionResource(R.dimen.padding_small))
        .clip(RoundedCornerShape(25.dp))

    Column (modifier = modifier.clickable {
        navigateTo("${VinylsScreen.Album.name}/${album.id}")
    }){
        CroppedImage(image = album.cover, modifier = imageModifier)
        Text(
            text = album.name,
            style = MaterialTheme.typography.labelSmall,
            overflow = TextOverflow.Visible,
            textAlign = TextAlign.Center,
            maxLines = 2,
            modifier = Modifier.width(100.dp)
        )
    }
}