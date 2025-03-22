package com.nothing.auth.internal.presentation

import com.nothing.auth.api.data.AuthUiState
import com.nothing.auth.api.data.CredentialUiModel
import com.nothing.core.feature.CoroutineFeature
import com.nothing.core.flow.AnyStateFlow
import com.nothing.core.flow.wrapToAny
import com.nothing.core_room.UserDao
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

    fun updateState(update: (AuthUiState) -> AuthUiState) {
        _state.value = update(_state.value)
    }


    private fun load() {
        coroutineScope.launch {
            delay(1000)
            //_state.value = AuthUiState.Data(AuthUiModel(user = User(id = "1", name = "Nikita")))
            /*if (userDao.getById(1) == null) {
                userDao.insert(UserEntity(id = 1, name = "Nikita", surname = "", age = 0, email = null))
            }*/
            _state.value = AuthUiState.SignIn(CredentialUiModel("", ""))
        }
    }

}