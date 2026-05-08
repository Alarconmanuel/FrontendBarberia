package com.barberia.app.model

import kotlinx.serialization.Serializable

@Serializable
enum class EstadoCitaEnum {
    PENDIENTE,
    CONFIRMADA,
    EN_PROGRESO,
    FINALIZADA,
    CANCELADA,
    NO_PRESENTO
}
