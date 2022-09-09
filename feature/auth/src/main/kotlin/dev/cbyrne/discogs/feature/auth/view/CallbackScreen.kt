package dev.cbyrne.discogs.feature.auth.view

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.cbyrne.discogs.common.navigation.Route
import dev.cbyrne.discogs.feature.auth.view.model.CallbackScreenState
import dev.cbyrne.discogs.feature.auth.view.model.CallbackScreenViewModel
import dev.cbyrne.discogs.ui.layout.CenteredColumn

@Composable
fun CallbackScreen(
    navigateTo: (Route) -> Unit,
    token: String?,
    verifier: String?,
    viewModel: CallbackScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val context = LocalContext.current

    // Try to login with the token and verifier when the view is first shown
    LaunchedEffect(Unit) {
        viewModel.handleToken(token, verifier)
    }

    CenteredColumn(modifier = Modifier.padding(16.dp)) {
        when (state) {
            is CallbackScreenState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.padding(bottom = 16.dp),
                )

                Text(
                    text = "Signing into Discogs...",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            is CallbackScreenState.Error -> {
                LaunchedEffect(Unit) {
                    navigateTo(Route.Auth.Login)
                    Toast.makeText(context, state.reason, Toast.LENGTH_LONG).show()
                }
            }
            is CallbackScreenState.Success -> {
                LaunchedEffect(Unit) {
                    navigateTo(Route.Discover.Home)
                }
            }
        }
    }
}