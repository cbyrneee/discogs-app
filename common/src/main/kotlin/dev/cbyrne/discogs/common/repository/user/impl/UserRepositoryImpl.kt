package dev.cbyrne.discogs.common.repository.user.impl

import dev.cbyrne.discogs.common.data.model.user.UserAuthorizationData
import dev.cbyrne.discogs.common.data.model.user.UserCredentials
import dev.cbyrne.discogs.common.repository.storage.SecureStorageRepository
import dev.cbyrne.discogs.common.repository.storage.getObject
import dev.cbyrne.discogs.common.repository.storage.setObject
import dev.cbyrne.discogs.common.repository.user.UserRepository
import dev.cbyrne.discogs.common.util.toSnakeCase
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.KProperty

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val secureStorageRepository: SecureStorageRepository
) : UserRepository {
    override var credentials: UserCredentials? by secureStorage()
    override var authorizationData: UserAuthorizationData? by secureStorage()

    private fun <T> secureStorage(): SecureStorageDelegate<T> = SecureStorageDelegate()

    internal inner class SecureStorageDelegate<R> {
        inline operator fun <reified T> getValue(thisRef: R, property: KProperty<*>): T? {
            val key = getPropertyKey(property)
            return secureStorageRepository.getObject(key)
        }

        inline operator fun <reified T> setValue(thisRef: R, property: KProperty<*>, value: T) {
            val key = getPropertyKey(property)
            if (value == null) {
                secureStorageRepository.remove(key)
            } else {
                secureStorageRepository.setObject(key, value)
            }
        }

        private fun getPropertyKey(property: KProperty<*>): String {
            val name = property.name.toSnakeCase()
            return "user.$name"
        }
    }
}
