package dev.cbyrne.discogs.data.model.generic

import kotlinx.serialization.Serializable

@Serializable
data class ErrorModel(val message: String)