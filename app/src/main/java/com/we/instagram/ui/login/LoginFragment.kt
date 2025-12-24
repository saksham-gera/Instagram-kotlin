package com.we.instagram.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.we.instagram.R

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var viewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewModel initialization
        val factory = LoginViewModelFactory(requireContext())
        viewModel = ViewModelProvider(this, factory)
            .get(LoginViewModel::class.java)

        val emailEt = view.findViewById<EditText>(R.id.etEmail)
        val passwordEt = view.findViewById<EditText>(R.id.etPassword)
        val loginBtn = view.findViewById<Button>(R.id.btnLogin)

        loginBtn.setOnClickListener {
            viewModel.login(
                emailEt.text.toString(),
                passwordEt.text.toString()
            )
        }

        viewModel.loginState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoginState.Loading -> {
                    // optional: show loading
                }
                is LoginState.Success -> {
                    findNavController()
                        .navigate(R.id.action_login_to_feed)
                }
                is LoginState.Error -> {
                    Toast.makeText(
                        requireContext(),
                        state.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}