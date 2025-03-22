package com.nothing.community.community.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.nothing.community.community.internal.di.createChatModule
import com.nothing.community.communities.api.data.CommunityUiState
import com.nothing.community.communities.internal.presentation.CommunitiesFeature
import com.nothing.core.flow.AnyStateFlow
import com.nothing.core_koin.ComponentKoinContext

class DefaultChatComponent(
    componentContext: ComponentContext,
    dependencies: ChatDependencies,
) : ChatComponent, ComponentContext by componentContext {

    private val koinContext = instanceKeeper.getOrCreate {
        ComponentKoinContext()
    }

    private val scope = koinContext.getOrCreateKoinScope(
        createChatModule(dependencies)
    )

    private val feature: CommunitiesFeature = instanceKeeper.getOrCreate {
        scope.get()
    }

    override val state: AnyStateFlow<CommunityUiState> = feature.state
}