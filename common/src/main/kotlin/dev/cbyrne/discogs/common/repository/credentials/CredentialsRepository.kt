package dev.cbyrne.discogs.common.repository.credentials

import dev.cbyrne.discogs.common.data.model.user.UserAuthorizationData
import dev.cbyrne.discogs.common.data.model.user.UserCredentials

interface CredentialsRepository {
    /**
     * The user's access token and information used when signing requests to the Discogs API
     * When setting this variable to null, it will be erased from secure storage. ([android.content.SharedPreferences.Editor.remove])
     */
    var credentials: UserCredentials?

    /**
     * The user's temporary login information. This is used for exchanging authorization codes to
     * access tokens.
     */
    var authorizationData: UserAuthorizationData?
}