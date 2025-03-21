package com.nothing.messenger.api.data

sealed class MessengerUiState {

    object Loading : MessengerUiState()
    object Error : MessengerUiState()
    object Empty : MessengerUiState()

    data class Data(val messengerUiModel: MessengerUiModel) : MessengerUiState()
}