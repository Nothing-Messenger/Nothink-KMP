package com.nothing.contacts.internal.di

import com.nothing.contacts.api.ContactsDependencies
import org.koin.core.module.Module
import org.koin.dsl.module

internal fun createContactsModule(dependencies: ContactsDependencies): List<Module> {
    return listOf(
        contactsModule,
        module {
            single { dependencies }
        }
    )
}