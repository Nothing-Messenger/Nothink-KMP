package com.nothing.root.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.nothing.auth.api.AuthComponent
import com.nothing.auth.api.DefaultAuthComponent
import com.nothing.core_koin.ComponentKoinContext
import com.nothing.messenger.DefaultMessengerComponent
import com.nothing.messenger.MessengerComponent
import com.nothing.root.api.Root.Child.Auth
import com.nothing.root.api.Root.Child.Messenger
import com.nothing.root.internal.di.dataModule
import com.nothing.root.internal.di.rootModule
import kotlinx.serialization.Serializable

class RootComponent(componentContext: ComponentContext) : Root,
    ComponentContext by componentContext {

    private val koinContext = instanceKeeper.getOrCreate {
        ComponentKoinContext()
    }

    private val scope = koinContext.getOrCreateKoinScope(
        listOf(rootModule, dataModule)
    )

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<*, Root.Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(), // Or null to disable navigation state saving
        initialConfiguration = Config.Auth,
        handleBackButton = true, // Pop the back stack on back button press
        childFactory = ::createChild,
    )

    private fun createChild(
        config: Config, componentContext: ComponentContext
    ): Root.Child = when (config) {
        is Config.Auth -> Auth(auth(componentContext))
        is Config.Messenger -> Messenger(messenger(componentContext))
    }

    private fun auth(componentContext: ComponentContext): AuthComponent = DefaultAuthComponent(
        componentContext = componentContext,
        onLoginClicked = { navigation.push(Config.Messenger) },
        onRegisterClicked = { navigation.push(Config.Messenger) },
        dependencies = scope.get(),
    )

    private fun messenger(
        componentContext: ComponentContext,
    ): MessengerComponent = DefaultMessengerComponent(
        componentContext = componentContext,
        onLogOutClicked = { navigation.pop() },
    )


    @Serializable // kotlinx-serialization plugin must be applied
    private sealed class Config {
        @Serializable
        data object Auth : Config()

        @Serializable
        data object Messenger : Config()
    }
}