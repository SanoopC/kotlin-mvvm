package com.exalture.atm.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [AccountData::class, DatabaseProjectData::class],
    version = 1,
    exportSchema = false
)
abstract class ExaltureDatabase : RoomDatabase() {

    abstract val accountDatabaseDao: AccountDatabaseDao
    abstract val projectsDao: ProjectsDao

}