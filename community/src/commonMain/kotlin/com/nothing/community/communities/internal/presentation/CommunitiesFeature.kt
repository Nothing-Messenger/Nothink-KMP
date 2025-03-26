package com.nothing.community.communities.internal.presentation

import com.nothing.community.communities.api.data.CommunityModel
import com.nothing.community.communities.api.data.CommunityUiState
import com.nothing.core.feature.CoroutineFeature
import com.nothing.core.flow.AnyStateFlow
import com.nothing.core.flow.wrapToAny
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CommunitiesFeature() : CoroutineFeature() {

    private val _state = MutableStateFlow<CommunityUiState>(CommunityUiState.Loading)
    val state: AnyStateFlow<CommunityUiState> = _state.wrapToAny()

    private val _communities = MutableStateFlow<MutableList<CommunityModel>>(mutableListOf())
    val communities: AnyStateFlow<List<CommunityModel>> = _communities.wrapToAny()

    init {
        load()
    }

    private fun load() {
        coroutineScope.launch {

            for (index in 0..20) {
                _communities.value.add(
                    CommunityModel(
                        name = "Chat $index",
                        lastMessage = "Last message from chat $index",
                        lastMessageTime = "12:35",
                        unreadCount = index
                    )
                )
            }

            delay(2000)
            _state.value = CommunityUiState.Data(communities.value)
        }
    }

}