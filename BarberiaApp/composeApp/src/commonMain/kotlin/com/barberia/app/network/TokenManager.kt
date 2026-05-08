package com.barberia.app.network

import com.russhwolf.settings.Settings

object TokenManager {

    private val settings: Settings by lazy { Settings() }

    private const val KEY_TOKEN = "auth_token"
    private const val KEY_USER_ID = "auth_user_id"
    private const val KEY_USER_NAME = "auth_user_name"
    private const val KEY_USER_ROLE = "auth_user_role"

    var token: String?
        get() = settings.getStringOrNull(KEY_TOKEN)
        set(value) {
            if (value != null) settings.putString(KEY_TOKEN, value)
            else settings.remove(KEY_TOKEN)
        }

    var userId: Int?
        get() = settings.getStringOrNull(KEY_USER_ID)?.toIntOrNull()
        set(value) {
            if (value != null) settings.putString(KEY_USER_ID, value.toString())
            else settings.remove(KEY_USER_ID)
        }

    var userName: String?
        get() = settings.getStringOrNull(KEY_USER_NAME)
        set(value) {
            if (value != null) settings.putString(KEY_USER_NAME, value)
            else settings.remove(KEY_USER_NAME)
        }

    var userRole: String?
        get() = settings.getStringOrNull(KEY_USER_ROLE)
        set(value) {
            if (value != null) settings.putString(KEY_USER_ROLE, value)
            else settings.remove(KEY_USER_ROLE)
        }

    fun clear() {
        settings.remove(KEY_TOKEN)
        settings.remove(KEY_USER_ID)
        settings.remove(KEY_USER_NAME)
        settings.remove(KEY_USER_ROLE)
    }

    val isLoggedIn: Boolean get() = token != null
}
