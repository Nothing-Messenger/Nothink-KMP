package com.nothing.root.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.nothing.auth.api.AuthComponent
import com.nothing.auth.api.DefaultAuthComponent
import com.nothing.community.community.api.ChatComponent
import com.nothing.community.community.api.DefaultChatComponent
import com.nothing.core_koin.ComponentKoinContext
import com.nothing.nothing_main.api.DefaultNothingMainComponent
import com.nothing.nothing_main.api.NothingMainComponent
import com.nothing.root.api.Root.Child.Auth
import com.nothing.root.internal.di.dataModule
import com.nothing.root.internal.di.rootModule
import kotlinx.serialization.Serializable
import org.koin.dsl.KoinAppDeclaration

@DelicateDecomposeApi
class RootComponent(componentContext: ComponentContext, appDeclaration: KoinAppDeclaration = {}) :
    Root, ComponentContext by componentContext {

    private val koinContext = instanceKeeper.getOrCreate {
        ComponentKoinContext()
    }

    private val scope = koinContext.getOrCreateKoinScope(
        listOf(rootModule, dataModule),
        appDeclaration,
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
        is Config.NothingApp -> Root.Child.NothingMain(nothingApp(componentContext))
        is Config.Community -> Root.Child.Chat(chat(componentContext))
    }

    private fun auth(componentContext: ComponentContext): AuthComponent = DefaultAuthComponent(
        componentContext = componentContext,
        onLoginClicked = { navigation.push(Config.NothingApp) },
        onRegisterClicked = { navigation.push(Config.NothingApp) },
        dependencies = scope.get(),
    )

    private fun nothingApp(
        componentContext: ComponentContext,
    ): NothingMainComponent = DefaultNothingMainComponent(
        componentContext = componentContext,
        onCommunityClicked = { onCommunityClicked() }
        //dependencies = scope.get()
    )

    private fun chat(
        componentContext: ComponentContext,
    ): ChatComponent = DefaultChatComponent(
        componentContext = componentContext,
        dependencies = scope.get()
    )

    override fun onCommunityClicked() {
        // maybe transfer to nothingApp 'onCommunityClicked'
        navigation.push(Config.Community)
    }

    @Serializable // kotlinx-serialization plugin must be applied
    private sealed class Config {
        @Serializable
        data object Auth : Config()

        @Serializable
        data object NothingApp : Config()

        @Serializable
        data object Community : Config()
    }
}