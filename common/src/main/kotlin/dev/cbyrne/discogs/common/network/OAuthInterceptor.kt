package dev.cbyrne.discogs.common.network

import dev.cbyrne.discogs.common.BuildConfig
import dev.cbyrne.discogs.common.data.model.user.UserAuthorizationData
import dev.cbyrne.discogs.common.data.model.user.UserCredentials
import dev.cbyrne.discogs.common.repository.user.UserRepository
import oauth.signpost.http.HttpParameters
import oauth.signpost.signature.PlainTextMessageSigner
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer

class OAuthInterceptor(private val userRepository: UserRepository) : Interceptor {
    private val consumer = OkHttpOAuthConsumer(
        BuildConfig.CONSUMERKEY,
        BuildConfig.CONSUMERSECRET
    )

    override fun intercept(chain: Interceptor.Chain): Response {
        val credentials = userRepository.credentials
        loginWithCredentials(credentials)

        // Let's sign the request :^)
        val signed = consumer.sign(chain.request())
        val signedRequest = signed.unwrap() as Request
        return chain.proceed(signedRequest)
    }

    private fun loginWithCredentials(credentials: UserCredentials?) {
        if (credentials != null) {
            // If the user has credentials, we can just log in with those as normal
            consumer.setTokenWithSecret(credentials.token, credentials.secret)
            return
        }

        // If the user's credentials are null, they haven't logged in yet.
        // Thus, we need to add some extra parameters and change a few things to accommodate
        // the authorization process.
        // We also need to specify some extra parameters in the authorization header
        val additionalParameters = HttpParameters()
        additionalParameters["oauth_callback"] = sortedSetOf("discogs://callback")

        // Discogs' authorization requests are plain text signed, not HMAC-SHA1
        consumer.setMessageSigner(PlainTextMessageSigner())

        // This data is supplied to us during the authorization process
        // If we have `Full` authorization data (i.e. a token, secret and verifier), the current
        // request should be requesting the access token.
        val data = userRepository.authorizationData
        if (data is UserAuthorizationData.Full) {
            consumer.setTokenWithSecret(data.token, data.secret)
            additionalParameters["oauth_verifier"] = sortedSetOf(data.verifier)

            // Once we're finished with this data we don't need it anymore, it expires
            // after 15 minutes anyways.
            userRepository.authorizationData = null
        }

        consumer.setAdditionalParameters(additionalParameters)
    }
}