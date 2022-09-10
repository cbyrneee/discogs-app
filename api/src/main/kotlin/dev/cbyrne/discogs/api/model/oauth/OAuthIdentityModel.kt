package dev.cbyrne.discogs.api.model.oauth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OAuthIdentityModel(
    val id: Int,
    val username: String,
    @SerialName("resource_url")
    val resourceUrl: String,
    @SerialName("consumer_name")
    val consumerName: String
)