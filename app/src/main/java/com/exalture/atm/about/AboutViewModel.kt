package com.exalture.atm.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exalture.atm.network.ExaltureApi
import com.exalture.atm.network.ExaltureProjects
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }
class AboutViewModel : ViewModel() {
    // The internal MutableLiveData String that stores the most recent response
    private val _status = MutableLiveData<ApiStatus>()

    // The external immutable LiveData for the response String
    val status: LiveData<ApiStatus>
        get() = _status

    private val _projects = MutableLiveData<List<ExaltureProjects>>()

    val projects: LiveData<List<ExaltureProjects>>
        get() = _projects

    // Internally, we use a MutableLiveData to handle navigation to the selected property
    private val _navigateToSelectedProject = MutableLiveData<ExaltureProjects>()

    // The external immutable LiveData for the navigation property
    val navigateToSelectedProject: LiveData<ExaltureProjects>
        get() = _navigateToSelectedProject

    /**
     * Call getPortfolio() on init so we can display immediately.
     */
    init {
        getPortfolio()
    }

    /**
     * Sets the value of the response LiveData to the Mars API status or the successful number of
     * Mars properties retrieved.
     */
    private fun getPortfolio() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _projects.value = ExaltureApi.retrofitService.getPortfolio()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _projects.value = ArrayList()
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
}

