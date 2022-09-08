package dev.cbyrne.discogs.common.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.cbyrne.discogs.common.repository.storage.SecureStorageRepository
import dev.cbyrne.discogs.common.repository.storage.impl.SecureStorageRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun getSecureStorageRepository(repository: SecureStorageRepositoryImpl): SecureStorageRepository
}