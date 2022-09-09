package dev.cbyrne.discogs.common.repository.user

import dev.cbyrne.discogs.common.data.model.user.UserAuthorizationData
import dev.cbyrne.discogs.common.data.model.user.UserCredentials
import dev.cbyrne.discogs.common.data.model.user.UserIdentity

interface UserRepository {
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

    /**
     * The user's identity (ID and username)
     */
    var identity: UserIdentity?
}