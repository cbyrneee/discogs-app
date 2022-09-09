package dev.cbyrne.discogs.feature.auth.data.api

import dev.cbyrne.discogs.feature.auth.data.model.OauthAccessTokenModel
import dev.cbyrne.discogs.feature.auth.data.model.OauthIdentityModel
import dev.cbyrne.discogs.feature.auth.data.model.OauthTokenModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Headers

interface OauthApiService {
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @GET("oauth/request_token")
    suspend fun getRequestToken(@HeaderMap headers: Map<String, String>): Response<OauthTokenModel>

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @GET("oauth/access_token")
    suspend fun getAccessToken(@HeaderMap headers: Map<String, String>): Response<OauthAccessTokenModel>

    @Headers("Content-Type: application/json")
    @GET("oauth/identity")
    suspend fun getIdentity(): Response<OauthIdentityModel>
}