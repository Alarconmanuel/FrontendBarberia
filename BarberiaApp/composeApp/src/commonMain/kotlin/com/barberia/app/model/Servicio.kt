package com.barberia.app.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Servicio(
    @SerialName("idServicio") val idServicio: Int,
    val nombre: String,
    val precio: Double,
    val duracion: String? = null,
    val activo: Boolean = true
)
