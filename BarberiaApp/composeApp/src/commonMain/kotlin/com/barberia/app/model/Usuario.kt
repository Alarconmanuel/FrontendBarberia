package com.barberia.app.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Usuario(
    @SerialName("idUsuario") val idUsuario: Int? = null,
    val nombre: String,
    @SerialName("correoOTelefono") val correoOTelefono: String,
    val password: String? = null,
    val rol: RolEnum = RolEnum.CLIENTE,
    val activo: Boolean = true
)
