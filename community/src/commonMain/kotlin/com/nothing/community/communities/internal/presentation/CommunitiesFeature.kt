package com.nothing.community.communities.internal.presentation

import com.nothing.community.communities.api.data.CommunityUiState
import com.nothing.core.feature.CoroutineFeature
import com.nothing.core.flow.AnyStateFlow
import com.nothing.core.flow.wrapToAny
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CommunitiesFeature() : CoroutineFeature() {

    private val _state = MutableStateFlow<CommunityUiState>(CommunityUiState.Loading)
    val state: AnyStateFlow<CommunityUiState> = _state.wrapToAny()

    init {
        load()
    }

    private fun load() {
        coroutineScope.launch {
            _state.value = CommunityUiState.Data
        }
    }

}