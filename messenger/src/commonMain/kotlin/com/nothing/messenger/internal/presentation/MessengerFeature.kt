package com.nothing.messenger.internal.presentation

import com.nothing.core.feature.CoroutineFeature
import com.nothing.core.flow.AnyStateFlow
import com.nothing.core.flow.wrapToAny
import com.nothing.core_room.UserDao
import com.nothing.messenger.api.data.MessengerUiModel
import com.nothing.messenger.api.data.MessengerUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MessengerFeature(private val userDao: UserDao) : CoroutineFeature() {

    private val _state = MutableStateFlow<MessengerUiState>(MessengerUiState.Loading)
    val state: AnyStateFlow<MessengerUiState> = _state.wrapToAny()

    init {
        load()
    }

    private fun load() {
        coroutineScope.launch {
            val user = userDao.getById(1)
            _state.value =
                MessengerUiState.Data(messengerUiModel = MessengerUiModel(name = user?.name))
        }
    }

}