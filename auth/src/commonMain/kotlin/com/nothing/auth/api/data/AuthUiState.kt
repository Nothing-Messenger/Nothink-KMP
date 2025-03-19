package com.nothing.auth.api.data

import com.nothing.auth.internal.domain.model.User

sealed class AuthUiState {

    object Loading : AuthUiState()
    object Error : AuthUiState()
    object Empty : AuthUiState()

    data class Data(val authUiModel: AuthUiModel) : AuthUiState()
}