package com.exalture.atm.pin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PinNumberViewModel : ViewModel() {

    private var _isPinVisible = MutableLiveData<Boolean>(false)
    val isPinVisible: LiveData<Boolean>
        get() = _isPinVisible

    fun handleVisibility() {
        _isPinVisible.value = !_isPinVisible.value!!
    }

}