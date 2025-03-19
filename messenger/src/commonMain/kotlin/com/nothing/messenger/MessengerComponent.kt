package com.nothing.messenger

import com.arkivanov.decompose.ComponentContext

interface MessengerComponent {

    fun onLogOutClicked()

}

class DefaultMessengerComponent(
    componentContext: ComponentContext,
    private val onLogOutClicked: () -> Unit
) : MessengerComponent,
    ComponentContext by componentContext {

    override fun onLogOutClicked() {
        onLogOutClicked()
    }

}