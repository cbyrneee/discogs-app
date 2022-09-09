package dev.cbyrne.discogs.feature.auth.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OauthIdentityModel(
    val id: Int,
    val username: String,
    @SerialName("resource_url")
    val resourceUrl: String,
    @SerialName("consumer_name")
    val consumerName: String
)