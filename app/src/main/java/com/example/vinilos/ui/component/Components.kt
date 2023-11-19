package com.example.vinilos.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter


@Composable
fun CroppedImage(image: String, modifier: Modifier = Modifier) {
    AsyncImage(model = image, contentDescription = null, modifier = modifier, contentScale = ContentScale.Crop )
}

@Composable
fun LoadingData( modifier: Modifier = Modifier) {

    LinearProgressIndicator(
        modifier = Modifier.width(100.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        trackColor = MaterialTheme.colorScheme.secondary,
    )
}

@Composable
fun ErrorOnRetrieveData( message: String, modifier: Modifier = Modifier) {
    Text(text = message,  modifier = Modifier.width(64.dp))
}