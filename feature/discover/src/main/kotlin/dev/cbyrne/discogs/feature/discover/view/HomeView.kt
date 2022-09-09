package dev.cbyrne.discogs.feature.discover.view

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import dev.cbyrne.discogs.ui.layout.DiscogsLayout

@Composable
fun HomeView() {
    DiscogsLayout(title = "Home") {
        Text(
            style = MaterialTheme.typography.bodyLarge,
            text = "This is the home page"
        )
    }
}