package com.exalture.atm.details

import android.app.Application
import androidx.lifecycle.*
import com.exalture.atm.network.ExaltureProjects

class DetailViewModel(
    exaltureProjects: ExaltureProjects,
    application: Application
) : AndroidViewModel(application) {
    private val _selectedProject = MutableLiveData<ExaltureProjects>()

    // The external LiveData for the SelectedProject
    val selectedProject: LiveData<ExaltureProjects>
        get() = _selectedProject

    // Initialize the _selectedProject MutableLiveData
    init {
        _selectedProject.value = exaltureProjects
    }

}