package com.exalture.atm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [AccountData::class, DatabaseProjectData::class],
    version = 1,
    exportSchema = false
)
abstract class ExaltureDatabase : RoomDatabase() {

    abstract val accountDatabaseDao: AccountDatabaseDao
    abstract val projectsDao: ProjectsDao

    companion object {
        @Volatile
        private var INSTANCE: ExaltureDatabase? = null
        fun getInstance(context: Context): ExaltureDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ExaltureDatabase::class.java,
                        "account_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}