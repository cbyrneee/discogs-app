package dev.cbyrne.discogs.common.repository.user.impl

import dev.cbyrne.discogs.common.data.model.user.UserCredentials
import dev.cbyrne.discogs.common.repository.storage.SecureStorageRepository
import dev.cbyrne.discogs.common.repository.storage.getObject
import dev.cbyrne.discogs.common.repository.storage.setObject
import dev.cbyrne.discogs.common.repository.user.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val secureStorageRepository: SecureStorageRepository
) : UserRepository {
    override var credentials: UserCredentials? = null
        get() = secureStorageRepository.getObject("user.credentials")
        set(value) {
            field = value

            if (value == null) {
                secureStorageRepository.remove("user.credentials")
            } else {
                secureStorageRepository.setObject("user.credentials", value)
            }
        }
}
