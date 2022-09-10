package dev.cbyrne.discogs.api.model.oauth

import dev.cbyrne.discogs.api.util.PropertiesDecoder
import dev.cbyrne.discogs.api.util.named
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = OAuthRequestTokenModel.Serializer::class)
data class OAuthRequestTokenModel(
    private val map: Map<String, String>
) {
    val token: String by map.named("oauth_token")
    val tokenSecret: String by map.named("oauth_token_secret")
    val callbackConfirmed: Boolean by map.named("oauth_callback_confirmed") {
        it.toString().toBoolean()
    }

    internal object Serializer :
        KSerializer<OAuthRequestTokenModel> by PropertiesDecoder(::OAuthRequestTokenModel)
}
