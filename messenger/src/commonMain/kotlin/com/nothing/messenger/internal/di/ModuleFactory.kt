package com.nothing.messenger.internal.di

import com.nothing.messenger.api.MessengerDependencies
import org.koin.core.module.Module
import org.koin.dsl.module

internal fun createMessengerModule(dependencies: MessengerDependencies): List<Module> {
    return listOf(
        messengerModule,
        module {
            single { dependencies.userDao }
        }
    )
}