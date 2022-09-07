package dev.cbyrne.discogs.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalUriHandler
import dev.cbyrne.discogs.data.model.releases.ReleaseModel

@Composable
fun HomeReleaseContent(release: ReleaseModel) {
    val uriHandler = LocalUriHandler.current

    Column {
        Text(
            text = release.title,
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = release.artists.first().name,
            style = MaterialTheme.typography.titleMedium
        )
    }

    Text(text = release.notes ?: "No description for this release")

    Button(onClick = {
        uriHandler.openUri(release.uri)
    }) {
        Text("View on Discogs")
    }
}