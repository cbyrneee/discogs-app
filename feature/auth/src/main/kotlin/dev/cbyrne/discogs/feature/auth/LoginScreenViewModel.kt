package dev.cbyrne.discogs.feature.auth

import androidx.compose.ui.platform.UriHandler
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cbyrne.discogs.common.network.ApiResult
import dev.cbyrne.discogs.common.network.handleApiResponse
import dev.cbyrne.discogs.feature.auth.data.repository.OauthRepository
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val oauthRepository: OauthRepository
) : ViewModel() {
    suspend fun getRequestToken(uriHandler: UriHandler) {
        val response = handleApiResponse {
            oauthRepository.getRequestToken(
                consumerKey = "jXjOtjTnxNVgHiTDJqoP",
                signature = "PCKFQRysJsRlutrEfcOGfhwHzbxKBUuv",
                callback = "balls"
            )
        }

        when (response) {
            is ApiResult.Success ->
                uriHandler.openUri("https://discogs.com/oauth/authorize?oauth_token=${response.data.token}")
            else -> error(response)
        }
    }
}