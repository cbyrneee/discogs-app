package dev.cbyrne.discogs.common.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.cbyrne.discogs.common.network.OAuthInterceptor
import dev.cbyrne.discogs.common.repository.credentials.CredentialsRepository
import dev.cbyrne.discogs.common.util.BASE_URL
import dev.cbyrne.discogs.common.util.JSON_MEDIA_TYPE
import dev.cbyrne.discogs.common.util.json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(credentialsRepository: CredentialsRepository): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("User-Agent", "CustomDiscogsApp/1.0")
                    .build()

                chain.proceed(request)
            }
            .addInterceptor(OAuthInterceptor(credentialsRepository))
            .addInterceptor(HttpLoggingInterceptor().setLevel(Level.BODY))
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(JSON_MEDIA_TYPE))
            .build()
}