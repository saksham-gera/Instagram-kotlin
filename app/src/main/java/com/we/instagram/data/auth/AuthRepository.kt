package com.we.instagram.data.auth

interface AuthRepository {
    fun login(email: String, password: String): Result<Unit>
}