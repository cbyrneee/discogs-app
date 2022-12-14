package dev.cbyrne.discogs.feature.discover.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import dev.cbyrne.discogs.feature.discover.component.ProfileCard
import dev.cbyrne.discogs.feature.discover.view.model.HomeViewModel
import dev.cbyrne.discogs.feature.discover.view.model.HomeViewState
import dev.cbyrne.discogs.ui.layout.CenteredColumn
import dev.cbyrne.discogs.ui.layout.DiscogsLayout

@Composable
fun HomeView(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val refreshingState = rememberSwipeRefreshState(isRefreshing = viewModel.isRefreshing)

    LaunchedEffect(viewModel) {
        viewModel.load()
    }

    DiscogsLayout(title = "Home") {
        when (state) {
            is HomeViewState.Loaded -> {
                SwipeRefresh(
                    state = refreshingState,
                    onRefresh = { viewModel.reload() }
                ) {
                    Column {
                        ProfileCard(information = state.information)

                        LazyColumn {
                            items(state.releases) {
                                Text(text = it.basicInformation.title)
                            }
                        }
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