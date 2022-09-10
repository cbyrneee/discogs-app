package dev.cbyrne.discogs.api.model.oauth

import dev.cbyrne.discogs.api.util.PropertiesDecoder
import dev.cbyrne.discogs.api.util.named
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = OAuthAccessTokenModel.Serializer::class)
data class OAuthAccessTokenModel(
    private val map: Map<String, String>
) {
    val token: String by map.named("oauth_token")
    val tokenSecret: String by map.named("oauth_token_secret")

    internal object Serializer :
        KSerializer<OAuthAccessTokenModel> by PropertiesDecoder(::OAuthAccessTokenModel)
}
