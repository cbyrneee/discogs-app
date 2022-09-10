package dev.cbyrne.discogs.api.model.user.collection

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FolderReleasesModel(
    val releases: List<Release>
) {
    @Serializable
    data class Release(
        val id: Long,
        val rating: Long,
        @SerialName("instance_id")
        val instanceID: Long,
        @SerialName("date_added")
        val dateAdded: String,
        @SerialName("basic_information")
        val basicInformation: ReleaseInformation
    )

    @Serializable
    data class ReleaseInformation(
        val id: Long,
        val title: String,
        val year: Long,
        val formats: List<Format>,
        val labels: List<Label>,
        val artists: List<Artist>,
        val genres: List<String>,
        val styles: List<String>,
        val thumb: String,
        @SerialName("master_id")
        val masterID: Long,
        @SerialName("master_url")
        val masterURL: String? = null,
        @SerialName("resource_url")
        val resourceURL: String,
        @SerialName("cover_image")
        val coverImage: String
    )

    @Serializable
    data class Artist(
        val name: String,
        val anv: String,
        val join: String,
        val role: String,
        val tracks: String,
        val id: Long,
        @SerialName("resource_url")
        val resourceURL: String
    )

    @Serializable
    data class Format(
        val name: String,
        val qty: String,
        val text: String? = null,
        val descriptions: List<String>? = null
    )

    @Serializable
    data class Label(
        val id: Long,
        val name: String,
        @SerialName("catno")
        val catalogNumber: String,
        @SerialName("entity_type")
        val entityType: String,
        @SerialName("entity_type_name")
        val entityTypeName: String,
        @SerialName("resource_url")
        val resourceURL: String
    )
}
