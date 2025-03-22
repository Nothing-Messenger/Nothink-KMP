package com.nothing.root.api

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.nothing.auth.api.AuthComponent
import com.nothing.community.community.api.ChatComponent
import com.nothing.nothing_main.api.NothingMainComponent

interface Root {

    val childStack: Value<ChildStack<*, Child>>

    fun onCommunityClicked()

    sealed class Child {
        class Auth(val component: AuthComponent) : Child()
        class NothingMain(val component: NothingMainComponent) : Child()
        class Chat(val component: ChatComponent) : Child()
    }

}