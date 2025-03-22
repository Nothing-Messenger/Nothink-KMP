package com.nothing.community.community.api.data

sealed class ChatUiState {
    object Loading : ChatUiState()
    object Error : ChatUiState()
    object Empty : ChatUiState()
    object Data : ChatUiState()
}