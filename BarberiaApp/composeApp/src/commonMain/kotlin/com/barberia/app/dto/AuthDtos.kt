package com.barberia.app.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    @SerialName("correoOTelefono") val correoOTelefono: String,
    val password: String
)

@Serializable
data class RegisterRequest(
    val nombre: String,
    @SerialName("correoOTelefono") val correoOTelefono: String,
    val password: String
)

@Serializable
data class AuthResponse(
    val token: String,
    val type: String,
    @SerialName("idUsuario") val idUsuario: Int,
    val nombre: String,
    @SerialName("correo") val correo: String? = null,
    val rol: String
)
