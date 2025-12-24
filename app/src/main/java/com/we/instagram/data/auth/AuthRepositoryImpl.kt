package com.we.instagram.data.auth

import com.we.instagram.data.prefs.SessionManager

class AuthRepositoryImpl(
    private val sessionManager: SessionManager
) : AuthRepository {

    override fun login(email: String, password: String): Result<Unit> {
        return if (email == "test@test.com" && password == "123456") {
            sessionManager.setLoggedIn(true)
            Result.success(Unit)
        } else {
            Result.failure(Exception("Invalid credentials"))
        }
    }
}