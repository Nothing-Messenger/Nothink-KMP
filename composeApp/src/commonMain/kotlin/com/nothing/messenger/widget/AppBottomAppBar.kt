package com.nothing.messenger.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
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

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (isAndroid()) {
                    if (isAndroid31OrHigher()) Color.White.copy(alpha = 0.7f)
                    else Color.White
                } else {
                    Color.White.copy(alpha = 0.7f)
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
                selectedContentColor = Color.DarkGray,
                unselectedContentColor = Color.Gray,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "Contacts",
                    )
                },
            )
            BottomNavigationItem(
                selected = activeComponent is CommunityChild,
                onClick = component::openCommunity,
                selectedContentColor = Color.DarkGray,
                unselectedContentColor = Color.Gray,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Communities",
                    )
                },
            )
            BottomNavigationItem(
                selected = activeComponent is SettingsChild,
                onClick = component::openSettings,
                selectedContentColor = Color.DarkGray,
                unselectedContentColor = Color.Gray,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Settings,
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