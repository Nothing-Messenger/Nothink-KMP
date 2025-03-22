package com.nothing.settings.api.data

sealed class SettingsUiState {
    object Loading : SettingsUiState()
    object Error : SettingsUiState()
    object Empty : SettingsUiState()
    object Data : SettingsUiState()
}