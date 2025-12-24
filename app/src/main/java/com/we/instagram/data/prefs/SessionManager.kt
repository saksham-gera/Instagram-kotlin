package com.we.instagram.data.prefs

import android.content.Context

class SessionManager(context: Context) {

    private val prefs = context.getSharedPreferences(
        "app_session",
        Context.MODE_PRIVATE
    )

    fun isLoggedIn(): Boolean =
        prefs.getBoolean(KEY_IS_LOGGED_IN, false)

    fun setLoggedIn(loggedIn: Boolean) {
        prefs.edit().putBoolean(KEY_IS_LOGGED_IN, loggedIn).apply()
    }

    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }
}