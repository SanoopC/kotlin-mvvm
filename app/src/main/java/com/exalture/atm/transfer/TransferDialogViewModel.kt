package com.exalture.atm.transfer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TransferDialogViewModel : ViewModel() {
    private var _okButtonClicked = MutableLiveData<Boolean?>()
    val okButtonClicked: LiveData<Boolean?>
        get() = _okButtonClicked

    fun closeDialog() {
        _okButtonClicked.value = true
    }

    fun doneNavigation() {
        _okButtonClicked.value = null
    }

}
