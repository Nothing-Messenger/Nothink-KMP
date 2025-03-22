package com.nothing.nothing_main.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.nothing.community.communities.api.CommunityComponent
import com.nothing.community.communities.api.DefaultCommunityComponent
import com.nothing.contacts.api.ContactsComponent
import com.nothing.contacts.api.DefaultContactsComponent
import com.nothing.core_koin.ComponentKoinContext
import com.nothing.nothing_main.internal.di.nothingMainModule
import com.nothing.settings.api.DefaultSettingsComponent
import com.nothing.settings.api.SettingsComponent
import kotlinx.serialization.Serializable

@DelicateDecomposeApi
class DefaultNothingMainComponent(
    componentContext: ComponentContext,
    private val onCommunityClicked: () -> Unit,
) : NothingMainComponent, ComponentContext by componentContext {

    private val koinContext = instanceKeeper.getOrCreate {
        ComponentKoinContext()
    }

    private val scope = koinContext.getOrCreateKoinScope(
        listOf(nothingMainModule),
    )

    private val bottomNavigation = StackNavigation<BottomConfig>()

    override val bottomChildStack: Value<ChildStack<*, NothingMainComponent.BottomChild>> = childStack(
        source = bottomNavigation,
        serializer = BottomConfig.serializer(), // Or null to disable navigation state saving
        initialConfiguration = BottomConfig.Community,
        handleBackButton = true, // Pop the back stack on back button press
        childFactory = ::createBottomChild,
        key = "BottomStack"
    )

    private fun createBottomChild(
        config: BottomConfig, componentContext: ComponentContext
    ): NothingMainComponent.BottomChild = when (config) {
        is BottomConfig.Contacts -> NothingMainComponent.BottomChild.Contacts(contacts(componentContext))
        is BottomConfig.Community -> NothingMainComponent.BottomChild.Community(community(componentContext))
        is BottomConfig.Settings -> NothingMainComponent.BottomChild.Settings(settings(componentContext))
    }

    private fun contacts(
        componentContext: ComponentContext,
    ): ContactsComponent = DefaultContactsComponent(
        componentContext = componentContext,
        dependencies = scope.get()
    )

    private fun community(
        componentContext: ComponentContext,
    ): CommunityComponent = DefaultCommunityComponent(
        componentContext = componentContext,
        onCommunityClicked = { onCommunityClicked() },
        dependencies = scope.get()
    )

    private fun settings(
        componentContext: ComponentContext,
    ): SettingsComponent = DefaultSettingsComponent(
        componentContext = componentContext,
        dependencies = scope.get()
    )

    override fun openContacts() {
        bottomNavigation.bringToFront(BottomConfig.Contacts)
    }

    override fun openCommunity() {
        bottomNavigation.bringToFront(BottomConfig.Community)
    }

    override fun openSettings() {
        bottomNavigation.bringToFront(BottomConfig.Settings)
    }

    @Serializable
    private sealed class BottomConfig {
        @Serializable
        data object Contacts : BottomConfig()

        @Serializable
        data object Community : BottomConfig()

        @Serializable
        data object Settings : BottomConfig()
    }

}