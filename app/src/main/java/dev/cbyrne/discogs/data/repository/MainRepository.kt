package dev.cbyrne.discogs.data.repository

import dev.cbyrne.discogs.data.model.releases.ReleaseModel
import retrofit2.Response

interface MainRepository {
    suspend fun getRelease(id: String): Response<ReleaseModel>
}