package dev.cbyrne.discogs.feature.discover.view.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cbyrne.discogs.api.model.user.UserInformationModel
import dev.cbyrne.discogs.common.repository.user.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class HomeViewState {
    class Loaded(val information: UserInformationModel) : HomeViewState()
    class Error(val reason: String) : HomeViewState()
    object Loading : HomeViewState()
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    var state by mutableStateOf<HomeViewState>(HomeViewState.Loading)
    var isRefreshing by mutableStateOf(false)

    suspend fun load() {
        if (state is HomeViewState.Loaded) {
            return
        }

        state = HomeViewState.Loading
        fetchData()
    }

    fun reload() {
        viewModelScope.launch {
            isRefreshing = true
            fetchData()
            isRefreshing = false
        }
    }

    private suspend fun fetchData() {
        val result = userRepository.current()
        state = result.fold(
            onSuccess = { HomeViewState.Loaded(it) },
            onFailure = { HomeViewState.Error(it.message ?: "Unknown error") }
        )
    }
}
