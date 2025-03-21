package com.nothing.messenger.messenger

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.nothing.messenger.api.MessengerComponent
import com.nothing.messenger.api.data.MessengerUiState
import com.nothing.messenger.widget.AppProgressBar

@Composable
fun MessengerScreen(
    component: MessengerComponent,
    modifier: Modifier = Modifier
) {

    val uiState by component.state.collectAsState()


    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (val state = uiState) {
                is MessengerUiState.Loading -> AppProgressBar()
                is MessengerUiState.Data -> {
                    MessengerContent(state)
                }

                is MessengerUiState.Empty -> MessengerError()
                is MessengerUiState.Error -> MessengerError()
            }
        }
    }
}

@Composable
fun MessengerContent(uiState: MessengerUiState.Data) {
    Column {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Messenger screen ${uiState.messengerUiModel.name}"
        )
    }
}

@Composable
fun MessengerError() {

}