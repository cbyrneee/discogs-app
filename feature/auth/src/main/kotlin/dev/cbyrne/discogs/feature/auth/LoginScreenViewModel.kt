package dev.cbyrne.discogs.feature.auth

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cbyrne.discogs.feature.auth.data.repository.OauthRepository
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val oauthRepository: OauthRepository
) : ViewModel() {
    suspend fun getRequestToken() {
        val response = oauthRepository.getRequestToken(
            consumerKey = "jXjOtjTnxNVgHiTDJqoP",
            signature = "PCKFQRysJsRlutrEfcOGfhwHzbxKBUuv",
            callback = "balls"
        )

        print(response.body()?.callbackConfirmed)
    }
}