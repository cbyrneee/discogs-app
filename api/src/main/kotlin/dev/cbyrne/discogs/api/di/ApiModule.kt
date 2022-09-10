package dev.cbyrne.discogs.api.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.cbyrne.discogs.api.service.OAuthService
import dev.cbyrne.discogs.api.service.UserCollectionService
import dev.cbyrne.discogs.api.service.UserService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun provideOAuthService(retrofit: Retrofit): OAuthService =
        retrofit.create(OAuthService::class.java)

    @Provides
    @Singleton
    fun provideUserCollectionService(retrofit: Retrofit): UserCollectionService =
        retrofit.create(UserCollectionService::class.java)
}