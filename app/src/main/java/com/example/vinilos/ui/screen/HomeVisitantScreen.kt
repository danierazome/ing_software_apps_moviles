package com.example.vinilos.ui.screen


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vinilos.R
import com.example.vinilos.data.model.Collector
import com.example.vinilos.data.model.Musician
import com.example.vinilos.data.model.album.Album
import com.example.vinilos.ui.component.BottomBarVisitor
import com.example.vinilos.ui.component.CroppedImage
import com.example.vinilos.ui.component.ErrorOnRetrieveData
import com.example.vinilos.ui.component.LoadingData
import com.example.vinilos.ui.component.TopBar
import com.example.vinilos.ui.enumIU.VinylsScreen
import com.example.vinilos.ui.viewmodel.AlbumUIState
import com.example.vinilos.ui.viewmodel.AlbumViewModel
import com.example.vinilos.ui.viewmodel.CollectorUIState
import com.example.vinilos.ui.viewmodel.CollectorViewModel
import com.example.vinilos.ui.viewmodel.MusicianUIState
import com.example.vinilos.ui.viewmodel.MusicianViewModel

@Composable
@Preview
fun HomeVisitant(
    navigateTo: (String) -> Unit = {},
    navigateUp: () -> Unit = {},
    modifier: Modifier = Modifier) {
    var screenSelected by rememberSaveable { mutableStateOf(VinylsScreen.HomeVisitant.name) }
    var idObjectSelected by rememberSaveable { mutableIntStateOf(0) }

    val setIdObject: (Int) -> Unit = {idObjectSelected = it }
    val setScreenSelected: (String) -> Unit = {screenSelected = it }

    Log.d("HOME SCREEN SELECTE", screenSelected)

    val albumViewModel: AlbumViewModel =
        viewModel(factory = AlbumViewModel.Factory)

    val collectorViewModel: CollectorViewModel =
        viewModel(factory = CollectorViewModel.Factory)

    val musicianViewModel: MusicianViewModel =
        viewModel(factory = MusicianViewModel.Factory)

    Scaffold(
        topBar = {
                 TopBar(title = "¡Bienvenido Visitante!", navigateUp = navigateUp)
        },
        bottomBar = {
            BottomBarVisitor(
                selectedItem = screenSelected,
                onSelect = setScreenSelected
            )
        }
    ) { innerPadding ->
        when (screenSelected) {
            VinylsScreen.AlbumsVisitant.name -> Albums(
                navigateTo = navigateTo
            )
            VinylsScreen.CollectorsVisitant.name -> Collectors()
            VinylsScreen.ArtistsVisitant.name -> Artists(
                navigateTo = navigateTo
            )
            else -> HomeScreen(
                albumsUiState = albumViewModel.albumsUiState,
                collectorUIState = collectorViewModel.collectorUiState,
                musicianUIState = musicianViewModel.musicianUIState,
                randomAvatar = {collectorViewModel.randomAvatar()},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }


}


@Composable
fun HomeScreen(
    albumsUiState: AlbumUIState,
    collectorUIState: CollectorUIState,
    musicianUIState: MusicianUIState,
    randomAvatar: () -> String,
    modifier: Modifier = Modifier
) {

    val componentModifier = Modifier.padding(10.dp)

    LazyColumn {
        item {
            Image(
                painter = painterResource(R.drawable.welcome_visitants),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 50.dp)
                    .width(300.dp)
                    .height(200.dp)
            )
        }

        item {
            when (albumsUiState) {
                is AlbumUIState.Loading -> LoadingData()
                is AlbumUIState.Success -> AlbumsComponent(albums = albumsUiState.albums, componentModifier)
                is AlbumUIState.Error -> ErrorOnRetrieveData("Albums no disponibles")
            }
        }

        item {
            when (musicianUIState) {
                is MusicianUIState.Loading -> LoadingData()
                is MusicianUIState.Success -> MusiciansComponent(musicians = musicianUIState.musicians, componentModifier)
                is MusicianUIState.Error -> ErrorOnRetrieveData("Musicos no disponibles")
            }
        }

        item {
            when (collectorUIState) {
                is CollectorUIState.Loading -> LoadingData()
                is CollectorUIState.Success -> CollectorComponent(
                    collectors = collectorUIState.collectors,
                    randomAvatar = randomAvatar)
                is CollectorUIState.Error -> ErrorOnRetrieveData("Coleccionistas no disponibles")
            }
        }

    }
}

// ------------> ALBUM SECTION
@Composable
fun AlbumsComponent(albums: List<Album>, modifier: Modifier = Modifier) {
    Column (
        modifier = modifier
    ){
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = "Albums mas populares",
            style = MaterialTheme.typography.titleMedium
        )
        LazyRow {
            items(albums) { album ->
                AlbumCarouselItem(album = album)
            }
        }
    }
}

@Composable
fun AlbumCarouselItem(album: Album, modifier: Modifier = Modifier) {

    val imageModifier = Modifier
        .size(dimensionResource(R.dimen.image_small_size))
        .padding(dimensionResource(R.dimen.padding_small))
        .clip(RoundedCornerShape(25.dp))

    Column {
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

// ------------> MUSICIAN SECTION

@Composable
fun MusiciansComponent(musicians: List<Musician>, modifier: Modifier = Modifier) {
    Column (
        modifier = modifier
    ){
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = "Artistas en tendencia",
            style = MaterialTheme.typography.titleMedium
        )
        LazyRow {
            items(musicians) { musician ->
                MusicianCarouselItem(musician = musician)
            }
        }
    }
}

@Composable
fun MusicianCarouselItem(musician: Musician, modifier: Modifier = Modifier) {

    val imageModifier = Modifier
        .size(dimensionResource(R.dimen.image_small_size))
        .padding(dimensionResource(R.dimen.padding_small))
        .clip(RoundedCornerShape(50.dp))

    Column {
        CroppedImage(image = musician.image, modifier = imageModifier)
        Text(
            text = musician.name,
            style = MaterialTheme.typography.labelSmall,
            overflow = TextOverflow.Visible,
            textAlign = TextAlign.Center,
            maxLines = 2,
            modifier = Modifier.width(100.dp)
        )
    }
}

// ------------> MUSICIAN SECTION

@Composable
fun CollectorComponent(
    collectors: List<Collector>,
    randomAvatar: () -> kotlin.String,
    modifier: Modifier = Modifier) {

    Column (
        modifier = modifier.padding(bottom = 100.dp)
    ){
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = "Coleccionistas reconocidos",
            style = MaterialTheme.typography.titleMedium
        )
        LazyRow {
            items(collectors) { collector ->
                CollectorsCarouselItem(collector = collector, avatar = randomAvatar.invoke())
            }
        }
    }
}

@Composable
fun CollectorsCarouselItem(collector: Collector, avatar: String, modifier: Modifier = Modifier) {

    val imageModifier = Modifier
        .size(dimensionResource(R.dimen.image_small_size))
        .padding(dimensionResource(R.dimen.padding_small))
        .clip(RoundedCornerShape(25.dp))

    Column {
        CroppedImage(image = avatar, modifier = imageModifier)
        Text(
            text = collector.name,
            style = MaterialTheme.typography.labelSmall,
            overflow = TextOverflow.Visible,
            textAlign = TextAlign.Center,
            maxLines = 2,
            modifier = Modifier.width(100.dp)
        )
    }
}
