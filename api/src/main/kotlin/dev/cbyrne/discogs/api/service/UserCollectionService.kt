package dev.cbyrne.discogs.api.service

import dev.cbyrne.discogs.api.model.user.collection.FolderReleasesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserCollectionService {
    @GET("/users/{username}/collection/folders/{folder_id}/releases")
    suspend fun retrieveFolderReleases(
        @Path("username") username: String,
        @Path("folder_id") folderId: Int
    ): Response<FolderReleasesModel>
}