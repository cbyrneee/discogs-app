package dev.cbyrne.discogs.common.repository.storage.impl

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.cbyrne.discogs.common.repository.storage.SecureStorageRepository
import javax.inject.Inject

class SecureStorageRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context
) : SecureStorageRepository {
    companion object {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    }

    private val sharedPreferences = EncryptedSharedPreferences.create(
        "secure_shared_prefs",
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override fun set(key: String, value: String) =
        sharedPreferences.edit()
            .putString(key, value)
            .apply()

    override fun get(key: String) =
        sharedPreferences.getString(key, null)

    override fun remove(key: String) {
        sharedPreferences.edit()
            .remove(key)
            .apply()
    }
}