package com.nothing.contacts.internal.di

import com.nothing.contacts.internal.presentation.ContactsFeature
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val contactsModule = module {
    factoryOf(::ContactsFeature)
}