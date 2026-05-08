package com.barberia.app.service

import com.barberia.app.model.Barbero
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

class BarberoService {

    private val client get() = ApiClient.httpClient

    suspend fun getAll(): Result<List<Barbero>> {
        return runCatching {
            val response = client.get("/api/barberos")
            if (response.status.isSuccess()) response.body<List<Barbero>>()
            else throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }

    suspend fun getById(id: Int): Result<Barbero> {
        return runCatching {
            val response = client.get("/api/barberos/$id")
            if (response.status.isSuccess()) response.body<Barbero>()
            else throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }

    suspend fun crear(barbero: Barbero): Result<Barbero> {
        return runCatching {
            val response = client.post("/api/barberos") { setBody(barbero) }
            if (response.status.isSuccess()) response.body<Barbero>()
            else throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }

    suspend fun update(id: Int, barbero: Barbero): Result<Barbero> {
        return runCatching {
            val response = client.put("/api/barberos/$id") { setBody(barbero) }
            if (response.status.isSuccess()) response.body<Barbero>()
            else throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }

    suspend fun delete(id: Int): Result<Unit> {
        return runCatching {
            val response = client.delete("/api/barberos/$id")
            if (!response.status.isSuccess())
                throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }
}
