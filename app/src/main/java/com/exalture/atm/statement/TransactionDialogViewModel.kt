package com.exalture.atm.statement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TransactionDialogViewModel : ViewModel() {
    private var _closeButtonClicked = MutableLiveData<Boolean?>()
    val closeButtonClicked: LiveData<Boolean?>
        get() = _closeButtonClicked

    fun closeDialog() {
        _closeButtonClicked.value = true
    }

    fun doneNavigation() {
        _closeButtonClicked.value = null
    }

}
