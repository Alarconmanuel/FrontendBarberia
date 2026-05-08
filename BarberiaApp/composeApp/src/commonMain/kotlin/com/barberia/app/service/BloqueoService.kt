package com.barberia.app.service

import com.barberia.app.model.Bloqueo
import com.barberia.app.network.ApiClient
import com.barberia.app.network.ApiException
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess

class BloqueoService {

    private val client get() = ApiClient.httpClient

    suspend fun getByBarbero(idBarbero: Int): Result<List<Bloqueo>> {
        return runCatching {
            val response = client.get("/api/bloqueos/barbero/$idBarbero")
            if (response.status.isSuccess()) response.body<List<Bloqueo>>()
            else throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }

    suspend fun crear(bloqueo: Bloqueo): Result<Bloqueo> {
        return runCatching {
            val response = client.post("/api/bloqueos") { setBody(bloqueo) }
            if (response.status.isSuccess()) response.body<Bloqueo>()
            else throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }

    suspend fun delete(id: Int): Result<Unit> {
        return runCatching {
            val response = client.delete("/api/bloqueos/$id")
            if (!response.status.isSuccess())
                throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }
}
