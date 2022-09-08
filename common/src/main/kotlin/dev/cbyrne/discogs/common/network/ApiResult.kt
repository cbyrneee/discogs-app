package dev.cbyrne.discogs.common.network

import dev.cbyrne.discogs.common.data.model.ErrorModel
import dev.cbyrne.discogs.common.util.json
import kotlinx.serialization.decodeFromString
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

sealed class ApiResult<T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val code: Int, val message: String? = "Unknown error") : ApiResult<Nothing>()
    object NotFound : ApiResult<Nothing>()
}

suspend fun <T> handleApiResponse(execute: suspend () -> Response<T>): ApiResult<out T> {
    return try {
        val response = execute()
        val body = response.body()

        // If the response code is not 2xx, we return a different data type
        if (!response.isSuccessful) {
            return when (response.code()) {
                404 -> ApiResult.NotFound
                else -> return handleResponseError(response)
            }
        }

        // If we have a body and the request code is 2xx
        if (body != null) {
            return ApiResult.Success(body)
        }

        handleGenericResponseError(response)
    } catch (e: HttpException) {
        return handleHttpException(e)
    } catch (e: IOException) {
        e.printStackTrace()
        handleUnknownError()
    }
}

private fun <T> handleResponseError(response: Response<T>): ApiResult<out T> {
    val error = serializeError(response) ?: return handleGenericResponseError(response)
    return ApiResult.Error(response.code(), error.message)
}

private fun <T> serializeError(response: Response<T>): ErrorModel? {
    val errorBody = response.errorBody() ?: return null
    return runCatching { json.decodeFromString<ErrorModel>(errorBody.string()) }.getOrNull()
}

private fun <T> handleGenericResponseError(response: Response<T>): ApiResult<out T> =
    ApiResult.Error(response.code(), response.message())

private fun <T> handleUnknownError(): ApiResult<out T> =
    ApiResult.Error(code = -1, message = "Unknown error")

private fun <T> handleHttpException(e: HttpException): ApiResult<out T> =
    ApiResult.Error(e.code(), e.message())