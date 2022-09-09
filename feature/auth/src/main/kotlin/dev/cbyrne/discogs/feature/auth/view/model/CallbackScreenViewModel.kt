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
import dev.cbyrne.discogs.feature.auth.data.model.OauthAccessTokenModel
import dev.cbyrne.discogs.feature.auth.data.model.OauthIdentityModel
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

    /**
     * Called when the [dev.cbyrne.discogs.feature.auth.view.CallbackScreen] is first shown to the user (opened via deep-link)
     */
    suspend fun handleToken(token: String?, verifier: String?) {
        state = CallbackScreenState.Loading

        val secret = secureStorageRepository.get("oauth_token_secret")
        if (token == null || verifier == null || secret == null) {
            state = CallbackScreenState.Error(reason = "Please supply an oauth token!")
            return
        }

        val result = handleApiResponse { oauthRepository.getAccessToken(token, verifier, secret) }
        handleGetAccessToken(result)
    }

    /**
     * Called whenever we get a response from the [OauthRepository.getAccessToken] call
     */
    private suspend fun handleGetAccessToken(result: ApiResult<out OauthAccessTokenModel>) {
        state = when (result) {
            is ApiResult.Success -> {
                val credentials = UserCredentials(result.data.token, result.data.tokenSecret)
                userRepository.credentials = credentials

                val identityResult = handleApiResponse { oauthRepository.getIdentity() }
                handleGetIdentity(identityResult)
            }
            else -> handleApiError(result)
        }
    }

    /**
     * Called whenever we get a response from the [OauthRepository.getIdentity] call
     */
    private fun handleGetIdentity(result: ApiResult<out OauthIdentityModel>) =
        when (result) {
            is ApiResult.Success -> {
                println(result.data)
                CallbackScreenState.Success
            }
            else -> handleApiError(result)
        }

    /**
     * Called whenever any of the [OauthRepository] methods return an error
     */
    private fun <T> handleApiError(result: ApiResult<T>) =
        when (result) {
            is ApiResult.Error ->
                CallbackScreenState.Error("Error whilst authenticating. Please try again!")
            else ->
                CallbackScreenState.Error("Unknown error")

        }
}