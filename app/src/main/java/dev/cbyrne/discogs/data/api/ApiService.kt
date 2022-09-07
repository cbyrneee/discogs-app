package dev.cbyrne.discogs.data.api

import dev.cbyrne.discogs.data.model.releases.ReleaseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/releases/{id}")
    suspend fun getRelease(@Path("id") id: String): Response<ReleaseModel>
}