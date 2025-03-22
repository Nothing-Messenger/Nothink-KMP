package com.nothing.contacts.api.data

sealed class ContactsUiState {
    object Loading : ContactsUiState()
    object Error : ContactsUiState()
    object Empty : ContactsUiState()
    object Data : ContactsUiState()
}