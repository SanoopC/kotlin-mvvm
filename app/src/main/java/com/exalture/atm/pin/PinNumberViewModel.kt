package com.exalture.atm.pin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PinNumberViewModel : ViewModel() {

    private var _isPinVisible = MutableLiveData<Boolean>()
        .apply { value = false }
    val isPinVisible: LiveData<Boolean>
        get() = _isPinVisible

    fun handleVisibility() {
        _isPinVisible.value = !_isPinVisible.value!!
    }

    private var _isAuthenticated = MutableLiveData<Boolean>(false)

    val isAuthenticated: LiveData<Boolean>
        get() = _isAuthenticated

    fun authenticated() {
        _isAuthenticated.value = true
    }

    fun reset() {
        _isAuthenticated.value = false
    }

    private var _navigateBack = MutableLiveData<Boolean>()

    val navigateBack: LiveData<Boolean>
        get() = _navigateBack

    fun backNavigation() {
        _navigateBack.value = true
    }

    fun doneNavigation() {
        _navigateBack.value = false
        _isAuthenticated.value = false
    }

}