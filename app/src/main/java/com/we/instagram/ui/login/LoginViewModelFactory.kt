package com.we.instagram.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.we.instagram.data.auth.AuthRepositoryImpl
import com.we.instagram.data.prefs.SessionManager

class LoginViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            val sessionManager = SessionManager(context)
            val authRepository = AuthRepositoryImpl(sessionManager)
            return LoginViewModel(authRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}