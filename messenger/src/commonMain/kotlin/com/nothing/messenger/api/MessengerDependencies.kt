package com.nothing.messenger.api

import com.nothing.core_room.UserDao

interface MessengerDependencies {
    val userDao: UserDao
}