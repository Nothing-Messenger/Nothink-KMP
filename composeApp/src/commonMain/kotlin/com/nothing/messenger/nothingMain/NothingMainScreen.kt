package com.nothing.messenger.nothingMain

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.nothing.messenger.communities.CommunitiesScreen
import com.nothing.messenger.contacts.ContactsScreen
import com.nothing.messenger.settings.SettingsScreen
import com.nothing.messenger.widget.AppBottomAppBar
import com.nothing.nothing_main.api.NothingMainComponent

@Composable
fun NothingMainScreen(component: NothingMainComponent, modifier: Modifier = Modifier) {
    Scaffold(
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Scaffold(
                    content = { innerPadding ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Children(
                                stack = component.bottomChildStack,
                                modifier = Modifier.fillMaxSize(),
                                animation = stackAnimation(fade() + scale()),
                            ) {
                                when (val child = it.instance) {
                                    is NothingMainComponent.BottomChild.ContactsChild -> ContactsScreen(child.component)
                                    is NothingMainComponent.BottomChild.CommunityChild -> CommunitiesScreen(child.component)
                                    is NothingMainComponent.BottomChild.SettingsChild -> SettingsScreen(child.component)
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.BottomCenter)
                            ) {
                                AppBottomAppBar(
                                    component = component,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                )
            }
        }
    )
}