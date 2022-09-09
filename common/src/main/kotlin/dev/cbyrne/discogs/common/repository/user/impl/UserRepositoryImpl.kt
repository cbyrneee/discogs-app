package dev.cbyrne.discogs.common.repository.user.impl

import dev.cbyrne.discogs.common.data.model.user.UserAuthorizationData
import dev.cbyrne.discogs.common.data.model.user.UserCredentials
import dev.cbyrne.discogs.common.repository.storage.SecureStorageRepository
import dev.cbyrne.discogs.common.repository.storage.getObject
import dev.cbyrne.discogs.common.repository.storage.setObject
import dev.cbyrne.discogs.common.repository.user.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val secureStorageRepository: SecureStorageRepository
) : UserRepository {
    override var credentials: UserCredentials? = null
        get() = secureStorageRepository.getObject("user.credentials")
        set(value) {
            field = value
            setOrRemove("user.credentials", value)
        }

    override var authorizationData: UserAuthorizationData? = null
        get() = secureStorageRepository.getObject("user.authorization_data")
        set(value) {
            field = value
            setOrRemove("user.authorization_data", value)
        }

    private inline fun <reified T> setOrRemove(key: String, data: T) {
        if (data == null) {
            secureStorageRepository.remove(key)
        } else {
            secureStorageRepository.setObject(key, data)
        }
    }
}
