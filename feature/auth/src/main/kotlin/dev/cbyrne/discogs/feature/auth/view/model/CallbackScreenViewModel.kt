package dev.cbyrne.discogs.feature.auth.view.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cbyrne.discogs.common.data.model.user.UserAuthorizationData
import dev.cbyrne.discogs.common.data.model.user.UserCredentials
import dev.cbyrne.discogs.common.data.model.user.UserIdentity
import dev.cbyrne.discogs.common.network.ApiResult
import dev.cbyrne.discogs.common.network.handleApiResponse
import dev.cbyrne.discogs.common.repository.user.UserRepository
import dev.cbyrne.discogs.feature.auth.data.api.OauthApiService
import dev.cbyrne.discogs.feature.auth.data.model.OauthAccessTokenModel
import dev.cbyrne.discogs.feature.auth.data.model.OauthIdentityModel
import javax.inject.Inject

sealed class CallbackScreenState {
    object Loading : CallbackScreenState()
    object Success : CallbackScreenState()

    class Error(val reason: String) : CallbackScreenState()
}

@HiltViewModel
class CallbackScreenViewModel @Inject constructor(
    private val oauthApiService: OauthApiService,
    private val userRepository: UserRepository
) : ViewModel() {
    var state by mutableStateOf<CallbackScreenState>(CallbackScreenState.Loading)
        private set

    /**
     * Called when the [dev.cbyrne.discogs.feature.auth.view.CallbackScreen] is first shown to the user (opened via deep-link)
     */
    suspend fun handleToken(token: String?, verifier: String?) {
        state = CallbackScreenState.Loading

        val authorizationData = userRepository.authorizationData
        if (token == null || verifier == null || authorizationData == null) {
            state = CallbackScreenState.Error(reason = "Please supply an oauth token!")
            return
        }

        userRepository.authorizationData = UserAuthorizationData.Full(
            authorizationData.secret,
            token = token,
            verifier = verifier
        )

        val result = handleApiResponse { oauthApiService.getAccessToken() }
        handleGetAccessToken(result)
    }

    /**
     * Called whenever we get a response from the [OauthApiService.getAccessToken] call
     */
    private suspend fun handleGetAccessToken(result: ApiResult<out OauthAccessTokenModel>) {
        state = when (result) {
            is ApiResult.Success -> {
                val credentials = UserCredentials(result.data.token, result.data.tokenSecret)
                userRepository.credentials = credentials

                val identityResult = handleApiResponse { oauthApiService.getIdentity() }
                handleGetIdentity(identityResult)
            }
            else -> handleApiError(result)
        }
    }

    /**
     * Called whenever we get a response from the [OauthApiService.getIdentity] call
     */
    private fun handleGetIdentity(result: ApiResult<out OauthIdentityModel>) =
        when (result) {
            is ApiResult.Success -> {
                userRepository.identity = UserIdentity(
                    id = result.data.id,
                    username = result.data.username
                )

                CallbackScreenState.Success
            }
            else -> handleApiError(result)
        }

    /**
     * Called whenever any of the [OauthApiService] methods return an error
     */
    private fun <T> handleApiError(result: ApiResult<T>) =
        when (result) {
            is ApiResult.Error ->
                CallbackScreenState.Error("Error whilst authenticating. Please try again!")
            else ->
                CallbackScreenState.Error("Unknown error")

        }
}