package com.exalture.atm.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AccountSuccessDialogViewModel : ViewModel() {
    private var _navigateToAuthentication = MutableLiveData<Boolean?>()
    val navigateToAuthentication: LiveData<Boolean?>
        get() = _navigateToAuthentication

    fun openAuthentication() {
        _navigateToAuthentication.value = true
    }

    fun doneNavigation() {
        _navigateToAuthentication.value = null
    }

}
