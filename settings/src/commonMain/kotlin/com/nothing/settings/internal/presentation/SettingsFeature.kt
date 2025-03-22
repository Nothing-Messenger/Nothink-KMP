package com.nothing.settings.internal.presentation

import com.nothing.core.feature.CoroutineFeature
import com.nothing.core.flow.AnyStateFlow
import com.nothing.core.flow.wrapToAny
import com.nothing.settings.api.data.SettingsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SettingsFeature() : CoroutineFeature() {

    private val _state = MutableStateFlow<SettingsUiState>(SettingsUiState.Loading)
    val state: AnyStateFlow<SettingsUiState> = _state.wrapToAny()

    init {
        load()
    }

    private fun load() {
        coroutineScope.launch {
            _state.value = SettingsUiState.Data
        }
    }

}