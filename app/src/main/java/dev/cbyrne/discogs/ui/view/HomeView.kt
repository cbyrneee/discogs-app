package dev.cbyrne.discogs.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.cbyrne.discogs.ui.component.CenteredBox
import dev.cbyrne.discogs.ui.home.HomeReleaseContent
import dev.cbyrne.discogs.ui.view.model.HomeUiState
import dev.cbyrne.discogs.ui.view.model.HomeViewModel

@Composable
fun HomeView(viewModel: HomeViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState
    var idValue by remember { mutableStateOf(TextFieldValue("14974399")) }

    LaunchedEffect(idValue.text) {
        viewModel.getRelease(idValue.text)
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Enter discogs ID") },
            value = idValue,
            onValueChange = { idValue = it }
        )

        if (idValue.text.isEmpty()) {
            Text("Enter an ID from discogs to view details!")
        } else {
            when (uiState) {
                is HomeUiState.Loaded -> HomeReleaseContent(uiState.release)
                is HomeUiState.Empty -> CenteredBox { CircularProgressIndicator() }
                is HomeUiState.Error -> Text(uiState.message)
            }
        }
    }
}