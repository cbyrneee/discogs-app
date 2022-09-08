package dev.cbyrne.discogs.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.cbyrne.discogs.common.repository.user.UserRepository
import dev.cbyrne.discogs.common.util.BASE_URL
import dev.cbyrne.discogs.common.util.JSON_MEDIA_TYPE
import dev.cbyrne.discogs.common.util.json
import dev.cbyrne.discogs.data.api.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(userRepository: UserRepository): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val newRequest = request.newBuilder()
                    .addHeader("User-Agent", "CustomDiscogsApp/1.0")

                userRepository.credentials?.let {
                    val timestamp = "${System.currentTimeMillis()}"
                    val authorization = mapOf(
                        "oauth_consumer_key" to "jXjOtjTnxNVgHiTDJqoP",
                        "oauth_nonce" to timestamp,
                        "oauth_token" to it.token,
                        "oauth_signature" to "PCKFQRysJsRlutrEfcOGfhwHzbxKBUuv&",
                        "oauth_signature_method" to "PLAINTEXT",
                        "oauth_timestamp" to timestamp,
                        "oauth_verifier" to it.secret
                    ).entries.joinToString(",") { (key, value) -> "${key}=\"${value}\"" }

                    newRequest.addHeader("Authorization", "OAuth $authorization")
                }

                val response = chain.proceed(newRequest.build())
                response
            }
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

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}