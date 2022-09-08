package dev.cbyrne.discogs.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import dev.cbyrne.discogs.data.model.releases.ReleaseModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeReleaseContent(release: ReleaseModel) {
    val uriHandler = LocalUriHandler.current

    release.images?.let {
        HorizontalPager(count = it.size) { index ->
            SubcomposeAsyncImage(
                model = it[index].uri,
                contentDescription = "Image",
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp)),
                loading = {
                    CircularProgressIndicator()
                },
                contentScale = ContentScale.Crop
            )
        }

    }

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