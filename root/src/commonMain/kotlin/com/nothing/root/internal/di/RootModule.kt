package com.nothing.root.internal.di

import com.nothing.auth.api.AuthDependencies
import com.nothing.core_room.UserDao
import com.nothing.messenger.api.MessengerDependencies
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val rootModule = module {
    singleOf(::DefaultAuthDependencies) bind AuthDependencies::class
    singleOf(::DefaultMessengerDependencies) bind MessengerDependencies::class
}

private class DefaultAuthDependencies(
    override val userDao: UserDao
) : AuthDependencies

private class DefaultMessengerDependencies(
    override val userDao: UserDao
) : MessengerDependencies