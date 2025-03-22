package com.nothing.settings.internal.di

import com.nothing.settings.api.SettingsDependencies
import org.koin.core.module.Module
import org.koin.dsl.module

internal fun createSettingsModule(dependencies: SettingsDependencies): List<Module> {
    return listOf(
        settingsModule,
        module {
            single { dependencies }
        }
    )
}