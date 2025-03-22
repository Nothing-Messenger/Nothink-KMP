package com.nothing.community.communities.api

import com.nothing.core.flow.AnyStateFlow
import com.nothing.community.communities.api.data.CommunityUiState

interface CommunityComponent {
    val state: AnyStateFlow<CommunityUiState>

    fun onCommunityClicked()
}