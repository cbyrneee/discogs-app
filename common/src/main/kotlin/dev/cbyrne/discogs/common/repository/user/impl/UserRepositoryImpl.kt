package dev.cbyrne.discogs.common.repository.user.impl

import dev.cbyrne.discogs.api.model.oauth.OAuthRequestTokenModel
import dev.cbyrne.discogs.api.model.user.UserInformationModel
import dev.cbyrne.discogs.api.service.OAuthService
import dev.cbyrne.discogs.api.service.UserService
import dev.cbyrne.discogs.common.data.model.user.UserAuthorizationData
import dev.cbyrne.discogs.common.data.model.user.UserCredentials
import dev.cbyrne.discogs.common.data.model.user.UserIdentity
import dev.cbyrne.discogs.common.network.ApiResult
import dev.cbyrne.discogs.common.network.handleApiResponse
import dev.cbyrne.discogs.common.repository.credentials.CredentialsRepository
import dev.cbyrne.discogs.common.repository.storage.SecureStorageBacked
import dev.cbyrne.discogs.common.repository.storage.SecureStorageRepository
import dev.cbyrne.discogs.common.repository.user.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val credentialsRepository: CredentialsRepository,
    private val oauthService: OAuthService,
    private val userService: UserService,
    secureStorageRepository: SecureStorageRepository
) : UserRepository, SecureStorageBacked(secureStorageRepository, "user") {
    override var identity: UserIdentity? by secureStorage()

    override suspend fun current(): Result<UserInformationModel> {
        val username = identity?.username
            ?: return Result.failure(Error("Not logged in"))

        return when (val result = handleApiResponse { userService.retrieve(username) }) {
            is ApiResult.Success -> Result.success(result.data)
            is ApiResult.NotFound -> Result.failure(Error("Not logged in"))
            is ApiResult.Error -> Result.failure(Error(result.message))
        }
    }

    override suspend fun authorize(): Result<Nothing?> {
        if (credentialsRepository.authorizationData !is UserAuthorizationData.Full) {
            return Result.failure(Error("Not logged in"))
        }

        return when (val result = handleApiResponse { oauthService.retrieveAccessToken() }) {
            is ApiResult.Success -> {
                credentialsRepository.credentials = UserCredentials(
                    token = result.data.token,
                    secret = result.data.tokenSecret
                )

                Result.success(null)
            }
            is ApiResult.NotFound -> Result.failure(Error("Not logged in"))
            is ApiResult.Error -> Result.failure(Error(result.message))
        }
    }

    override suspend fun retrieveAuthorizationRequestToken(): Result<OAuthRequestTokenModel> {
        return when (val result = handleApiResponse { oauthService.retrieveRequestToken() }) {
            is ApiResult.Success -> {
                credentialsRepository.authorizationData = UserAuthorizationData.Partial(
                    secret = result.data.tokenSecret
                )

                Result.success(result.data)
            }
            is ApiResult.NotFound -> Result.failure(Error("Not logged in"))
            is ApiResult.Error -> Result.failure(Error(result.message))
        }
    }

    override suspend fun retrieveIdentity(): Result<Nothing?> {
        if (credentialsRepository.credentials == null) {
            return Result.failure(Error("Not logged in"))
        }

        return when (val result = handleApiResponse { oauthService.retrieveIdentity() }) {
            is ApiResult.Success -> {
                identity = UserIdentity(result.data.id, result.data.username)
                Result.success(null)
            }
            is ApiResult.NotFound -> Result.failure(Error("Not logged in"))
            is ApiResult.Error -> Result.failure(Error(result.message))
        }
    }
}
