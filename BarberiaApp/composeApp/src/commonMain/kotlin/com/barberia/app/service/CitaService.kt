package com.barberia.app.service

import com.barberia.app.model.Cita
import com.barberia.app.network.ApiClient
import com.barberia.app.network.ApiException
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess

class CitaService {

    private val client get() = ApiClient.httpClient

    suspend fun crear(cita: Cita): Result<Cita> {
        return runCatching {
            val response = client.post("/api/citas") { setBody(cita) }
            if (response.status.isSuccess()) response.body<Cita>()
            else throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }

    suspend fun getCitasByUsuario(idUsuario: Int): Result<List<Cita>> {
        return runCatching {
            val response = client.get("/api/citas/usuario/$idUsuario")
            if (response.status.isSuccess()) response.body<List<Cita>>()
            else throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }

    suspend fun iniciar(idCita: Int): Result<Cita> {
        return runCatching {
            val response = client.patch("/api/citas/$idCita/iniciar")
            if (response.status.isSuccess()) response.body<Cita>()
            else throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }

    suspend fun finalizar(idCita: Int): Result<Cita> {
        return runCatching {
            val response = client.patch("/api/citas/$idCita/finalizar")
            if (response.status.isSuccess()) response.body<Cita>()
            else throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }

    suspend fun cancelar(idCita: Int): Result<Cita> {
        return runCatching {
            val response = client.patch("/api/citas/$idCita/cancelar")
            if (response.status.isSuccess()) response.body<Cita>()
            else throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }

    suspend fun noPresento(idCita: Int): Result<Cita> {
        return runCatching {
            val response = client.patch("/api/citas/$idCita/no-presento")
            if (response.status.isSuccess()) response.body<Cita>()
            else throw ApiException.fromResponse(response.status.value, response.bodyAsText())
        }
    }
}
