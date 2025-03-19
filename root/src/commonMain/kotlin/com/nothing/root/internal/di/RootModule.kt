package com.nothing.root.internal.di

import com.nothing.auth.api.AuthDependencies
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val rootModule = module {
    singleOf(::DefaultAuthDependencies) bind AuthDependencies::class
}

private class DefaultAuthDependencies(
) : AuthDependencies