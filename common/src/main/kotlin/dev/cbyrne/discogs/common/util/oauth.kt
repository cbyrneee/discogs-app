import dev.cbyrne.discogs.common.data.model.user.UserCredentials
import okhttp3.Request

fun Request.Builder.applyCredentials(credentials: UserCredentials?): Request.Builder {
    if (credentials == null) return this

    val currentTimestamp = System.currentTimeMillis().toString()
    val oauthData = mapOf(
        "oauth_consumer_key" to "jXjOtjTnxNVgHiTDJqoP",
        "oauth_nonce" to currentTimestamp,
        "oauth_token" to credentials.token,
        "oauth_signature" to "PCKFQRysJsRlutrEfcOGfhwHzbxKBUuv&",
        "oauth_signature_method" to "PLAINTEXT",
        "oauth_timestamp" to currentTimestamp,
        "oauth_verifier" to credentials.secret
    )

    val authorization = oauthData.entries
        .joinToString(",") { (key, value) -> "$key=\"$value\"" }
    addHeader("Authorization", "OAuth $authorization")

    return this
}