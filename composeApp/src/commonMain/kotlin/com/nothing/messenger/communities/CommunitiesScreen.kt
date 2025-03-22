package com.nothing.messenger.communities

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nothing.community.communities.api.CommunityComponent

@Composable
fun CommunitiesScreen(
    component: CommunityComponent,
    modifier: Modifier = Modifier
) {
    val communityState by component.state.collectAsState()

    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Text("Community")
                Button(
                    onClick = { component.onCommunityClicked() }
                ) { Text("Open Chat") }
            }
        }
    }


}