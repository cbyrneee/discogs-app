package dev.cbyrne.discogs.feature.discover.view

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import dev.cbyrne.discogs.feature.discover.view.model.HomeViewModel
import dev.cbyrne.discogs.feature.discover.view.model.HomeViewState
import dev.cbyrne.discogs.ui.layout.CenteredColumn
import dev.cbyrne.discogs.ui.layout.DiscogsLayout

@Composable
fun HomeView(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state

    LaunchedEffect(viewModel) {
        viewModel.load()
    }

    DiscogsLayout(title = "Home") {
        when (state) {
            is HomeViewState.Loaded -> {
                val information = state.information

                Text(
                    style = MaterialTheme.typography.bodyLarge,
                    text = "Hello, ${information.name} (${information.username})"
                )
            }

            is HomeViewState.Loading -> {
                CenteredColumn {
                    CircularProgressIndicator()
                }
            }

            is HomeViewState.Error -> {
                Text(
                    style = MaterialTheme.typography.bodyLarge,
                    text = "Error: ${state.reason}"
                )
            }
        }
    }
}