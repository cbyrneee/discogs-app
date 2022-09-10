package dev.cbyrne.discogs.common.repository.user.impl

import dev.cbyrne.discogs.api.model.oauth.OAuthAccessTokenModel
import dev.cbyrne.discogs.api.model.oauth.OAuthIdentityModel
import dev.cbyrne.discogs.api.model.oauth.OAuthRequestTokenModel
import dev.cbyrne.discogs.api.model.user.UserInformationModel
import dev.cbyrne.discogs.api.service.OAuthService
import dev.cbyrne.discogs.api.service.UserService
import dev.cbyrne.discogs.common.data.model.user.UserAuthorizationData
import dev.cbyrne.discogs.common.data.model.user.UserCredentials
import dev.cbyrne.discogs.common.data.model.user.UserIdentity
import dev.cbyrne.discogs.common.network.ApiError
import dev.cbyrne.discogs.common.network.toResult
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
            ?: return Result.failure(ApiError.Unauthorized)

        return userService.retrieve(username).toResult()
    }

    override suspend fun authorize(): Result<OAuthAccessTokenModel> {
        if (credentialsRepository.authorizationData !is UserAuthorizationData.Full) {
            return Result.failure(ApiError.Unauthorized)
        }

        return oauthService.retrieveAccessToken()
            .toResult()
            .onSuccess {
                credentialsRepository.credentials = UserCredentials(
                    token = it.token,
                    secret = it.tokenSecret
                )
            }
    }

    override suspend fun retrieveAuthorizationRequestToken(): Result<OAuthRequestTokenModel> {
        return oauthService.retrieveRequestToken()
            .toResult()
            .onSuccess {
                credentialsRepository.authorizationData =
                    UserAuthorizationData.Partial(it.tokenSecret)
            }
    }

    override suspend fun retrieveIdentity(): Result<OAuthIdentityModel> {
        if (credentialsRepository.credentials == null) {
            return Result.failure(ApiError.Unauthorized)
        }

        return oauthService.retrieveIdentity()
            .toResult()
            .onSuccess {
                identity = UserIdentity(it.id, it.username)
            }
    }
}
