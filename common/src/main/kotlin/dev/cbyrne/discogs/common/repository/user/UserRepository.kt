package dev.cbyrne.discogs.common.repository.user

import dev.cbyrne.discogs.common.data.model.user.UserCredentials

interface UserRepository {
    /**
     * The user's access token and information used when signing requests to the Discogs API
     * When setting this variable to null, it will be erased from secure storage. ([android.content.SharedPreferences.Editor.remove])
     */
    var credentials: UserCredentials?
}