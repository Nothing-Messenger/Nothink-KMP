package com.nothing.auth.internal.di

import com.nothing.auth.internal.presentation.AuthFeature
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val authModule = module {
    factoryOf(::AuthFeature)
}