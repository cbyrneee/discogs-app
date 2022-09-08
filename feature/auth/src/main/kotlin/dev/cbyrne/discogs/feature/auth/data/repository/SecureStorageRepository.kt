package dev.cbyrne.discogs.feature.auth.data.repository

interface SecureStorageRepository {
    fun set(key: String, value: String)
    fun get(key: String): String?
    fun remove(key: String)
}