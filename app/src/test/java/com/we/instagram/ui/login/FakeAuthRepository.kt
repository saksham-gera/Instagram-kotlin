package com.we.instagram.ui.login

import com.we.instagram.data.auth.AuthRepository

class FakeAuthRepository(
    private val shouldSucceed: Boolean
) : AuthRepository {

    override fun login(email: String, password: String): Result<Unit> {
        return if (shouldSucceed) {
            Result.success(Unit)
        } else {
            Result.failure(Exception("Invalid credentials"))
        }
    }
}