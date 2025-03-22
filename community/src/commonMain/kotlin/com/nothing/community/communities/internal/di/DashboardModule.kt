package com.nothing.community.communities.internal.di

import com.nothing.community.communities.internal.presentation.CommunitiesFeature
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val communitiesModule = module {
    factoryOf(::CommunitiesFeature)
}