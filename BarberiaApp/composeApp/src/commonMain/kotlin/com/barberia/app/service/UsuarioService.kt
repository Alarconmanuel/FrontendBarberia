package com.barberia.app.service

import com.barberia.app.dto.AuthResponse
import com.barberia.app.dto.LoginRequest
import com.barberia.app.dto.RegisterRequest
import com.barberia.app.model.Usuario
import com.barberia.app.network.ApiClient
import com.barberia.app.network.ApiException
import com.barberia.app.network.TokenManager
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess

class UsuarioService {

    private val client get() = ApiClient.httpClient

    suspend fun login(correoOTelefono: String, password: String): Result<AuthResponse> {
        return runCatching {
            val response = client.post("/api/auth/login") {
                setBody(LoginRequest(correoOTelefono, password))
            }
            if (response.status.isSuccess()) {
                val auth = response.body<AuthResponse>()
                TokenManager.token = auth.token
                TokenManager.userId = auth.idUsuario
                TokenManager.userName = auth.nombre
                TokenManager.userRole = auth.rol
                auth
            } else {
                throw ApiException.fromResponse(response.status.value, response.bodyAsText())
            }
        }
    }

    suspend fun register(nombre: String, correoOTelefono: String, password: String): Result<AuthResponse> {
        return runCatching {
            val response = client.post("/api/auth/register") {
                setBody(RegisterRequest(nombre, correoOTelefono, password))
            }
            if (response.status.isSuccess()) {
                val auth = response.body<AuthResponse>()
                TokenManager.token = auth.token
                TokenManager.userId = auth.idUsuario
                TokenManager.userName = auth.nombre
                TokenManager.userRole = auth.rol
                auth
            } else {
                throw ApiException.fromResponse(response.status.value, response.bodyAsText())
            }
        }
    }

    suspend fun me(): Result<Usuario> {
        return runCatching {
            val response = client.get("/api/auth/me")
            if (response.status.isSuccess()) response.body<Usuario>()
            else throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }

    suspend fun getAll(): Result<List<Usuario>> {
        return runCatching {
            val response = client.get("/api/usuarios")
            if (response.status.isSuccess()) response.body<List<Usuario>>()
            else throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }

    suspend fun getById(id: Int): Result<Usuario> {
        return runCatching {
            val response = client.get("/api/usuarios/$id")
            if (response.status.isSuccess()) response.body<Usuario>()
            else throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }

    suspend fun update(id: Int, usuario: Usuario): Result<Usuario> {
        return runCatching {
            val response = client.put("/api/usuarios/$id") { setBody(usuario) }
            if (response.status.isSuccess()) response.body<Usuario>()
            else throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }

    suspend fun delete(id: Int): Result<Unit> {
        return runCatching {
            val response = client.delete("/api/usuarios/$id")
            if (!response.status.isSuccess())
                throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }

    fun logout() {
        TokenManager.clear()
    }
}
