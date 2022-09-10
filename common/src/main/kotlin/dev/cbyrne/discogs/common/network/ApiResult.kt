package dev.cbyrne.discogs.common.network

import dev.cbyrne.discogs.common.data.model.ErrorModel
import dev.cbyrne.discogs.common.util.json
import kotlinx.serialization.decodeFromString
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

sealed class ApiError : Error() {
    data class Specific(val data: ErrorModel) : ApiError()
    data class Generic(val code: Int, override val message: String? = "Unknown Error") : ApiError()

    object Unauthorized : ApiError()
    object NotFound : ApiError()
}

fun <T> Response<T>.toResult(): Result<T> {
    try {
        val body = body()

        if (!isSuccessful) {
            return handleError()
        }

        if (body == null) {
            return genericError()
        }

        return Result.success(body)
    } catch (e: HttpException) {
        return genericError()
    } catch (e: IOException) {
        e.printStackTrace()
        return Result.failure(ApiError.Generic(-1))
    }
}

private fun <T> Response<T>.handleError(): Result<T> {
    return Result.failure(
        when (code()) {
            401 -> ApiError.Unauthorized
            404 -> ApiError.NotFound
            else -> {
                val data = serializeError(this) ?: return genericError()
                ApiError.Specific(data)
            }
        }
    )
}

private fun <T> Response<T>.genericError(): Result<T> {
    return Result.failure(ApiError.Generic(code(), message()))
}

private fun <T> serializeError(response: Response<T>): ErrorModel? {
    val errorBody = response.errorBody() ?: return null
    return runCatching { json.decodeFromString<ErrorModel>(errorBody.string()) }.getOrNull()
}
