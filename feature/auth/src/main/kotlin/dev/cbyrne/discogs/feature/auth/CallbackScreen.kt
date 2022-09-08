package dev.cbyrne.discogs.feature.auth

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.cbyrne.discogs.ui.layout.CenteredColumn

@Composable
fun CallbackScreen(navigateToLogin: () -> Unit, token: String?) {
    LaunchedEffect(token) {
        if (token == null) {
            navigateToLogin()
            return@LaunchedEffect
        }
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