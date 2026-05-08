package com.barberia.app.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Horario(
    @SerialName("idHorario") val idHorario: Int? = null,
    @SerialName("idBarbero") val idBarbero: Int,
    val diaSemana: String,
    @SerialName("horaInicio") val horaInicio: String,
    @SerialName("horaFin") val horaFin: String
)
