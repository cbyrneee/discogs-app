package dev.cbyrne.discogs.common.data.model.user

import kotlinx.serialization.Serializable

@Serializable
data class UserCredentials(
    val token: String,
    val secret: String
)
