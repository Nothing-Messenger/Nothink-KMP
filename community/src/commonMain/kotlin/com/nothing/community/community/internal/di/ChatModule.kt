package com.nothing.community.community.internal.di

import com.nothing.community.communities.internal.presentation.CommunitiesFeature
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val chatModule = module {
    factoryOf(::CommunitiesFeature)
}