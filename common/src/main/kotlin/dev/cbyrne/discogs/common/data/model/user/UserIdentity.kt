package dev.cbyrne.discogs.common.data.model.user

import kotlinx.serialization.Serializable

@Serializable
data class UserIdentity(
    val id: Int,
    val username: String
)
