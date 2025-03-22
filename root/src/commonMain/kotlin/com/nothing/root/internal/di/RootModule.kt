package com.nothing.root.internal.di

import com.nothing.auth.api.AuthDependencies
import com.nothing.community.community.api.ChatDependencies
import com.nothing.core_room.UserDao
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val rootModule = module {
    singleOf(::DefaultAuthDependencies) bind AuthDependencies::class
    singleOf(::DefaultChatDependencies) bind ChatDependencies::class
}

private class DefaultAuthDependencies(
    override val userDao: UserDao
) : AuthDependencies

private class DefaultChatDependencies(
) : ChatDependencies