package com.nothing.auth.internal.di

import com.nothing.auth.api.AuthDependencies
import org.koin.core.module.Module

internal fun createAuthModules(dependencies: AuthDependencies): List<Module> {
    return listOf(
        authModule
    )
}