package dev.cbyrne.discogs.common.repository.storage

import dev.cbyrne.discogs.common.util.json
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

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
