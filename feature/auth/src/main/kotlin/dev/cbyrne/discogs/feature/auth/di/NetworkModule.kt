package dev.cbyrne.discogs.feature.auth.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.cbyrne.discogs.feature.auth.data.api.OauthApiService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOauthApiService(retrofit: Retrofit): OauthApiService =
        retrofit.create(OauthApiService::class.java)
}