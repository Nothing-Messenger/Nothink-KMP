package com.nothing.settings.internal.di

import com.nothing.settings.internal.presentation.SettingsFeature
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val settingsModule = module {
    factoryOf(::SettingsFeature)
}