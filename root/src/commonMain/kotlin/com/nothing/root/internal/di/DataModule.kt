package com.nothing.root.internal.di

import com.nothing.core_room.roomModule
import org.koin.dsl.module

internal val dataModule = module {
    includes(roomModule)
    
//    factoryOf(::HttpClientProvider)
//    singleOf(HttpClientProvider::get)
//    singleOf(::DefaultSpaceXApi) bind SpaceXApi::class
}