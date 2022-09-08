package dev.cbyrne.discogs.feature.auth.data.model

import dev.cbyrne.discogs.common.util.PropertiesDecoder
import dev.cbyrne.discogs.common.util.named
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = OauthAccessTokenModelSerializer::class)
data class OauthAccessTokenModel(
    private val map: Map<String, String>
) {
    val token: String by map.named("oauth_token")
    val tokenSecret: String by map.named("oauth_token_secret")
}

object OauthAccessTokenModelSerializer :
    KSerializer<OauthAccessTokenModel> by PropertiesDecoder(::OauthAccessTokenModel)