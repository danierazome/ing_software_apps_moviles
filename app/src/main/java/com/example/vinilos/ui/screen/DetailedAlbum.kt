package com.example.vinilos.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vinilos.R
import com.example.vinilos.data.model.album.DetailedAlbum
import com.example.vinilos.ui.component.CroppedImage
import com.example.vinilos.ui.component.ErrorOnRetrieveData
import com.example.vinilos.ui.component.LoadingData
import com.example.vinilos.ui.component.TopBar
import com.example.vinilos.ui.viewmodel.DetailedAlbumUIState
import com.example.vinilos.ui.viewmodel.DetailedAlbumViewModel

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.testTag
import com.example.vinilos.data.model.album.Comment
import com.example.vinilos.data.model.album.Track

import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import com.example.vinilos.ui.component.AddTrackSuccessfully

@Composable
fun DetailedAlbum(id: Int, navigateUp: () -> Unit = {}, modifier: Modifier = Modifier) {

    val detailedAlbumViewModel: DetailedAlbumViewModel = viewModel(factory = DetailedAlbumViewModel.Factory)

    if (detailedAlbumViewModel.albumId != id) {
        detailedAlbumViewModel.updateDetailedAlbumUiState(id)
    }

    var detailedAlbumUiState = detailedAlbumViewModel.detailedAlbumUiState

    val topBarModifier = modifier.background(Color.Transparent)

    Scaffold(

        topBar = {
            TopBar(title = "", navigateUp = navigateUp, modifier = topBarModifier)
        }
    ) { innerPadding ->
        when (detailedAlbumUiState) {
            is DetailedAlbumUIState.Loading -> LoadingData()
            is DetailedAlbumUIState.Error -> ErrorOnRetrieveData("Album no disponible")
            is DetailedAlbumUIState.Success -> DetailedAlbumScreen(
                detailedAlbum = detailedAlbumUiState.album, modifier = modifier.padding(innerPadding))
            is DetailedAlbumUIState.SuccessAddTrack -> AddTrackSuccessfully("La canción se agregó exitosamente.")
        }
    }

}

@Composable
fun DetailedAlbumScreen(detailedAlbum: DetailedAlbum, modifier: Modifier = Modifier) {

    val imageModifier = Modifier
        .size(dimensionResource(R.dimen.image_large_size))
        .padding(dimensionResource(R.dimen.padding_small))
        .clip(RoundedCornerShape(50.dp))

    val showDialog = remember { mutableStateOf(false) }
    val detailedAlbumViewModel: DetailedAlbumViewModel = viewModel(factory = DetailedAlbumViewModel.Factory)


    Box(
        modifier = Modifier
        .fillMaxWidth(1f)
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            item {
                CroppedImage(image = detailedAlbum.cover, modifier = imageModifier)
            }

            item {
                Text(text = detailedAlbum.name, modifier = Modifier.padding(vertical = 10.dp), style = MaterialTheme.typography.titleLarge)
            }

            item {
                AlbumTracks(detailedAlbum = detailedAlbum)
            }

            item {
                AlbumComments(detailedAlbum = detailedAlbum)
            }

            item {
                FloatingActionButton(
                    onClick = { showDialog.value = true },
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomEnd)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Track")
                }
            }
            }
        if (showDialog.value) {
            AddTrackDialog(
                onDismiss = { showDialog.value = false },
                onAddTrack = { trackName, trackDuration ->
                    detailedAlbumViewModel.addTrackAlbum(detailedAlbum.id, trackName, trackDuration)
                    println("Track Name: $trackName, Duration: $trackDuration")
                    showDialog.value = false
                }
            )
        }
        }
    }


// -------------> TRACK FRAGMENT
@Composable
fun AlbumTracks(detailedAlbum: DetailedAlbum) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()

    ) {
        Text(text = "Tracks", modifier = Modifier
            .padding(5.dp)
            .testTag("Tracks"), style = MaterialTheme.typography.titleMedium)
        detailedAlbum.tracks.forEach{TrackItem(track = it)}
    }
}

@Composable
fun TrackItem(track: Track) {
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(start = 15.dp, bottom = 5.dp, end = 15.dp)
        .padding(horizontal = 0.dp), horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(text = track.name, style = MaterialTheme.typography.bodySmall)
        Text(text = "${track.duration}", style = MaterialTheme.typography.bodySmall)
    }
}


// -------------> COMMENT FRAGMENT

@Composable
fun AlbumComments(detailedAlbum: DetailedAlbum) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()

    ) {
        Text(
            text = "Comments",
            modifier = Modifier.padding(start = 5.dp, bottom = 10.dp),
            style = MaterialTheme.typography.titleMedium
        )
        detailedAlbum.comments.forEach{CommentItem(comment = it) }
    }
}

@Composable
fun CommentItem(comment: Comment) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, bottom = 20.dp, end = 15.dp),
        horizontalAlignment = Alignment.Start
    ){
        CommentRating(rating = comment.rating)
        Text(text = "${comment.description}", style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun CommentRating(rating: Int){
    val isStarRated: (Int, Int) -> Boolean = {i: Int, r: Int -> r >= (i+1)}
    Row {
        repeat(5) {i -> RatingStar(isStarRated = isStarRated(i, rating))}
    }
}

@Composable
fun RatingStar(isStarRated: Boolean) {
    if (isStarRated){
        Icon(
            modifier = Modifier.size(15.dp),
            imageVector = Icons.Outlined.Star, 
            contentDescription = "RATED", 
            tint = MaterialTheme.colorScheme.secondary)
    }else {
        Icon(
            modifier = Modifier.size(15.dp),
            imageVector = Icons.Outlined.Star, 
            contentDescription = "NO RATED", )
    }
}

@Composable
fun AddTrackDialog(
    onDismiss: () -> Unit,
    onAddTrack: (trackName: String, trackDuration: String) -> Unit
) {
    var trackName by remember { mutableStateOf(TextFieldValue()) }
    var trackDuration by remember { mutableStateOf(TextFieldValue()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Track") },
        confirmButton = {
            TextButton(
                onClick = {
                    val name = trackName.text
                    val duration = trackDuration.text
                    onAddTrack(name, duration)
                    onDismiss()
                }
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        text = {
            Column {
                OutlinedTextField(
                    value = trackName,
                    onValueChange = { trackName = it },
                    label = { Text("Track Name") },
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = trackDuration,
                    onValueChange = { trackDuration = it },
                    label = { Text("Track Duration") }
                )
            }
        }
    )
}
