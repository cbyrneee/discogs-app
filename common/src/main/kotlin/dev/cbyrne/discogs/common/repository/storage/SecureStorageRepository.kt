package dev.cbyrne.discogs.common.repository.storage

import dev.cbyrne.discogs.common.util.json
import dev.cbyrne.discogs.common.util.toSnakeCase
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlin.reflect.KProperty

interface SecureStorageRepository {
    fun set(key: String, value: String)
    fun get(key: String): String?
    fun remove(key: String)
}

inline fun <reified T> SecureStorageRepository.getObject(key: String): T? {
    val string = get(key) ?: return null
    return kotlin.runCatching { json.decodeFromString<T>(string) }.getOrNull()
}

inline fun <reified T> SecureStorageRepository.setObject(key: String, value: T) {
    val json = json.encodeToString(value)
    set(key, json)
}

open class SecureStorageBacked(
    private val secureStorageRepository: SecureStorageRepository,
    private val namespace: String
) {
    internal fun <T> secureStorage(): SecureStorageDelegate<T> = SecureStorageDelegate()

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

        fun getPropertyKey(property: KProperty<*>): String {
            val name = property.name.toSnakeCase()
            return "$namespace.$name"
        }
    }
}