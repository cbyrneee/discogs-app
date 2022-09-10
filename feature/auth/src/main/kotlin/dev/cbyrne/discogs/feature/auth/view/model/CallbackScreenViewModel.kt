package dev.cbyrne.discogs.feature.auth.view.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cbyrne.discogs.common.data.model.user.UserAuthorizationData
import dev.cbyrne.discogs.common.repository.credentials.CredentialsRepository
import dev.cbyrne.discogs.common.repository.user.UserRepository
import javax.inject.Inject

sealed class CallbackScreenState {
    object Loading : CallbackScreenState()
    object Success : CallbackScreenState()

    class Error(val reason: String) : CallbackScreenState()
}

@HiltViewModel
class CallbackScreenViewModel @Inject constructor(
    private val credentialsRepository: CredentialsRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    var state by mutableStateOf<CallbackScreenState>(CallbackScreenState.Loading)
        private set

    /**
     * Called when the screen is first shown to the user (opened via deep-link)
     */
    suspend fun handleToken(token: String?, verifier: String?) {
        state = CallbackScreenState.Loading

        val authorizationData = credentialsRepository.authorizationData
        if (token == null || verifier == null || authorizationData == null) {
            state = CallbackScreenState.Error(reason = "Please supply an oauth token!")
            return
        }

        credentialsRepository.authorizationData = UserAuthorizationData.Full(
            authorizationData.secret,
            token = token,
            verifier = verifier
        )

        userRepository.authorize()
            .onFailure {
                handleAuthorizationError()
                return
            }

        userRepository.retrieveIdentity()
            .onFailure {
                handleAuthorizationError()
                return
            }

        state = CallbackScreenState.Success
    }

    private fun handleAuthorizationError() {
        state = CallbackScreenState.Error("Error whilst authenticating. Please try again!")
    }
}