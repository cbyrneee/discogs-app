package dev.cbyrne.discogs.feature.discover.view

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import dev.cbyrne.discogs.feature.discover.view.model.HomeViewModel
import dev.cbyrne.discogs.ui.layout.DiscogsLayout

@Composable
fun HomeView(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val (id, username) = viewModel.identity

    DiscogsLayout(title = "Home") {
        Text(
            style = MaterialTheme.typography.bodyLarge,
            text = "Hello, $username ($id)"
        )
    }
}