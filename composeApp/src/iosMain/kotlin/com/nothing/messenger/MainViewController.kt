package com.nothing.messenger

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.nothing.messenger.theme.AppTheme
import com.nothing.messenger.theme.MyThemeColor
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

fun MainViewController() = ComposeUIViewController {

    val isDarkTheme = UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
            UIUserInterfaceStyle.UIUserInterfaceStyleDark

    //MyTest(isDarkTheme)

    App(
        darkTheme = isDarkTheme,
        dynamicColor = false,
        content = {
            // start for iOS
        }
    )
}

@Composable
fun MyTest(isDarkTheme: Boolean) {
    AppTheme(
        darkTheme = isDarkTheme,
        selectedTheme = MyThemeColor.GREEN,
        dynamicColor = false
    ) {
        var showText by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
        ) {
            AnimatedVisibility(showText) {
                Text(
                    modifier = Modifier.align(Alignment.TopCenter),
                    text = "Somethings up with IOS.",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }

            Button(
                modifier = Modifier.align(Alignment.Center),
                onClick = { showText = !showText }) {
                Text("This is ios main!")
            }
        }
    }
}