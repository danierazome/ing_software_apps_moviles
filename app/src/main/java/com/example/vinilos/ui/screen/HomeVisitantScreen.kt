package com.example.vinilos.ui.screen


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vinilos.model.Album
import com.example.vinilos.ui.viewmodel.AlbumUIState
import com.example.vinilos.ui.viewmodel.AlbumViewModel

@Composable
@Preview
fun HomeVisitant( modifier: Modifier = Modifier) {
    val albumViewModel: AlbumViewModel =
        viewModel(factory = AlbumViewModel.Factory)
    
    AlbumCarrousel(albumsUiState = albumViewModel.albumsUiState)
}

@Composable
fun AlbumCarrousel( albumsUiState: AlbumUIState, modifier: Modifier = Modifier) {
    when (albumsUiState) {
        is AlbumUIState.Loading -> Loading()
        is AlbumUIState.Success -> AlbumsOk(albums = albumsUiState.albums)
        is AlbumUIState.Error -> ErrorAlbum()
    }
}

@Composable
fun Loading( modifier: Modifier = Modifier) {
    Text(text = "Loading")
}

@Composable
fun ErrorAlbum( modifier: Modifier = Modifier) {
    Text(text = "ErrorAlbum")
}

@Composable
fun AlbumsOk(albums: List<Album>, modifier: Modifier = Modifier) {
    Text(text = albums.toString())
}
