package com.nothing.auth.internal.presentation

import com.nothing.auth.api.data.AuthUiModel
import com.nothing.auth.api.data.AuthUiState
import com.nothing.auth.internal.domain.model.User
import com.nothing.core.feature.CoroutineFeature
import com.nothing.core.flow.AnyStateFlow
import com.nothing.core.flow.wrapToAny
import com.nothing.core_room.UserDao
import com.nothing.core_room.UserEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

internal class AuthFeature(
    private val userDao: UserDao
) : CoroutineFeature() {

    private val _state = MutableStateFlow<AuthUiState>(AuthUiState.Loading)
    val state: AnyStateFlow<AuthUiState> = _state.wrapToAny()

    init {
        load()
    }

    private fun load() {
        coroutineScope.launch {
            delay(1000)
            _state.value = AuthUiState.Data(AuthUiModel(user = User(id = "1", name = "Nikita")))

            if (userDao.getById(1) == null) {
                userDao.insert(UserEntity(id = 1, name = "Nikita", surname = "", age = 0, email = null))
            }

        }
    }

}