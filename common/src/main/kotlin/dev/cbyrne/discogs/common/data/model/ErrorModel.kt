package dev.cbyrne.discogs.common.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ErrorModel(val message: String)