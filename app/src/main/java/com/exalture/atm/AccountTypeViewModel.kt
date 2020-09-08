package com.exalture.atm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AccountTypeViewModel : ViewModel() {
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
