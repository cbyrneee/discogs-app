package dev.cbyrne.discogs.api.service

import dev.cbyrne.discogs.api.model.UserInformationModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("/users/{username}")
    suspend fun retrieve(@Path("username") username: String): Response<UserInformationModel>
}