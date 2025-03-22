package com.nothing.messenger.communities

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nothing.community.community.api.ChatComponent

@Composable
fun CommunityScreen(
    component: ChatComponent,
    modifier: Modifier = Modifier
) {
    val chatUiState by component.state.collectAsState()

    //todo Add logic to split into chatContent / communityContent

    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Chat")
        }
    }
}