package com.nothing.community.community.api

import com.nothing.core.flow.AnyStateFlow
import com.nothing.community.communities.api.data.CommunityUiState

interface ChatComponent {
    val state: AnyStateFlow<CommunityUiState>
}