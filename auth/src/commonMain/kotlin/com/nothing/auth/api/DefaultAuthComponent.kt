package com.nothing.auth.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.nothing.auth.api.data.AuthUiState
import com.nothing.auth.internal.di.createAuthModules
import com.nothing.auth.internal.presentation.AuthFeature
import com.nothing.core.flow.AnyStateFlow
import com.nothing.core_koin.ComponentKoinContext
import org.koin.core.parameter.parametersOf
import kotlin.text.get

class DefaultAuthComponent(
    componentContext: ComponentContext,
    private val onLoginClicked: () -> Unit,
    private val onRegisterClicked: () -> Unit,
    dependencies: AuthDependencies,
) : AuthComponent,
    ComponentContext by componentContext {
    private val koinContext = instanceKeeper.getOrCreate {
        ComponentKoinContext()
    }

    private val scope = koinContext.getOrCreateKoinScope(
        createAuthModules(dependencies)
    )

    private val feature: AuthFeature = instanceKeeper.getOrCreate {
        scope.get()
    }

    override val state: AnyStateFlow<AuthUiState> = feature.state

    //override val state: AnyStateFlow<AuthUiState> =
    override fun onLoginClicked() {
        onLoginClicked()
    }

    override fun onRegisterClicked() {
        onRegisterClicked()
    }

}