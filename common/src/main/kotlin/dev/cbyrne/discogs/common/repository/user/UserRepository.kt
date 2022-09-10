package dev.cbyrne.discogs.common.repository.user

import dev.cbyrne.discogs.api.model.oauth.OAuthAccessTokenModel
import dev.cbyrne.discogs.api.model.oauth.OAuthIdentityModel
import dev.cbyrne.discogs.api.model.oauth.OAuthRequestTokenModel
import dev.cbyrne.discogs.api.model.user.UserInformationModel
import dev.cbyrne.discogs.api.model.user.collection.FolderReleasesModel
import dev.cbyrne.discogs.common.data.model.user.UserIdentity

interface UserRepository {
    /**
     * The user's identity (ID and username)
     */
    var identity: UserIdentity?

    /**
     * The currently logged in user's information
     */
    suspend fun current(): Result<UserInformationModel>

    /**
     * Returns the list of releases in a folder in the user's collection
     */
    suspend fun releases(folderId: Int): Result<List<FolderReleasesModel.Release>>

    /**
     * Uses the current [authorizationData] to get an access token, populating the [credentials]
     * field.
     */
    suspend fun authorize(): Result<OAuthAccessTokenModel>

    /**
     * Gets an authorization request token for the user
     */
    suspend fun retrieveAuthorizationRequestToken(): Result<OAuthRequestTokenModel>

    /**
     * Uses the current [credentials] to get the user's OAuth identity ([identity])
     */
    suspend fun retrieveIdentity(): Result<OAuthIdentityModel>
}