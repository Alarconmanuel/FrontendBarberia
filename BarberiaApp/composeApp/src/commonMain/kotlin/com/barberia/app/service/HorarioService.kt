package com.barberia.app.service

import com.barberia.app.model.Horario
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

class HorarioService {

    private val client get() = ApiClient.httpClient

    suspend fun getByBarbero(idBarbero: Int): Result<List<Horario>> {
        return runCatching {
            val response = client.get("/api/horarios/barbero/$idBarbero")
            if (response.status.isSuccess()) response.body<List<Horario>>()
            else throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }

    suspend fun crear(horario: Horario): Result<Horario> {
        return runCatching {
            val response = client.post("/api/horarios") { setBody(horario) }
            if (response.status.isSuccess()) response.body<Horario>()
            else throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }

    suspend fun update(id: Int, horario: Horario): Result<Horario> {
        return runCatching {
            val response = client.put("/api/horarios/$id") { setBody(horario) }
            if (response.status.isSuccess()) response.body<Horario>()
            else throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }

    suspend fun delete(id: Int): Result<Unit> {
        return runCatching {
            val response = client.delete("/api/horarios/$id")
            if (!response.status.isSuccess())
                throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }
}
