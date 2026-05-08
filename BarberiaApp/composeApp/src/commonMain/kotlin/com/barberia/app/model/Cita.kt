package com.barberia.app.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cita(
    @SerialName("idCita") val idCita: Int? = null,
    @SerialName("idUsuario") val idUsuario: Int,
    @SerialName("idBarbero") val idBarbero: Int,
    @SerialName("idServicio") val idServicio: Int,
    val fecha: String,
    @SerialName("horaInicio") val horaInicio: String,
    @SerialName("horaFin") val horaFin: String? = null,
    val estado: EstadoCitaEnum = EstadoCitaEnum.PENDIENTE,
    @SerialName("nombreBarbero") val nombreBarbero: String? = null,
    @SerialName("nombreServicio") val nombreServicio: String? = null,
    @SerialName("precioServicio") val precioServicio: Double? = null
)
