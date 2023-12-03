package com.example.vinilos.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vinilos.R
import com.example.vinilos.ui.component.ErrorOnRetrieveData
import com.example.vinilos.ui.component.LoadingData
import com.example.vinilos.ui.viewmodel.CollectorUIState
import com.example.vinilos.ui.viewmodel.CollectorViewModel

@Composable
fun Collectors(modifier: Modifier = Modifier,
               navigateTo: (String) -> Unit,) {
    val collectorViewModel: CollectorViewModel =
        viewModel(factory = CollectorViewModel.Factory)

    val state = collectorViewModel.collectorUiState

    Column {
        Text(
            text = stringResource(id = R.string.collectors_on_trend),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(top = 70.dp, bottom = 20.dp)
        )
        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            when (state) {
                is CollectorUIState.Loading -> {
                    item {
                        LoadingData()
                    }
                }
                is CollectorUIState.Success -> {
                    val collectors = state.collectors
                    val sortedCollectors = collectors.sortedByDescending { it.comments?.size }
                    sortedCollectors.take(3).toTypedArray().forEach { collector ->
                        item {
                            CollectorsCarouselItem(collector = collector, avatar = collectorViewModel.randomAvatar(), navigateTo = navigateTo)
                        }
                    }
                }
                is CollectorUIState.Error -> {
                    item {
                        ErrorOnRetrieveData("Collectors no disponibles")
                    }
                }
            }
        }

        Text(
            text = stringResource(id = R.string.collectors),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(top = 70.dp, bottom = 20.dp)
        )

        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            when (state) {
                is CollectorUIState.Loading -> {
                    item {
                        LoadingData()
                    }
                }
                is CollectorUIState.Success -> {
                    val collectors = state.collectors
                    val sortedCollectors = collectors.sortedByDescending { it.comments?.size }
                    sortedCollectors.drop(3).toTypedArray().forEach { collector ->
                        item {
                            CollectorsCarouselItem(collector = collector, avatar = collectorViewModel.randomAvatar(), navigateTo = navigateTo)
                        }
                    }
                }
                is CollectorUIState.Error -> {
                    item {
                        ErrorOnRetrieveData("Collectors no disponibles")
                    }
                }
            }
        }
    }
}