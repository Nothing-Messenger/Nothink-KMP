package com.nothing.messenger

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.nothing.messenger.theme.AppTheme
import com.nothing.messenger.theme.MyThemeColor
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    context: Any? = null,
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    var themeSelection by remember { mutableStateOf("BLUE") }
    var darkMode by remember { mutableStateOf(darkTheme) }
    var dynamicTheme by remember { mutableStateOf(dynamicColor) }

    LaunchedEffect(darkTheme) {
        darkMode = darkTheme
    }

    val selectedTheme = when (themeSelection) {
        "BLUE" -> MyThemeColor.BLUE
        "NEO" -> MyThemeColor.NEO
        "GREEN" -> MyThemeColor.GREEN
        else -> MyThemeColor.BLUE
    }

    AppTheme(
        darkTheme = darkMode,
        selectedTheme = selectedTheme,
        dynamicColor = dynamicTheme && dynamicColor,
        content = content
    )
}