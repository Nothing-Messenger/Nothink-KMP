package com.nothing.messenger.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.nothing.core.flow.AnyStateFlow
import com.nothing.core_koin.ComponentKoinContext
import com.nothing.messenger.api.data.MessengerUiState
import com.nothing.messenger.internal.di.createMessengerModule
import com.nothing.messenger.internal.presentation.MessengerFeature

class DefaultMessengerComponent(
    componentContext: ComponentContext,
    private val onLogOutClicked: () -> Unit,
    dependencies: MessengerDependencies,
) : MessengerComponent,
    ComponentContext by componentContext {

    private val koinContext = instanceKeeper.getOrCreate {
        ComponentKoinContext()
    }

    private val scope = koinContext.getOrCreateKoinScope(
        createMessengerModule(dependencies)
    )

    private val feature: MessengerFeature = instanceKeeper.getOrCreate {
        scope.get()
    }

    override val state: AnyStateFlow<MessengerUiState> = feature.state

    override fun onLogOutClicked() {
        onLogOutClicked.invoke()
    }

}