package dev.cbyrne.discogs.feature.auth.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.cbyrne.discogs.feature.auth.data.repository.OauthRepository
import dev.cbyrne.discogs.feature.auth.data.repository.SecureStorageRepository
import dev.cbyrne.discogs.feature.auth.data.repository.impl.OauthRepositoryImpl
import dev.cbyrne.discogs.feature.auth.data.repository.impl.SecureStorageRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun getOauthRepository(repository: OauthRepositoryImpl): OauthRepository

    @Binds
    abstract fun getSecureStorageRepository(repository: SecureStorageRepositoryImpl): SecureStorageRepository
}