package com.nothing.community.community.internal.di

import com.nothing.community.community.api.ChatDependencies
import org.koin.core.module.Module

internal fun createChatModule(dependencies: ChatDependencies): List<Module> {
    return listOf(
        chatModule,
    )
}