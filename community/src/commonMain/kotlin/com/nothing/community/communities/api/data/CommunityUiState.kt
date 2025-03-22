package com.nothing.community.communities.api.data

sealed class CommunityUiState {
    object Loading : CommunityUiState()
    object Error : CommunityUiState()
    object Empty : CommunityUiState()
    object Data : CommunityUiState()
}