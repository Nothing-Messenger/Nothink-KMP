package com.nothing.auth.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.nothing.auth.api.data.AuthUiState
import com.nothing.auth.api.data.SignInUiModel
import com.nothing.auth.api.data.SignUpUiModel
import com.nothing.auth.internal.di.createAuthModules
import com.nothing.auth.internal.presentation.AuthFeature
import com.nothing.core.flow.AnyStateFlow
import com.nothing.core_koin.ComponentKoinContext

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

    override fun onLoginChanged(login: String) {
        feature.updateState { currentState ->
            when (currentState) {
                is AuthUiState.SignIn -> currentState.copy(
                    signInUiModel = currentState.signInUiModel.copy(login = login)
                )
                is AuthUiState.SignUp -> currentState.copy(
                    signUpUiModel = currentState.signUpUiModel.copy(login = login)
                )
                else -> AuthUiState.SignIn(SignInUiModel(login = login, password = ""))
            }
        }
    }

    override fun onPasswordChanged(password: String) {
        feature.updateState { currentState ->
            when (currentState) {
                is AuthUiState.SignIn -> currentState.copy(
                    signInUiModel = currentState.signInUiModel.copy(password = password)
                )
                is AuthUiState.SignUp -> currentState.copy(
                    signUpUiModel = currentState.signUpUiModel.copy(password = password)
                )
                else -> AuthUiState.SignIn(SignInUiModel(login = "", password = password))
            }
        }
    }

    override fun onPasswordRepeatChanged(password: String) {
        feature.updateState { currentState ->
            when (currentState) {
                is AuthUiState.SignUp -> currentState.copy(
                    signUpUiModel = currentState.signUpUiModel.copy(passwordRepeat = password)
                )
                else -> AuthUiState.SignIn(SignInUiModel(login = "", password = password))
            }
        }
    }

    override fun onLoginClicked() {
        onLoginClicked.invoke()
    }

    override fun onRegisterClicked() {
        onRegisterClicked.invoke()
    }

    override fun onForgotPasswordClicked() {
        feature.updateState { AuthUiState.ForgotPassword }
    }

    override fun showSignIn() {
        feature.updateState { AuthUiState.SignIn(SignInUiModel(login = "", password = "")) }
    }

    override fun showSignUp() {
        feature.updateState { AuthUiState.SignUp(SignUpUiModel(login = "", password = "", passwordRepeat = "")) }
    }

}