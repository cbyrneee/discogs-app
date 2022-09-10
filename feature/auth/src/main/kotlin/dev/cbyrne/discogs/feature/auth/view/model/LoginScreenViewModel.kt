package dev.cbyrne.discogs.feature.auth.view.model

import androidx.compose.ui.platform.UriHandler
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cbyrne.discogs.common.repository.user.UserRepository
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    suspend fun getRequestToken(uriHandler: UriHandler) {
        userRepository.retrieveAuthorizationRequestToken().fold(
            onSuccess = {
                uriHandler.openUri("https://discogs.com/oauth/authorize?oauth_token=${it.token}")
            },
            onFailure = {
                throw it
            }
        )
    }
}