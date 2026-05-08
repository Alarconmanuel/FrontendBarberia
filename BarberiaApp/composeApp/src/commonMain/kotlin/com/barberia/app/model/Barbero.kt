package com.barberia.app.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Barbero(
    @SerialName("idBarbero") val idBarbero: Int,
    val nombre: String,
    val especialidad: String? = null,
    val activo: Boolean = true,
    @SerialName("urlFoto") val urlFoto: String? = null
)
