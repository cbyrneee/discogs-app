package dev.cbyrne.discogs.data.model.releases

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReleaseModel(
    val id: Int,
    val title: String,
    val uri: String,
    val notes: String? = null,
    val artists: List<Artist>
) {
    @Serializable
    data class Artist(
        val name: String,

        @SerialName("resource_url")
        val resourceUrl: String
    )
}