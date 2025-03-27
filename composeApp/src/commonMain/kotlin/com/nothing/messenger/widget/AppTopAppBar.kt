package com.nothing.messenger.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nothing.core.utils.isAndroid
import com.nothing.core.utils.isAndroid31OrHigher

@Composable
internal fun AppTopAppBar(
    title: String,
    topBarHeight: Dp = 70.dp,
    onCloseClick: (() -> Unit)? = null,
) {
    val backgroundColor = MaterialTheme.colorScheme.surface

    TopAppBar(
        title = { Text(text = title, color = MaterialTheme.colorScheme.onSurface) },
        modifier = Modifier.height(topBarHeight),
        backgroundColor = if (isAndroid()) {
            if (isAndroid31OrHigher()) backgroundColor.copy(alpha = 0.7f)
            else backgroundColor
        } else {
            backgroundColor.copy(alpha = 0.7f)
        },
        navigationIcon = onCloseClick?.let { listener ->
            {
                IconButton(onClick = listener) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Back button",
                    )
                }
            }
        },
        elevation = 0.dp,
    )
}