package com.nothing.contacts.api

import com.nothing.contacts.api.data.ContactsUiState
import com.nothing.core.flow.AnyStateFlow

interface ContactsComponent {
    val state: AnyStateFlow<ContactsUiState>
}