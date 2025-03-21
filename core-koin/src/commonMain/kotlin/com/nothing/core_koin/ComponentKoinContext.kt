package com.nothing.core_koin

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import org.koin.core.KoinApplication
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.module.Module
import org.koin.core.scope.Scope
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.koinApplication

class ComponentKoinContext : InstanceKeeper.Instance {
    private var koinApp: KoinApplication? = null

    @OptIn(KoinInternalApi::class)
    fun getOrCreateKoinScope(modules: List<Module>, appDeclaration: KoinAppDeclaration = {}): Scope {
        if (koinApp == null) {
            koinApp = koinApplication {
                appDeclaration() // Use to provide Android context
                modules(modules)
            }
        }
        return requireNotNull(koinApp).koin.scopeRegistry.rootScope
    }

    override fun onDestroy() {
        koinApp?.close()
        koinApp = null
    }
}
