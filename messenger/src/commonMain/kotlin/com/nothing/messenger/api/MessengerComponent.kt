package com.nothing.messenger.api

import com.nothing.core.flow.AnyStateFlow
import com.nothing.messenger.api.data.MessengerUiState

interface MessengerComponent {
    val state: AnyStateFlow<MessengerUiState>

    fun onLogOutClicked()

}