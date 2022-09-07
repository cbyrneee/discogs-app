package dev.cbyrne.discogs.ui.view.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cbyrne.discogs.data.model.releases.ReleaseModel
import dev.cbyrne.discogs.data.repository.MainRepository
import dev.cbyrne.discogs.network.ApiResult
import dev.cbyrne.discogs.network.handleApiResponse
import javax.inject.Inject

sealed class HomeUiState {
    object Empty : HomeUiState()

    class Loaded(val release: ReleaseModel) : HomeUiState()
    class Error(val message: String) : HomeUiState()
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    var uiState by mutableStateOf<HomeUiState>(HomeUiState.Empty)
        private set

    suspend fun getRelease(id: String) {
        uiState = HomeUiState.Empty

        val response = handleApiResponse { mainRepository.getRelease(id) }
        uiState = when (response) {
            is ApiResult.Success -> HomeUiState.Loaded(release = response.data)
            is ApiResult.Error -> HomeUiState.Error(message = "An error occurred: ${response.message ?: "Unknown error"}")
            is ApiResult.NotFound -> HomeUiState.Error(message = "Release not found")
        }
    }
}