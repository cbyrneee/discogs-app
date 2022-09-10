package dev.cbyrne.discogs.api.service

import dev.cbyrne.discogs.api.model.oauth.OAuthAccessTokenModel
import dev.cbyrne.discogs.api.model.oauth.OAuthIdentityModel
import dev.cbyrne.discogs.api.model.oauth.OAuthRequestTokenModel
import retrofit2.Response
import retrofit2.http.GET

interface OAuthService {
    @GET("oauth/request_token")
    suspend fun retrieveRequestToken(): Response<OAuthRequestTokenModel>

    @GET("oauth/access_token")
    suspend fun retrieveAccessToken(): Response<OAuthAccessTokenModel>

    @GET("oauth/identity")
    suspend fun retrieveIdentity(): Response<OAuthIdentityModel>
}