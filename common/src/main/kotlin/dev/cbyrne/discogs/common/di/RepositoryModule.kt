package dev.cbyrne.discogs.common.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.cbyrne.discogs.common.repository.storage.SecureStorageRepository
import dev.cbyrne.discogs.common.repository.storage.impl.SecureStorageRepositoryImpl
import dev.cbyrne.discogs.common.repository.user.UserRepository
import dev.cbyrne.discogs.common.repository.user.impl.UserRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun getSecureStorageRepository(repository: SecureStorageRepositoryImpl): SecureStorageRepository

    @Binds
    abstract fun getUserRepository(repository: UserRepositoryImpl): UserRepository
}