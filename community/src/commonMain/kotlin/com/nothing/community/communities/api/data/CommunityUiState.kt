package com.nothing.community.communities.api.data

sealed class CommunityUiState {
    object Loading : CommunityUiState()
    object Error : CommunityUiState()
    object Empty : CommunityUiState()
    data class Data(val communities: List<CommunityModel>) : CommunityUiState()
}