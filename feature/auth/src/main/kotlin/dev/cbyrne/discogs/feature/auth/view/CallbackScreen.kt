package dev.cbyrne.discogs.feature.auth.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.cbyrne.discogs.feature.auth.view.model.CallbackScreenViewModel
import dev.cbyrne.discogs.ui.layout.CenteredColumn

@Composable
fun CallbackScreen(
    navigateToLogin: () -> Unit,
    token: String?,
    verifier: String?,
    viewModel: CallbackScreenViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.handleToken(token, verifier)
    }

    CenteredColumn(modifier = Modifier.padding(16.dp)) {
        CircularProgressIndicator()

        Text(
            text = "Signing into Discogs...",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp),
            style = MaterialTheme.typography.titleMedium
        )
    }
}