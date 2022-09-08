package dev.cbyrne.discogs.feature.auth.data.repository

import dev.cbyrne.discogs.feature.auth.data.model.OauthAccessTokenModel
import dev.cbyrne.discogs.feature.auth.data.model.OauthTokenModel
import retrofit2.Response

interface OauthRepository {
    suspend fun getRequestToken(callback: String): Response<OauthTokenModel>

    suspend fun getAccessToken(
        token: String,
        verifier: String,
        secret: String
    ): Response<OauthAccessTokenModel>
}