package dev.cbyrne.discogs.feature.auth.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.cbyrne.discogs.feature.auth.view.model.LoginScreenViewModel
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

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("This will soon be a login screen")
        Button(onClick = authorize) {
            Text("Login")
        }
    }
}