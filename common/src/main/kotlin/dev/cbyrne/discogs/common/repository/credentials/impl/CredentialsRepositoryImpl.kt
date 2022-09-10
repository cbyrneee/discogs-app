package dev.cbyrne.discogs.common.repository.credentials.impl

import dev.cbyrne.discogs.common.data.model.user.UserAuthorizationData
import dev.cbyrne.discogs.common.data.model.user.UserCredentials
import dev.cbyrne.discogs.common.repository.credentials.CredentialsRepository
import dev.cbyrne.discogs.common.repository.storage.SecureStorageBacked
import dev.cbyrne.discogs.common.repository.storage.SecureStorageRepository
import javax.inject.Inject

class CredentialsRepositoryImpl @Inject constructor(
    secureStorageRepository: SecureStorageRepository
) : CredentialsRepository, SecureStorageBacked(secureStorageRepository, "credentials") {
    override var credentials: UserCredentials? by secureStorage()
    override var authorizationData: UserAuthorizationData? by secureStorage()
}