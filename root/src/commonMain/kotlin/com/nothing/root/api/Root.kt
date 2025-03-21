package com.nothing.root.api

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.nothing.auth.api.AuthComponent
import com.nothing.messenger.api.MessengerComponent

interface Root {

    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class Auth(val component: AuthComponent) : Child()
        class Messenger(val component: MessengerComponent) : Child()
    }

}