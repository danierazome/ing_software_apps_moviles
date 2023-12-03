package com.example.vinilos.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
import com.example.vinilos.data.model.collector.DetailedCollector
import com.example.vinilos.ui.component.ErrorOnRetrieveData
import com.example.vinilos.ui.component.LoadingData
import com.example.vinilos.ui.component.TopBar
import com.example.vinilos.ui.viewmodel.DetailedCollectorUIState
import com.example.vinilos.ui.viewmodel.DetailedCollectorViewModel


@Composable
fun DetailedCollector(id: Int, navigateUp: () -> Unit = {}, modifier: Modifier = Modifier) {

    val detailedCollectorViewModel: DetailedCollectorViewModel = viewModel(factory = DetailedCollectorViewModel.Factory)

    if (detailedCollectorViewModel.collectorId != id) {
        detailedCollectorViewModel.updateDetailedCollectorUiState(id)
    }

    var detailedCollectarUiState = detailedCollectorViewModel.detailedCollectorUiState

    val topBarModifier = modifier.background(Color.Transparent)

    Scaffold(
        topBar = {
            TopBar(title = "", navigateUp = navigateUp, modifier = topBarModifier)
        }
    ) { innerPadding ->
        when (detailedCollectarUiState) {
            is DetailedCollectorUIState.Loading -> LoadingData()
            is DetailedCollectorUIState.Error -> ErrorOnRetrieveData("Coleccionista no disponible")
            is DetailedCollectorUIState.Success -> DetailedCollectorScreen(
                detailedCollector = detailedCollectarUiState.collector, modifier = modifier.padding(innerPadding))
        }
    }
}


@Composable
fun DetailedCollectorScreen(detailedCollector: DetailedCollector, modifier: Modifier = Modifier) {

    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            item {
                Text(text = detailedCollector.name, modifier = Modifier.padding(vertical = 100.dp), style = MaterialTheme.typography.titleLarge)
                Text(text = detailedCollector.email, modifier = Modifier.padding(vertical = 10.dp), style = MaterialTheme.typography.bodyMedium)
                Text(text = detailedCollector.telephone, modifier = Modifier.padding(vertical = 10.dp), style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}