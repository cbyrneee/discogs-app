package dev.cbyrne.discogs.feature.auth.view.model

import androidx.compose.ui.platform.UriHandler
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cbyrne.discogs.common.data.model.user.UserAuthorizationData
import dev.cbyrne.discogs.common.network.ApiResult
import dev.cbyrne.discogs.common.network.handleApiResponse
import dev.cbyrne.discogs.common.repository.user.UserRepository
import dev.cbyrne.discogs.feature.auth.data.api.OauthApiService
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val oauthApiService: OauthApiService,
    private val userRepository: UserRepository
) : ViewModel() {
    suspend fun getRequestToken(uriHandler: UriHandler) {
        when (val result = handleApiResponse { oauthApiService.getRequestToken() }) {
            is ApiResult.Success -> {
                userRepository.authorizationData =
                    UserAuthorizationData.Partial(result.data.tokenSecret)
                uriHandler.openUri("https://discogs.com/oauth/authorize?oauth_token=${result.data.token}")
            }

            else -> error(result)
        }
    }
}