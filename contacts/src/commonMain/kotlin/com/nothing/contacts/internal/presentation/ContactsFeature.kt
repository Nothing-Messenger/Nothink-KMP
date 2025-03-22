package com.nothing.contacts.internal.presentation

import com.nothing.contacts.api.data.ContactsUiState
import com.nothing.core.feature.CoroutineFeature
import com.nothing.core.flow.AnyStateFlow
import com.nothing.core.flow.wrapToAny
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ContactsFeature() : CoroutineFeature() {

    private val _state = MutableStateFlow<ContactsUiState>(ContactsUiState.Loading)
    val state: AnyStateFlow<ContactsUiState> = _state.wrapToAny()

    init {
        load()
    }

    private fun load() {
        coroutineScope.launch {
            _state.value = ContactsUiState.Data
        }
    }

}