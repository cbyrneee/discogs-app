package dev.cbyrne.discogs.common.repository.storage

interface SecureStorageRepository {
    fun set(key: String, value: String)
    fun get(key: String): String?
    fun remove(key: String)
}