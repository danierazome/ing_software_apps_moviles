package com.example.vinilos.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vinilos.R
import com.example.vinilos.data.model.musician.Album
import com.example.vinilos.data.model.musician.DetailedArtist
import com.example.vinilos.data.model.musician.Musician
import com.example.vinilos.ui.component.CroppedImage
import com.example.vinilos.ui.component.ErrorOnRetrieveData
import com.example.vinilos.ui.component.LoadingData
import com.example.vinilos.ui.component.TopBar
import com.example.vinilos.ui.viewmodel.DetailedArtistUIState
import com.example.vinilos.ui.viewmodel.DetailedArtistViewModel

@Composable
fun DetailedArtist(id: Int, navigateUp: () -> Unit = {}, modifier: Modifier = Modifier) {

    val detailedArtistViewModel: DetailedArtistViewModel = viewModel(factory = DetailedArtistViewModel.Factory)

    Log.d("Artist detailed painted", "painted")
    if (detailedArtistViewModel.artistId != id) {
        detailedArtistViewModel.updateDetailedArtistUiState(id)
        Log.d("Artist id is diferent", "is different")
    }


    var detailedArtistUiState = detailedArtistViewModel.detailedArtistUiState

    val topBarModifier = modifier.background(Color.Transparent)

    Scaffold(

        topBar = {
            TopBar(title = "", navigateUp = navigateUp, modifier = topBarModifier)
        }
    ) { innerPadding ->
        when (detailedArtistUiState) {
            is DetailedArtistUIState.Loading -> LoadingData()
            is DetailedArtistUIState.Error -> ErrorOnRetrieveData("Artista no disponible")
            is DetailedArtistUIState
                .Success -> DetailedArtistScreen(
                detailedArtist = detailedArtistUiState.artist, modifier = modifier.padding(innerPadding))
        }
    }

}

@Composable
fun DetailedArtistScreen(detailedArtist: Musician, modifier: Modifier = Modifier) {

    val imageModifier = Modifier
        .size(dimensionResource(R.dimen.image_large_size))
        .padding(dimensionResource(R.dimen.padding_small))
        .clip(RoundedCornerShape(50.dp))

    Box(
        modifier = Modifier
        .fillMaxWidth(1f)
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            item {
                CroppedImage(image = detailedArtist.image, modifier = imageModifier)
            }

            item {
                Text(text = detailedArtist.name, modifier = Modifier.padding(vertical = 10.dp), style = MaterialTheme.typography.titleLarge)
            }

            item {
                Text(text = detailedArtist.description, modifier = Modifier.padding(vertical = 10.dp), style = MaterialTheme.typography.bodyLarge)
            }

            item {
                val date = detailedArtist.birthDate.split('T')
                Text(text = date[0], modifier = Modifier.padding(vertical = 10.dp), style = MaterialTheme.typography.bodyLarge)
            }

            item {
                AlbumInfo(detailedArtist = detailedArtist.)
            }

        }
    }
}

@Composable
fun AlbumInfo(detailedArtist: DetailedArtist) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()

    ) {
        Text(text = "Albums", modifier = Modifier.padding(5.dp).testTag("name"), style = MaterialTheme.typography.titleMedium)
        //detailedArtist.albumes.forEach{AlbumItem(album = it)}
    }
}

@Composable
fun AlbumItem(album: Album) {
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(start = 15.dp, bottom = 5.dp, end = 15.dp)
        .padding(horizontal = 0.dp), horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(text = album.name, style = MaterialTheme.typography.bodySmall)
        Text(text = "${album.genre}", style = MaterialTheme.typography.bodySmall)
    }
}