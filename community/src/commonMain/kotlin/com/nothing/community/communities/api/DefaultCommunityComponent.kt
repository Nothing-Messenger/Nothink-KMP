package com.nothing.community.communities.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.nothing.community.communities.api.data.CommunityUiState
import com.nothing.community.communities.internal.di.createCommunitiesModule
import com.nothing.community.communities.internal.presentation.CommunitiesFeature
import com.nothing.core.flow.AnyStateFlow
import com.nothing.core_koin.ComponentKoinContext

class DefaultCommunityComponent(
    componentContext: ComponentContext,
    private val onCommunityClicked: () -> Unit,
    dependencies: CommunitiesDependencies,
) : CommunityComponent, ComponentContext by componentContext {

    private val koinContext = instanceKeeper.getOrCreate {
        ComponentKoinContext()
    }

    private val scope = koinContext.getOrCreateKoinScope(
        createCommunitiesModule(dependencies)
    )

    private val feature: CommunitiesFeature = instanceKeeper.getOrCreate {
        scope.get()
    }

    override val state: AnyStateFlow<CommunityUiState> = feature.state

    override fun onCommunityClicked() {
        onCommunityClicked.invoke()
    }
}