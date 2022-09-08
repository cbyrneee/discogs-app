package dev.cbyrne.discogs.common.util

import dev.cbyrne.discogs.common.BuildConfig
import dev.cbyrne.discogs.common.data.model.user.UserCredentials
import okhttp3.Request

fun Request.Builder.applyCredentials(credentials: UserCredentials?): Request.Builder {
    if (credentials == null) return this

    val authorization = generateOAuthHeader(
        token = credentials.token,
        verifier = credentials.secret
    )

    addHeader("Authorization", authorization)
    return this
}

fun generateOAuthHeader(
    consumerKey: String = BuildConfig.CONSUMERKEY,
    signature: String = BuildConfig.CONSUMERSECRET,
    signatureMethod: String = "PLAINTEXT",
    nonce: String = System.currentTimeMillis().toString(),
    timestamp: String = System.currentTimeMillis().toString(),
    secret: String = "",
    token: String? = null,
    verifier: String? = null,
    callback: String? = null
): String {
    val oauth = mutableMapOf(
        "oauth_consumer_key" to consumerKey,
        "oauth_nonce" to nonce,
        "oauth_signature" to "$signature&$secret",
        "oauth_signature_method" to signatureMethod,
        "oauth_timestamp" to timestamp,
    )

    callback?.let { oauth["oauth_callback"] = it }
    token?.let { oauth["oauth_token"] = it }
    verifier?.let { oauth["oauth_verifier"] = it }

    val authorization = oauth.entries
        .joinToString(",") { (key, value) -> "$key=\"$value\"" }

    return "OAuth $authorization"
}