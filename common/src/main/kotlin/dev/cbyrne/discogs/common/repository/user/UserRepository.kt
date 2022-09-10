package dev.cbyrne.discogs.common.repository.user

import dev.cbyrne.discogs.api.model.oauth.OAuthRequestTokenModel
import dev.cbyrne.discogs.api.model.user.UserInformationModel
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
     * Uses the current [authorizationData] to get an access token, populating the [credentials]
     * field.
     */
    suspend fun authorize(): Result<Nothing?>

    /**
     * Gets an authorization request token for the user
     */
    suspend fun retrieveAuthorizationRequestToken(): Result<OAuthRequestTokenModel>

    /**
     * Uses the current [credentials] to get the user's OAuth identity ([identity])
     */
    suspend fun retrieveIdentity(): Result<Nothing?>
}