package com.nothing.contacts.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.nothing.contacts.api.data.ContactsUiState
import com.nothing.contacts.internal.di.createContactsModule
import com.nothing.contacts.internal.presentation.ContactsFeature
import com.nothing.core.flow.AnyStateFlow
import com.nothing.core_koin.ComponentKoinContext

class DefaultContactsComponent(
    componentContext: ComponentContext,
    dependencies: ContactsDependencies,
) : ContactsComponent, ComponentContext by componentContext {

    private val koinContext = instanceKeeper.getOrCreate {
        ComponentKoinContext()
    }

    private val scope = koinContext.getOrCreateKoinScope(
        createContactsModule(dependencies)
    )

    private val feature: ContactsFeature = instanceKeeper.getOrCreate {
        scope.get()
    }

    override val state: AnyStateFlow<ContactsUiState> = feature.state
}