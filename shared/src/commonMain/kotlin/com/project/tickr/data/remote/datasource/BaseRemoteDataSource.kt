package com.project.tickr.data.remote.datasource

import com.project.tickr.core.result.AppError
import com.project.tickr.core.result.DataResult
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.exceptions.HttpRequestException
import io.github.jan.supabase.exceptions.NotFoundRestException
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.exceptions.UnauthorizedRestException
import io.github.jan.supabase.postgrest.postgrest
import io.ktor.client.plugins.HttpRequestTimeoutException
import kotlinx.serialization.SerializationException

abstract class BaseRemoteDataSource(protected val client: SupabaseClient) {

    protected val db get() = client.postgrest

    protected suspend fun <T> safeApiCall(block: suspend () -> T): DataResult<T> =
        try {
            DataResult.Success(block())
        } catch (e: Exception) {
            DataResult.Error(e.toAppError())
        }

    private fun Exception.toAppError(): AppError = when (this) {
        is UnauthorizedRestException -> AppError.Unauthorized
        is NotFoundRestException -> AppError.NotFound(error)
        is RestException -> when (statusCode) {
            401 -> AppError.Unauthorized
            404 -> AppError.NotFound("record")
            else -> AppError.Unknown("HTTP $statusCode: $error")
        }
        is HttpRequestException -> AppError.Network
        is HttpRequestTimeoutException -> AppError.Network
        is SerializationException -> AppError.Serialization(message ?: "serialization error")
        is NoSuchElementException -> AppError.NotFound("record")
        else -> AppError.Unknown(message ?: this::class.simpleName ?: "unknown")
    }
}
