package com.nothing.messenger.internal.di

import com.nothing.messenger.internal.presentation.MessengerFeature
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val messengerModule = module {
    factoryOf(::MessengerFeature)
}