package com.exalture.atm.landing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class AccountTypeViewModel @Inject constructor() : ViewModel() {
    private var _isSavingsAccount = MutableLiveData<Boolean?>()
    val isSavingsAccount: LiveData<Boolean?>
        get() = _isSavingsAccount

    fun setSavings() {
        _isSavingsAccount.value = true
    }

    fun setCurrent() {
        _isSavingsAccount.value = true
    }

    fun doneNavigation() {
        _isSavingsAccount.value = null
    }

}
