package com.barberia.app.service

import com.barberia.app.model.Servicio
import com.barberia.app.network.ApiClient
import com.barberia.app.network.ApiException
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess

class ServicioService {

    private val client get() = ApiClient.httpClient

    suspend fun getAll(): Result<List<Servicio>> {
        return runCatching {
            val response = client.get("/api/servicios")
            if (response.status.isSuccess()) response.body<List<Servicio>>()
            else throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }

    suspend fun getById(id: Int): Result<Servicio> {
        return runCatching {
            val response = client.get("/api/servicios/$id")
            if (response.status.isSuccess()) response.body<Servicio>()
            else throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }

    suspend fun crear(servicio: Servicio): Result<Servicio> {
        return runCatching {
            val response = client.post("/api/servicios") { setBody(servicio) }
            if (response.status.isSuccess()) response.body<Servicio>()
            else throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }

    suspend fun update(id: Int, servicio: Servicio): Result<Servicio> {
        return runCatching {
            val response = client.put("/api/servicios/$id") { setBody(servicio) }
            if (response.status.isSuccess()) response.body<Servicio>()
            else throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }

    suspend fun delete(id: Int): Result<Unit> {
        return runCatching {
            val response = client.delete("/api/servicios/$id")
            if (!response.status.isSuccess())
                throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }
}
