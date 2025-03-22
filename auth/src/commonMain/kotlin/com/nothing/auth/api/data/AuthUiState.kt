package com.nothing.auth.api.data

sealed class AuthUiState {

    object Loading : AuthUiState()
    object Error : AuthUiState()
    object Empty : AuthUiState()

    //data class Data(val authUiModel: AuthUiModel) : AuthUiState()

    data class SignIn(val credentialUiModel: CredentialUiModel) : AuthUiState()
    data class SignUp(val credentialUiModel: CredentialUiModel) : AuthUiState()

    object ForgotPassword : AuthUiState()
}