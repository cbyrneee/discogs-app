package dev.cbyrne.discogs.feature.auth.view.model

import androidx.compose.ui.platform.UriHandler
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cbyrne.discogs.common.network.ApiResult
import dev.cbyrne.discogs.common.network.handleApiResponse
import dev.cbyrne.discogs.feature.auth.data.repository.OauthRepository
import dev.cbyrne.discogs.common.repository.storage.SecureStorageRepository
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val oauthRepository: OauthRepository,
    private val secureStorageRepository: SecureStorageRepository
) : ViewModel() {
    suspend fun getRequestToken(uriHandler: UriHandler) {
        val response = handleApiResponse {
            oauthRepository.getRequestToken(
                consumerKey = "jXjOtjTnxNVgHiTDJqoP",
                signature = "PCKFQRysJsRlutrEfcOGfhwHzbxKBUuv",
                callback = "discogs://callback"
            )
        }

        when (response) {
            is ApiResult.Success -> {
                secureStorageRepository.set("oauth_token_secret", response.data.tokenSecret)
                uriHandler.openUri("https://discogs.com/oauth/authorize?oauth_token=${response.data.token}")
            }

            else -> error(response)
        }
    }
}