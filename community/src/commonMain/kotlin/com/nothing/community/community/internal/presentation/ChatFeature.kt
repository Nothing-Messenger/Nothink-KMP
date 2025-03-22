package com.nothing.community.community.internal.presentation

import com.nothing.community.community.api.data.ChatUiState
import com.nothing.core.feature.CoroutineFeature
import com.nothing.core.flow.AnyStateFlow
import com.nothing.core.flow.wrapToAny
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ChatFeature() : CoroutineFeature() {

    private val _state = MutableStateFlow<ChatUiState>(ChatUiState.Loading)
    val state: AnyStateFlow<ChatUiState> = _state.wrapToAny()

    init {
        load()
    }

    private fun load() {
        coroutineScope.launch {
            _state.value = ChatUiState.Data
        }
    }

}