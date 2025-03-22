package com.nothing.settings.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.nothing.core.flow.AnyStateFlow
import com.nothing.core_koin.ComponentKoinContext
import com.nothing.settings.api.data.SettingsUiState
import com.nothing.settings.internal.di.createSettingsModule
import com.nothing.settings.internal.presentation.SettingsFeature

class DefaultSettingsComponent(
    componentContext: ComponentContext,
    dependencies: SettingsDependencies,
) : SettingsComponent, ComponentContext by componentContext {

    private val koinContext = instanceKeeper.getOrCreate {
        ComponentKoinContext()
    }

    private val scope = koinContext.getOrCreateKoinScope(
        createSettingsModule(dependencies)
    )

    private val feature: SettingsFeature = instanceKeeper.getOrCreate {
        scope.get()
    }

    override val state: AnyStateFlow<SettingsUiState> = feature.state
}