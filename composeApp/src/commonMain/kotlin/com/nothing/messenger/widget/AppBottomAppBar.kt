package com.nothing.messenger.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.nothing.core.utils.isAndroid
import com.nothing.core.utils.isAndroid31OrHigher
import com.nothing.nothing_main.api.NothingMainComponent
import com.nothing.nothing_main.api.NothingMainComponent.BottomChild.CommunityChild
import com.nothing.nothing_main.api.NothingMainComponent.BottomChild.ContactsChild
import com.nothing.nothing_main.api.NothingMainComponent.BottomChild.SettingsChild

@Composable
internal fun AppBottomAppBar(
    component: NothingMainComponent,
    modifier: Modifier = Modifier
) {
    val stack by component.bottomChildStack.subscribeAsState()
    val activeComponent = stack.active.instance

    val backgroundColor = MaterialTheme.colorScheme.surface

    val selectedContentColor = MaterialTheme.colorScheme.primary
    val unselectedContentColor = MaterialTheme.colorScheme.onSurface

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (isAndroid()) {
                    if (isAndroid31OrHigher()) backgroundColor.copy(alpha = 0.7f)
                    else backgroundColor
                } else {
                    backgroundColor.copy(alpha = 0.7f)
                }
            )
    ) {
        BottomNavigation(
            modifier = modifier
                .fillMaxWidth()
                .height(60.dp),
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
        ) {
            BottomNavigationItem(
                selected = activeComponent is ContactsChild,
                onClick = component::openContacts,
                selectedContentColor = selectedContentColor,
                unselectedContentColor = unselectedContentColor,
                label = {
                    Text("Contacts")
                },
                icon = {
                    Icon(
                        imageVector = Icons.Rounded.Call,
                        contentDescription = "Contacts",
                    )
                },
            )
            BottomNavigationItem(
                selected = activeComponent is CommunityChild,
                onClick = component::openCommunity,
                selectedContentColor = selectedContentColor,
                unselectedContentColor = unselectedContentColor,
                label = {
                    Text("Chat")
                },
                icon = {
                    Icon(
                        imageVector = Icons.Rounded.Home,
                        contentDescription = "Communities",
                    )
                },
            )
            BottomNavigationItem(
                selected = activeComponent is SettingsChild,
                onClick = component::openSettings,
                selectedContentColor = selectedContentColor,
                unselectedContentColor = unselectedContentColor,
                label = {
                    Text("Settings")
                },
                icon = {
                    Icon(
                        imageVector = Icons.Rounded.Settings,
                        contentDescription = "Settings",
                    )
                },
            )
        }
    }

    /*BottomAppBar(
        modifier = modifier.fillMaxWidth().height(70.dp),
        actions = {
            screens.forEachIndexed { index, screensBottom ->
                NavigationBarItem(
                    icon = {
                        androidx.compose.material3.Icon(
                            screensBottom.icon,
                            contentDescription = null
                        )
                    },
                    *//*label = {
                        androidx.compose.material3.Text(
                            text = screensBottom.name,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Light
                        )
                    },*//*
                    selected = selectedItem == index,
                    onClick = {
                        onItemSelected(index)
                        screensBottom.openScreen.invoke()
                    },
                    colors = NavigationBarItemDefaults.colors(selectedIconColor = MaterialTheme.colorScheme.primary)
                )
            }
        }
    )*/
}