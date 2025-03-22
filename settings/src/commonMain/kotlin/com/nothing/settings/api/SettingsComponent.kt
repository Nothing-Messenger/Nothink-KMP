package com.nothing.settings.api

import com.nothing.core.flow.AnyStateFlow
import com.nothing.settings.api.data.SettingsUiState


interface SettingsComponent {
    val state: AnyStateFlow<SettingsUiState>
}