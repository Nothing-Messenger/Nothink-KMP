package com.nothing.auth.api.data

data class SignUpUiModel(
    val login: String,
    val password: String,
    val passwordRepeat: String,
)