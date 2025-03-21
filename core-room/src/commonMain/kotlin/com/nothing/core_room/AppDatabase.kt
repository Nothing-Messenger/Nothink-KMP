package com.nothing.core_room

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

expect val databaseModule: Module

@Database(
    entities = [UserEntity::class],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}

val roomModule = module {
    includes(databaseModule)
    single<UserDao> { get<AppDatabase>().getUserDao() }
}
