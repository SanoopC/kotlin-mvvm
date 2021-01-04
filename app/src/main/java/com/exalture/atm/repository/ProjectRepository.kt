package com.exalture.atm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.exalture.atm.database.ExaltureDatabase
import com.exalture.atm.database.asDomainModel
import com.exalture.atm.domain.ExaltureProjects
import com.exalture.atm.network.ExaltureApi
import com.exalture.atm.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProjectRepository(private val database: ExaltureDatabase) {
    suspend fun refreshProjects() {
        withContext(Dispatchers.IO) {
            val projects = ExaltureApi.retrofitService.getPortfolio()
            database.projectsDao.insertAll(projects.asDatabaseModel())
        }
    }

    val projects: LiveData<List<ExaltureProjects>> =
        Transformations.map(database.projectsDao.getProjects()) {
            it.asDomainModel()
        }
}