package dev.cbyrne.discogs.common.data.model.user

import kotlinx.serialization.Serializable

@Serializable
sealed class UserAuthorizationData {
    abstract val secret: String

    @Serializable
    data class Partial(override val secret: String) : UserAuthorizationData()

    @Serializable
    data class Full(
        override val secret: String,
        val token: String,
        val verifier: String
    ) : UserAuthorizationData()
}