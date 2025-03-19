package com.nothing.auth.internal.domain.model

data class User (
    val id: String,
    val name: String,
    val surname: String? = null
)