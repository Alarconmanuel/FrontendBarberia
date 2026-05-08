package com.barberia.app.network

import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

class ApiException(val statusCode: Int, override val message: String) : Exception(message) {

    companion object {
        private val json = Json { ignoreUnknownKeys = true }

        suspend fun fromResponse(statusCode: Int, body: String): ApiException {
            val msg = try {
                val error = json.decodeFromString<ErrorBody>(body)
                error.mensaje ?: error.error ?: error.message ?: "Error desconocido"
            } catch (_: Exception) {
                "Error inesperado del servidor"
            }
            return ApiException(statusCode, msg)
        }
    }
}

private data class ErrorBody(
    val mensaje: String? = null,
    val error: String? = null,
    val message: String? = null
)
