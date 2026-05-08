package com.barberia.app.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Resena(
    @SerialName("idResena") val idResena: Int? = null,
    @SerialName("idUsuario") val idUsuario: Int,
    @SerialName("idBarbero") val idBarbero: Int,
    val puntuacion: Int,
    val comentario: String? = null,
    @SerialName("nombreUsuario") val nombreUsuario: String? = null
)
