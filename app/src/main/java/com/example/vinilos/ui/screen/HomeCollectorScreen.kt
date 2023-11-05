package com.example.vinilos.ui.screen

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.vinilos.model.Album
import com.example.vinilos.model.Band
import com.example.vinilos.model.Collector
import com.example.vinilos.model.Musician
import com.example.vinilos.ui.component.CroppedImage
import com.example.vinilos.ui.component.ErrorOnRetrieveData
import com.example.vinilos.ui.component.LoadingData
import com.example.vinilos.ui.viewmodel.AlbumUIState
import com.example.vinilos.ui.viewmodel.AlbumViewModel
import com.example.vinilos.ui.viewmodel.BandUIState
import com.example.vinilos.ui.viewmodel.BandViewModel
import com.example.vinilos.ui.viewmodel.CollectorUIState
import com.example.vinilos.ui.viewmodel.CollectorViewModel
import com.example.vinilos.ui.viewmodel.MusicianUIState
import com.example.vinilos.ui.viewmodel.MusicianViewModel

@Composable
@Preview
fun HomeCollector( modifier: Modifier = Modifier) {
    val albumViewModel: AlbumViewModel =
        viewModel(factory = AlbumViewModel.Factory)

    val collectorViewModel: CollectorViewModel =
        viewModel(factory = CollectorViewModel.Factory)

    val musicianViewModel: MusicianViewModel =
        viewModel(factory = MusicianViewModel.Factory)

    val banViewModel: BandViewModel =
        viewModel(factory = BandViewModel.Factory)

    HomeScreenCollector(
        albumsUiState = albumViewModel.albumsUiState,
        collectorUIState = collectorViewModel.collectorUiState,
        musicianUIState = musicianViewModel.musicianUIState,
        bandUIState = banViewModel.bandUIState,
        randomAvatar = {collectorViewModel.randomAvatar()}
    )
}


@Composable
fun HomeScreenCollector(
    albumsUiState: AlbumUIState,
    collectorUIState: CollectorUIState,
    musicianUIState: MusicianUIState,
    bandUIState: BandUIState,
    randomAvatar: () -> String,
    modifier: Modifier = Modifier
) {

    val componentModifier = Modifier.padding(10.dp)

    LazyColumn {

        item {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = "¡Bienvenido Coleccionista!",
                style = MaterialTheme.typography.headlineSmall
            )
        }

        item {
            Image(
                painter = painterResource(R.drawable.welcome_collector),
                contentDescription = null,
                modifier = Modifier
                    .width(300.dp)
                    .height(200.dp)
            )
        }

        item {
            when (albumsUiState) {
                is AlbumUIState.Loading -> LoadingData()
                is AlbumUIState.Success -> AlbumsCollectorComponent(
                    albums = albumsUiState.albums, componentModifier)
                is AlbumUIState.Error -> ErrorOnRetrieveData("Albums no disponibles")
            }
        }

        item {
            when (musicianUIState) {
                is MusicianUIState.Loading -> LoadingData()
                is MusicianUIState.Success -> MusiciansCollectorComponent(
                    musicians = musicianUIState.musicians, componentModifier)
                is MusicianUIState.Error -> ErrorOnRetrieveData("Musicos no disponibles")
            }
        }

        item {
            when (collectorUIState) {
                is CollectorUIState.Loading -> LoadingData()
                is CollectorUIState.Success -> CollectorCollectorComponent(
                    collectors = collectorUIState.collectors,
                    randomAvatar = randomAvatar)
                is CollectorUIState.Error -> ErrorOnRetrieveData("Coleccionistas no disponibles")
            }
        }

        item {
            when(bandUIState){
                is BandUIState.Loading -> LoadingData()
                is BandUIState.Success -> BandCollectorComponente(
                    bands = bandUIState.bands, componentModifier
                    )
                is BandUIState.Error -> ErrorOnRetrieveData("Bandas no disponibles")
            }
        }

    }
}

// ------------> ALBUM SECTION
@Composable
fun AlbumsCollectorComponent(albums: List<Album>, modifier: Modifier = Modifier) {
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
                AlbumCarouselCollectorItem(album = album)
            }
        }
    }
}

@Composable
fun AlbumCarouselCollectorItem(album: Album, modifier: Modifier = Modifier) {

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
fun MusiciansCollectorComponent(musicians: List<Musician>, modifier: Modifier = Modifier) {
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
                MusicianCarouselCollectorItem(musician = musician)
            }
        }
    }
}

@Composable
fun MusicianCarouselCollectorItem(musician: Musician, modifier: Modifier = Modifier) {

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
fun CollectorCollectorComponent(
    collectors: List<Collector>,
    randomAvatar: () -> kotlin.String,
    modifier: Modifier = Modifier) {

    Column (
        modifier = modifier
    ){
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = "Coleccionistas reconocidos",
            style = MaterialTheme.typography.titleMedium
        )
        LazyRow {
            items(collectors) { collector ->
                CollectorsCarouselCollectionItem(collector = collector, avatar = randomAvatar.invoke())
            }
        }
    }
}

@Composable
fun CollectorsCarouselCollectionItem(collector: Collector, avatar: String, modifier: Modifier = Modifier) {

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


@Composable
fun BandCollectorComponente (
    bands: List<Band>,
    modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = "Bandas del momento",
            style = MaterialTheme.typography.titleMedium
        )
        LazyRow {
            items(bands) { band ->
                BandCarouselCollectorItem(band = band, modifier = modifier)
            }
        }
    }
}


@Composable
fun BandCarouselCollectorItem(band: Band, modifier: Modifier){
    val imageModifier = modifier
        .size(dimensionResource(R.dimen.image_small_size))
        .padding(dimensionResource(R.dimen.padding_small))
        .clip(RoundedCornerShape(50.dp))

    Column {
        CroppedImage(image = band.image, modifier = imageModifier)
        Text(
            text = band.name,
            style = MaterialTheme.typography.labelSmall,
            overflow = TextOverflow.Visible,
            textAlign = TextAlign.Center,
            maxLines = 2,
            modifier = Modifier.width(100.dp)
        )
    }
}