package com.nothing.messenger.communities.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kevinnzou.swipebox.SwipeBox
import com.kevinnzou.swipebox.SwipeDirection
import com.kevinnzou.swipebox.widget.SwipeIcon
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeableChatItem(
    title: String,
    message: String,
    time: String,
    unreadCount: Int,
    onDelete: () -> Unit,
    onPin: () -> Unit,
    onClick: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    SwipeBox(
        modifier = Modifier.fillMaxWidth(),
        swipeDirection = SwipeDirection.Both,
        startContentWidth = 60.dp,
        startContent = { swipeableState, endSwipeProgress ->
            SwipeIcon(
                imageVector = Icons.Outlined.Favorite,
                contentDescription = "Favorite",
                tint = Color.White,
                background = Color(0xFFFFB133),
                weight = 1f,
                iconSize = 20.dp
            ) {
                coroutineScope.launch {
                    swipeableState.animateTo(0)
                    onPin.invoke()
                }
            }
        },
        endContentWidth = 60.dp,
        endContent = { swipeableState, endSwipeProgress ->
            SwipeIcon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = "Delete",
                tint = Color.White,
                background = Color(0xFFFA1E32),
                weight = 1f,
                iconSize = 20.dp
            ) {
                coroutineScope.launch {
                    swipeableState.animateTo(0)
                    onDelete.invoke()
                }
            }
        }
    ) { _, _, _ ->
        CommunityItem(
            title = title,
            message = message,
            time = time,
            unreadCount = unreadCount,
            onClick = onClick
        )
    }
}
