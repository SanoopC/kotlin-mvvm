package com.exalture.atm.landing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exalture.atm.Config
import com.exalture.atm.repository.AccountRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class LandingViewModel @Inject constructor(
    val repository: AccountRepository
) : ViewModel() {

    private val viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var accountNumber = MutableLiveData<String>()

    private var _navigateToAccountTypeDialog = MutableLiveData<String>()

    val navigateToAccountTypeDialog: LiveData<String>
        get() = _navigateToAccountTypeDialog

    private var _validationStatus = MutableLiveData<ValidationStatus>()

    val validationStatus: LiveData<ValidationStatus>
        get() = _validationStatus

    enum class ValidationStatus {
        ACCOUNT_NUMBER_MISSING,
        ACCOUNT_NUMBER_INVALID,
        ACCOUNT_NUMBER_NOT_REGISTERED
    }

    fun onSubmitButtonClick() {
        if (isValid()) {
            checkAccountNumberInDatabase()
        }
    }

    private fun isValid(): Boolean {
        return when {
            accountNumber.value.isNullOrBlank() -> {
                _validationStatus.postValue(ValidationStatus.ACCOUNT_NUMBER_MISSING)
                false
            }
            accountNumber.value!!.trim().length < Config.MINIMUM_ACCOUNT_NUMBER_LENGTH -> {
                _validationStatus.postValue(ValidationStatus.ACCOUNT_NUMBER_INVALID)
                false
            }
            else -> {
                _validationStatus.value = null
                true
            }
        }
    }

    private fun checkAccountNumberInDatabase() {
        uiScope.launch {
            if (repository.isAccountNumberRegistered(accountNumber.value.toString())) {
                _navigateToAccountTypeDialog.value = accountNumber.value.toString()
                _validationStatus.value = null
            } else {
                _validationStatus.postValue(ValidationStatus.ACCOUNT_NUMBER_NOT_REGISTERED)
            }
        }
    }

    fun doneNavigation() {
        _navigateToAccountTypeDialog.value = null
        accountNumber.value = null
        _validationStatus.value = null
    }
}