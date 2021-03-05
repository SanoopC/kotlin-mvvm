package com.exalture.atm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.exalture.atm.database.ExaltureDatabase
import com.exalture.atm.database.ProjectsDao
import com.exalture.atm.database.asDomainModel
import com.exalture.atm.domain.ExaltureProjects
import com.exalture.atm.network.ApiService
import com.exalture.atm.network.ExaltureApi
import com.exalture.atm.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProjectRepository @Inject constructor(private val projectsDao: ProjectsDao) {

    @Inject
    lateinit var retrofitService: ApiService

    suspend fun refreshProjects() {
        withContext(Dispatchers.IO) {
            val remoteProjects = retrofitService.getPortfolio()
            projectsDao.insertAll(remoteProjects.asDatabaseModel())
        }
    }

    suspend fun getProjectsById(projectId: String): ExaltureProjects {
        return withContext(Dispatchers.IO) {
            val project = projectsDao.getProjectsById(projectId)
            return@withContext project.asDomainModel()
        }
    }

    val projects: LiveData<List<ExaltureProjects>> =
        Transformations.map(projectsDao.getProjects()) {
            it.asDomainModel()
        }

}