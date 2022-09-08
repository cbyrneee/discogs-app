package dev.cbyrne.discogs.feature.auth.data.repository

import dev.cbyrne.discogs.feature.auth.data.model.OauthAccessTokenModel
import dev.cbyrne.discogs.feature.auth.data.model.OauthTokenModel
import retrofit2.Response

interface OauthRepository {
    suspend fun getRequestToken(
        consumerKey: String,
        nonce: String = "${System.currentTimeMillis()}",
        signature: String,
        signatureMethod: String = "PLAINTEXT",
        timestamp: String = "${System.currentTimeMillis()}",
        callback: String
    ): Response<OauthTokenModel>

    suspend fun getAccessToken(
        consumerKey: String,
        nonce: String = "${System.currentTimeMillis()}",
        token: String,
        signature: String,
        signatureMethod: String = "PLAINTEXT",
        timestamp: String = "${System.currentTimeMillis()}",
        verifier: String,
        secret: String
    ): Response<OauthAccessTokenModel>
}