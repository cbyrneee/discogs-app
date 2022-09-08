package dev.cbyrne.discogs.feature.auth.data.repository.impl

import dev.cbyrne.discogs.feature.auth.data.api.OauthApiService
import dev.cbyrne.discogs.feature.auth.data.model.OauthTokenModel
import dev.cbyrne.discogs.feature.auth.data.repository.OauthRepository
import retrofit2.Response
import javax.inject.Inject

class OauthRepositoryImpl @Inject constructor(
    private val oauthService: OauthApiService
) : OauthRepository {
    override suspend fun getRequestToken(
        consumerKey: String,
        nonce: String,
        signature: String,
        signatureMethod: String,
        timestamp: String,
        callback: String
    ): Response<OauthTokenModel> {
        val authorization = mapOf(
            "oauth_consumer_key" to consumerKey,
            "oauth_nonce" to nonce,
            "oauth_signature" to "$signature&",
            "oauth_signature_method" to signatureMethod,
            "oauth_timestamp" to timestamp,
            "oauth_callback" to callback
        ).entries.joinToString(",") { (key, value) -> "${key}=\"${value}\"" }

        val headers = mapOf(
            "Content-Type" to "application/x-www-form-urlencoded",
            "User-Agent" to "DiscogsAndroidApp/1.0",
            "Authorization" to "OAuth $authorization"
        )

        return oauthService.getRequestToken(headers)
    }
}