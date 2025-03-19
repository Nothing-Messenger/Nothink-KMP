package com.nothing.auth.api

import com.nothing.auth.api.data.AuthUiState
import com.nothing.core.flow.AnyStateFlow

interface AuthComponent {
    val state: AnyStateFlow<AuthUiState>

    fun onLoginClicked()
    fun onRegisterClicked()

}

