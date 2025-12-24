package com.we.instagram.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.we.instagram.data.auth.AuthRepository
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `login success updates state to Success`() {
        val fakeRepo = FakeAuthRepository(true)
        val viewModel = LoginViewModel(fakeRepo)

        viewModel.login("test@test.com", "123456")

        assertTrue(viewModel.loginState.value is LoginState.Success)
    }

    @Test
    fun `login failure updates state to Error`() {
        val fakeRepo = FakeAuthRepository(false)
        val viewModel = LoginViewModel(fakeRepo)

        viewModel.login("wrong@test.com", "wrong")

        assertTrue(viewModel.loginState.value is LoginState.Error)
    }
}