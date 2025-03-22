package com.nothing.auth.api

import com.nothing.auth.api.data.AuthUiState
import com.nothing.core.flow.AnyStateFlow

interface AuthComponent {
    val state: AnyStateFlow<AuthUiState>

    fun onLoginChanged(login: String)
    fun onPasswordChanged(password: String)
    fun onPasswordRepeatChanged(password: String)

    fun onLoginClicked()
    fun onRegisterClicked()

    fun onForgotPasswordClicked()
    fun showSignIn()
    fun showSignUp()
}

