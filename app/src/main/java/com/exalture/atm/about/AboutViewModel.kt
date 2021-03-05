package com.exalture.atm.about

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exalture.atm.domain.ExaltureProjects
import com.exalture.atm.repository.ProjectRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ApiStatus { LOADING, ERROR, DONE }
class AboutViewModel @Inject constructor(private val projectRepository: ProjectRepository) :
    ViewModel() {
    // The internal MutableLiveData String that stores the most recent response
    private val _status = MutableLiveData<ApiStatus>()

    // The external immutable LiveData for the response String
    val status: LiveData<ApiStatus>
        get() = _status

    // Internally, we use a MutableLiveData to handle navigation to the selected property
    private val _openSelectedProject = MutableLiveData<String>()

    // The external immutable LiveData for the navigation property
    val openSelectedProject: LiveData<String>
        get() = _openSelectedProject

//    var mylist = ObservableArrayList<String>().addAll(listOf("ss","ss"))

    /**
     * Call refreshDataFromRepository() on init so we can display immediately.
     */
    init {
        refreshDataFromRepository()
    }

    val projects = projectRepository.projects

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                projectRepository.refreshProjects()
                _status.value = ApiStatus.DONE
            } catch (exception: Exception) {
                Log.e("Exception", exception.toString())
                _status.value = ApiStatus.ERROR
            }
        }
    }

    /**
     * When the property is clicked, set the [_openSelectedProject] [MutableLiveData]
     * @param projectId The [ExaltureProjects] that was clicked on.
     */
    fun openProjectDetails(projectId: String) {
        _openSelectedProject.value = projectId
    }

    /**
     * After the navigation has taken place, make sure _openSelectedProject is set to null
     */
    fun displayPropertyDetailsComplete() {
        _openSelectedProject.value = null
    }
}

