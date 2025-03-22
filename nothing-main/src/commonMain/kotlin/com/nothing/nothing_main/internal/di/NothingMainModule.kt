package com.nothing.nothing_main.internal.di

import com.nothing.community.communities.api.CommunitiesDependencies
import com.nothing.contacts.api.ContactsDependencies
import com.nothing.settings.api.SettingsDependencies
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val nothingMainModule = module {
    singleOf(::DefaultContactsDependencies) bind ContactsDependencies::class
    singleOf(::DefaultCommunitiesDependencies) bind CommunitiesDependencies::class
    singleOf(::DefaultSettingsDependencies) bind SettingsDependencies::class
}

private class DefaultContactsDependencies(
) : ContactsDependencies

private class DefaultCommunitiesDependencies(
) : CommunitiesDependencies

private class DefaultSettingsDependencies(
) : SettingsDependencies