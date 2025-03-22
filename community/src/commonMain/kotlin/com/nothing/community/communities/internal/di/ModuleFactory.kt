package com.nothing.community.communities.internal.di

import com.nothing.community.communities.api.CommunitiesDependencies
import org.koin.core.module.Module

internal fun createCommunitiesModule(dependencies: CommunitiesDependencies): List<Module> {
    return listOf(
        communitiesModule,
    )
}