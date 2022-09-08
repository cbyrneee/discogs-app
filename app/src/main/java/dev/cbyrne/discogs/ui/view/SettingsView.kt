package dev.cbyrne.discogs.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.cbyrne.discogs.common.navigation.Route
import dev.cbyrne.discogs.ui.view.model.SettingsViewModel

@Composable
fun SettingsView(
    navigateTo: (Route) -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val logout: () -> Unit = {
        viewModel.logout()
        navigateTo(Route.Auth.Login)
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(onClick = logout) {
            Text(text = "Logout")
        }
    }
}