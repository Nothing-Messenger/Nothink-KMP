package com.nothing.messenger

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.nothing.messenger.auth.AuthScreen
import com.nothing.messenger.communities.CommunityScreen
import com.nothing.messenger.nothingMain.NothingMainScreen
import com.nothing.root.api.Root
import com.nothing.root.api.RootComponent
import org.koin.android.ext.koin.androidContext

class AndroidApp : Application()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        //WindowCompat.setDecorFitsSystemWindows(window, false)

        val root = RootComponent(defaultComponentContext(), { androidContext(this@MainActivity) })
        setContent {
            App(
                context = this,
                dynamicColor = false, //
                darkTheme = isSystemInDarkTheme(),
                content = {
                    RootScreen(root)
                }
            )
            //RootScreen(root)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun RootScreen(
    root: Root
) {
    val childStack by root.childStack.subscribeAsState()

    when (val active = childStack.active.instance) {
        is Root.Child.Auth -> AuthScreen(component = active.component)
        is Root.Child.NothingMain -> NothingMainScreen(component = active.component)
        is Root.Child.Chat -> CommunityScreen(component = active.component)
    }
}