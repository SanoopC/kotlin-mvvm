package com.exalture.atm.about

import android.app.Application
import androidx.lifecycle.*
import com.exalture.atm.database.ExaltureDatabase
import com.exalture.atm.domain.ExaltureProjects
import com.exalture.atm.repository.ProjectRepository
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }
class AboutViewModel(application: Application) : AndroidViewModel(application) {
    // The internal MutableLiveData String that stores the most recent response
    private val _status = MutableLiveData<ApiStatus>()

    // The external immutable LiveData for the response String
    val status: LiveData<ApiStatus>
        get() = _status

    // Internally, we use a MutableLiveData to handle navigation to the selected property
    private val _navigateToSelectedProject = MutableLiveData<ExaltureProjects>()

    // The external immutable LiveData for the navigation property
    val navigateToSelectedProject: LiveData<ExaltureProjects>
        get() = _navigateToSelectedProject

    private val projectRepository = ProjectRepository(ExaltureDatabase.getInstance(application))

    /**
     * Call getPortfolio() on init so we can display immediately.
     */
    init {
        refreshDataFromRepository()
    }

    val projects = projectRepository.projects

    /**
     * Sets the value of the response LiveData to the Mars API status or the successful number of
     * Mars properties retrieved.
     */
    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                projectRepository.refreshProjects()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
            }
        }
    }

    /**
     * When the property is clicked, set the [_navigateToSelectedProject] [MutableLiveData]
     * @param exaltureProjects The [ExaltureProjects] that was clicked on.
     */
    fun displayPropertyDetails(exaltureProjects: ExaltureProjects) {
        _navigateToSelectedProject.value = exaltureProjects
    }

    /**
     * After the navigation has taken place, make sure navigateToSelectedProperty is set to null
     */
    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProject.value = null
    }

    /**
     * Factory for constructing AboutViewModel with parameter
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AboutViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AboutViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}

