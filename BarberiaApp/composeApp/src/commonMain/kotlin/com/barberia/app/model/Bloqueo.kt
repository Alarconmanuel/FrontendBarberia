package com.barberia.app.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Bloqueo(
    @SerialName("idBloqueo") val idBloqueo: Int? = null,
    @SerialName("idBarbero") val idBarbero: Int,
    val fecha: String,
    @SerialName("horaInicio") val horaInicio: String,
    @SerialName("horaFin") val horaFin: String,
    val motivo: String? = null
)
