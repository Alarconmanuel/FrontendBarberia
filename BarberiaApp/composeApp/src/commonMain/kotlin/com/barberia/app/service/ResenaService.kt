package com.barberia.app.service

import com.barberia.app.model.Resena
import com.barberia.app.network.ApiClient
import com.barberia.app.network.ApiException
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess

class ResenaService {

    private val client get() = ApiClient.httpClient

    suspend fun crear(resena: Resena): Result<Resena> {
        return runCatching {
            val response = client.post("/api/resenas") { setBody(resena) }
            if (response.status.isSuccess()) response.body<Resena>()
            else throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }

    suspend fun getByBarbero(idBarbero: Int): Result<List<Resena>> {
        return runCatching {
            val response = client.get("/api/resenas/barbero/$idBarbero")
            if (response.status.isSuccess()) response.body<List<Resena>>()
            else throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }
}
