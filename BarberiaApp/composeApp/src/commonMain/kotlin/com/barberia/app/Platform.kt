package com.barberia.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform