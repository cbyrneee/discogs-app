package dev.cbyrne.discogs.feature.auth.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.cbyrne.discogs.feature.auth.view.model.LoginScreenViewModel
import dev.cbyrne.discogs.ui.layout.CenteredColumn
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(viewModel: LoginScreenViewModel = hiltViewModel()) {
    val coroutineScope = rememberCoroutineScope()
    val uriHandler = LocalUriHandler.current

    val authorize: () -> Unit = {
        coroutineScope.launch {
            viewModel.getRequestToken(uriHandler)
        }
    }

    CenteredColumn(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = "Welcome to the Discogs App",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "This is a third party application for the Discogs website. Clicking the button below will open your browser and authorize with your Discogs account.",
            textAlign = TextAlign.Center
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = authorize
        ) {
            Text("Login to Discogs")
        }
    }
}