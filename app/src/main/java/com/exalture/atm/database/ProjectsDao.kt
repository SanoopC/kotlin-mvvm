package com.exalture.atm.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProjectsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(databaseProjectData: List<DatabaseProjectData>)

    @Query("SELECT * from project_table")
    fun getProjects(): LiveData<List<DatabaseProjectData>>

    @Query("SELECT * from project_table WHERE id = :projectId")
    fun getProjectsById(projectId: String): DatabaseProjectData

}