package dev.cbyrne.discogs.feature.auth.data.repository.impl

import dev.cbyrne.discogs.common.util.generateOAuthHeader
import dev.cbyrne.discogs.feature.auth.data.api.OauthApiService
import dev.cbyrne.discogs.feature.auth.data.model.OauthAccessTokenModel
import dev.cbyrne.discogs.feature.auth.data.model.OauthTokenModel
import dev.cbyrne.discogs.feature.auth.data.repository.OauthRepository
import retrofit2.Response
import javax.inject.Inject

class OauthRepositoryImpl @Inject constructor(
    private val oauthService: OauthApiService
) : OauthRepository {
    override suspend fun getRequestToken(callback: String): Response<OauthTokenModel> {
        val authorization = generateOAuthHeader(callback = callback)
        val headers = mapOf("Authorization" to authorization)

        return oauthService.getRequestToken(headers)
    }

    override suspend fun getAccessToken(
        token: String,
        verifier: String,
        secret: String
    ): Response<OauthAccessTokenModel> {
        val authorization = generateOAuthHeader(
            token = token,
            verifier = verifier,
            secret = secret
        )

        val headers = mapOf("Authorization" to authorization)
        return oauthService.getAccessToken(headers)
    }
}