package com.barberia.app.model

import kotlinx.serialization.Serializable

@Serializable
enum class RolEnum {
    CLIENTE,
    BARBERO,
    ADMINISTRADOR,
    SUPERADMIN
}
