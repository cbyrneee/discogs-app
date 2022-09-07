package dev.cbyrne.discogs.data.repository.impl

import dev.cbyrne.discogs.data.api.ApiService
import dev.cbyrne.discogs.data.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MainRepository {
    override suspend fun getRelease(id: String) = apiService.getRelease(id)
}