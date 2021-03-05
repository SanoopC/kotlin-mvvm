package com.exalture.atm.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exalture.atm.about.ApiStatus
import com.exalture.atm.domain.ExaltureProjects
import com.exalture.atm.repository.ProjectRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var projectRepository: ProjectRepository

    private val _selectedProject = MutableLiveData<ExaltureProjects>()

    // The external LiveData for the SelectedProject
    val selectedProject: LiveData<ExaltureProjects>
        get() = _selectedProject

    // Initialize the _selectedProject MutableLiveData
//    init {
//        _selectedProject.value = exaltureProjects
//    }

    fun setSelectedProjects(projectId: String) {
        viewModelScope.launch {
            _selectedProject.value = projectRepository.getProjectsById(projectId)
        }
    }
}