package dev.cbyrne.discogs.feature.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cbyrne.discogs.common.network.ApiResult
import dev.cbyrne.discogs.common.network.handleApiResponse
import dev.cbyrne.discogs.feature.auth.data.repository.OauthRepository
import dev.cbyrne.discogs.feature.auth.data.repository.SecureStorageRepository
import javax.inject.Inject

sealed class CallbackScreenState {
    object Loading : CallbackScreenState()
    object Success : CallbackScreenState()

    class Error(val reason: String) : CallbackScreenState()
}

@HiltViewModel
class CallbackScreenViewModel @Inject constructor(
    private val oauthRepository: OauthRepository,
    private val secureStorageRepository: SecureStorageRepository
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

        val response = handleApiResponse {
            oauthRepository.getAccessToken(
                consumerKey = "jXjOtjTnxNVgHiTDJqoP",
                signature = "PCKFQRysJsRlutrEfcOGfhwHzbxKBUuv",
                token = token,
                verifier = verifier,
                secret = secret
            )
        }

        state = when (response) {
            is ApiResult.Success -> {
                println("Token: ${response.data.token} Secret: ${response.data.tokenSecret}")
                CallbackScreenState.Success
            }
            is ApiResult.Error ->
                CallbackScreenState.Error(response.message ?: "Invalid oauth token!")
            else ->
                CallbackScreenState.Error("Unknown error")
        }
    }
}