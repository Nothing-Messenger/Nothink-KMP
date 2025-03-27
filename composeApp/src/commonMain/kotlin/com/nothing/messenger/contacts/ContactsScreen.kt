package com.nothing.messenger.contacts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nothing.community.communities.api.CommunityComponent
import com.nothing.community.community.api.ChatComponent
import com.nothing.contacts.api.ContactsComponent

@Composable
fun ContactsScreen(
    component: ContactsComponent,
    modifier: Modifier = Modifier
) {
    val chatUiState by component.state.collectAsState()

    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Contacts", color = MaterialTheme.colorScheme.onBackground)
        }
    }
}