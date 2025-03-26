package com.nothing.messenger.communities

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.nothing.community.communities.api.CommunityComponent
import com.nothing.community.communities.api.data.CommunityModel
import com.nothing.community.communities.api.data.CommunityUiState
import com.nothing.messenger.communities.widget.CommunityItemSkeleton
import com.nothing.messenger.communities.widget.SwipeableChatItem
import com.nothing.messenger.widget.AppTopAppBar
import com.nothing.messenger.widget.EmptyScreen
import com.nothing.messenger.widget.ErrorScreen
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import dev.chrisbanes.haze.materials.HazeMaterials

@OptIn(ExperimentalHazeMaterialsApi::class)
@Composable
fun CommunitiesScreen(
    component: CommunityComponent,
    modifier: Modifier = Modifier
) {
    val communityState by component.state.collectAsState()
    val scrollState = rememberScrollState()

    val hazeState = remember { HazeState() }
    val topBarHeight = 70.dp
    val bottomBarHeight = 60.dp

    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .height(topBarHeight)
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .align(Alignment.TopCenter)
                    .hazeEffect(state = hazeState, style = HazeMaterials.ultraThin())
                    .zIndex(2f)
            ) {
                AppTopAppBar(title = "Chats", topBarHeight = topBarHeight)
            }
            when (val state = communityState) {
                is CommunityUiState.Loading -> CommunitiesContentSkeleton(
                    hazeState = hazeState,
                    scrollState = scrollState,
                    topBarHeight = topBarHeight,
                    bottomBarHeight = bottomBarHeight
                )
                is CommunityUiState.Data -> CommunitiesContent(
                    component = component,
                    items = state.communities,
                    hazeState = hazeState,
                    scrollState = scrollState,
                    topBarHeight = topBarHeight,
                    bottomBarHeight = bottomBarHeight
                )

                is CommunityUiState.Empty -> EmptyScreen()
                is CommunityUiState.Error -> ErrorScreen()
            }
            Box(
                modifier = Modifier
                    .height(bottomBarHeight)
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .align(Alignment.BottomCenter)
                    .hazeEffect(state = hazeState, style = HazeMaterials.ultraThin())
                    .zIndex(2f)
            )
        }
    }
}

@Composable
fun CommunitiesContentSkeleton(
    modifier: Modifier = Modifier,
    hazeState: HazeState = remember { HazeState() },
    scrollState: ScrollState = rememberScrollState(),
    bottomBarHeight: Dp = 60.dp,
    topBarHeight: Dp = 70.dp
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .hazeSource(state = hazeState)
            .scrollable(state = scrollState, enabled = false, orientation = Orientation.Vertical)
            .zIndex(1f)
    ) {
        item {
            Spacer(modifier = Modifier.height(topBarHeight))
        }
        items(20) {
            CommunityItemSkeleton()
        }
        item {
            Spacer(modifier = Modifier.height(bottomBarHeight))
        }
    }
}

@OptIn(ExperimentalHazeMaterialsApi::class)
@Composable
fun CommunitiesContent(
    modifier: Modifier = Modifier,
    component: CommunityComponent,
    items: List<CommunityModel>,
    hazeState: HazeState = remember { HazeState() },
    scrollState: ScrollState = rememberScrollState(),
    bottomBarHeight: Dp = 60.dp,
    topBarHeight: Dp = 70.dp
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .hazeSource(state = hazeState)
                .scrollable(state = scrollState, orientation = Orientation.Vertical)
                .zIndex(1f)
        ) {
            item {
                Spacer(modifier = Modifier.height(topBarHeight))
            }
            items(items.size) { index ->
                val model = items[index]

                SwipeableChatItem(
                    title = model.name,
                    message = model.lastMessage,
                    time = model.lastMessageTime,
                    unreadCount = model.unreadCount,
                    onClick = { component.onCommunityClicked() },
                    onPin = { },
                    onDelete = { }
                )
            }
            item {
                Spacer(modifier = Modifier.height(bottomBarHeight))
            }
        }
    }
}