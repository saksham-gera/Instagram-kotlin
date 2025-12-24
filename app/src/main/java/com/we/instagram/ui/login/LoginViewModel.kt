package com.we.instagram.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.we.instagram.data.auth.AuthRepository

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState

    fun login(email: String, password: String) {
        _loginState.value = LoginState.Loading

        val result = authRepository.login(email, password)

        _loginState.value = result.fold(
            onSuccess = { LoginState.Success },
            onFailure = {
                LoginState.Error(it.message ?: "Invalid credentials. Please try again.")
            }
        )
    }
}