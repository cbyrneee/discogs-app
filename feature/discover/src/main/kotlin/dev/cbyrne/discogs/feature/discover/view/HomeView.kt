package dev.cbyrne.discogs.feature.discover.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
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

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SubcomposeAsyncImage(
                        model = information.avatarUrl,
                        loading = {
                            CircularProgressIndicator()
                        },
                        contentDescription = information.name,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(2.5.dp))
                    )

                    Column {
                        Text(text = information.name)
                        Text(
                            text = information.username,
                            modifier = Modifier.alpha(0.75f)
                        )
                    }
                }
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