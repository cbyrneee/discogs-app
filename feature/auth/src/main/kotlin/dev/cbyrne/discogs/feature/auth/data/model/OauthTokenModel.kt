package dev.cbyrne.discogs.feature.auth.data.model

import dev.cbyrne.discogs.common.util.PropertiesDecoder
import dev.cbyrne.discogs.common.util.named
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = OauthTokenModelSerializer::class)
data class OauthTokenModel(
    private val map: Map<String, String>
) {
    val token: String by map.named("oauth_token")
    val tokenSecret: String by map.named("oauth_token_secret")
    val callbackConfirmed: Boolean by map.named("oauth_callback_confirmed") {
        it.toString().toBoolean()
    }
}

object OauthTokenModelSerializer :
    KSerializer<OauthTokenModel> by PropertiesDecoder(::OauthTokenModel)
