package com.nothing.messenger.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.nothing.auth.api.AuthComponent
import com.nothing.auth.api.data.AuthUiModel
import com.nothing.auth.api.data.AuthUiState
import com.nothing.messenger.widget.AppProgressBar

@Composable
fun AuthScreen(
    component: AuthComponent,
    modifier: Modifier = Modifier
) {

    val uiState by component.state.collectAsState()

    Scaffold(
        modifier = modifier,
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (val state = uiState) {
                is AuthUiState.Loading -> AppProgressBar()
                is AuthUiState.Data -> {
                    AuthContent(state, state.authUiModel, component)
                }

                is AuthUiState.Empty -> AuthContent(state)
                is AuthUiState.Error -> AuthError()
            }
        }
    }
}

@Composable
fun AuthContent(uiState: AuthUiState, authUiModel: AuthUiModel? = null, component: AuthComponent? = null) {
    Column {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Welcome to Noting Messenger, ${authUiModel?.user?.name} !!!"
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {
                component?.onRegisterClicked()
            }) {
                Text("Register")
            }
            Button(onClick = {
                component?.onLoginClicked()
            }) {
                Text("Login")
            }
        }
    }
}

@Composable
fun AuthError() {

}