package dev.cbyrne.discogs.feature.auth.view.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cbyrne.discogs.common.data.model.user.UserCredentials
import dev.cbyrne.discogs.common.network.ApiResult
import dev.cbyrne.discogs.common.network.handleApiResponse
import dev.cbyrne.discogs.common.repository.storage.SecureStorageRepository
import dev.cbyrne.discogs.common.repository.user.UserRepository
import dev.cbyrne.discogs.feature.auth.data.repository.OauthRepository
import javax.inject.Inject

sealed class CallbackScreenState {
    object Loading : CallbackScreenState()
    object Success : CallbackScreenState()

    class Error(val reason: String) : CallbackScreenState()
}

@HiltViewModel
class CallbackScreenViewModel @Inject constructor(
    private val oauthRepository: OauthRepository,
    private val secureStorageRepository: SecureStorageRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    var state by mutableStateOf<CallbackScreenState>(CallbackScreenState.Loading)
        private set

    suspend fun handleToken(token: String?, verifier: String?) {
        state = CallbackScreenState.Loading

        val secret = secureStorageRepository.get("oauth_token_secret")
        if (token == null || verifier == null || secret == null) {
            state = CallbackScreenState.Error(reason = "Please supply an oauth token!")
            return
        }

        val response = handleApiResponse { oauthRepository.getAccessToken(token, verifier, secret) }
        state = when (response) {
            is ApiResult.Success -> {
                val userCredentials = UserCredentials(
                    token = response.data.token,
                    secret = response.data.tokenSecret
                )

                userRepository.credentials = userCredentials
                CallbackScreenState.Success
            }
            is ApiResult.Error ->
                CallbackScreenState.Error("Error whilst authenticating. Please try again!")
            else ->
                CallbackScreenState.Error("Unknown error")
        }
    }
}