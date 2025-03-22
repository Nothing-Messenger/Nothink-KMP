package com.nothing.nothing_main.api

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.nothing.community.communities.api.CommunityComponent
import com.nothing.contacts.api.ContactsComponent
import com.nothing.settings.api.SettingsComponent

interface NothingMainComponent {

    val bottomChildStack: Value<ChildStack<*, BottomChild>>

    fun openContacts()
    fun openCommunity()
    fun openSettings()

    sealed class BottomChild {
        class Contacts(val component: ContactsComponent) : BottomChild()
        class Community(val component: CommunityComponent) : BottomChild()
        class Settings(val component: SettingsComponent) : BottomChild()
    }

}