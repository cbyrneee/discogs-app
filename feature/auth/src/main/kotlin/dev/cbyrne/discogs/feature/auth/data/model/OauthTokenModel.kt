package dev.cbyrne.discogs.feature.auth.data.model

import dev.cbyrne.discogs.feature.auth.util.named
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = OauthTokenModelSerializer::class)
data class OauthTokenModel(
    private val map: Map<String, String>
) {
    val token: String by map.named("oauth_token")
    val tokenSecret: String by map.named("oauth_token_secret")
    val callbackConfirmed: Boolean by map.named("oauth_callback_confirmed")
}

object OauthTokenModelSerializer : KSerializer<OauthTokenModel> {
    override val descriptor = PrimitiveSerialDescriptor("OauthTokenModel", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): OauthTokenModel {
        val string = decoder.decodeString()
        val entries = string.split("&")
            .associate {
                val (key, value) = it.split("=")
                key to value
            }

        return OauthTokenModel(entries)
    }

    override fun serialize(encoder: Encoder, value: OauthTokenModel) {
        TODO("Not yet implemented")
    }
}