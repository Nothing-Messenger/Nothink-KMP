package com.nothing.auth.internal.presentation

import com.nothing.auth.api.data.AuthUiModel
import com.nothing.auth.api.data.AuthUiState
import com.nothing.auth.internal.domain.model.User
import com.nothing.core.feature.CoroutineFeature
import com.nothing.core.flow.AnyStateFlow
import com.nothing.core.flow.wrapToAny
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

internal class AuthFeature : CoroutineFeature() {

    private val _state = MutableStateFlow<AuthUiState>(AuthUiState.Loading)
    val state: AnyStateFlow<AuthUiState> = _state.wrapToAny()

    init {
        load()
    }

    private fun load() {
        coroutineScope.launch {
            delay(3000)
            _state.value = AuthUiState.Data(AuthUiModel(user = User(id = "1", name = "Nikita")))
        }
    }

}