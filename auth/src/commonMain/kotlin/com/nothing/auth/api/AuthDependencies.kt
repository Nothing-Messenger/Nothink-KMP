package com.nothing.auth.api

import com.nothing.core_room.UserDao

interface AuthDependencies {
    val userDao: UserDao
}