package com.exalture.atm.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exalture.atm.database.AccountData
import com.exalture.atm.repository.AccountRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class AccountDetailsViewModel @Inject constructor(
    private val repository: AccountRepository
) : ViewModel() {
    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val accountDetails = MutableLiveData<AccountData?>()
    var mAccountNumber = ""


    fun setAccountNumber(accountNumber: String) {
        mAccountNumber = accountNumber
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val _navigateToLandingFragment = MutableLiveData<Boolean>()
    val navigateToLandingFragment: LiveData<Boolean>
        get() = _navigateToLandingFragment

    fun doneNavigation() {
        _navigateToLandingFragment.value = null
    }

    init {
        fetchAccountDetails()
    }

    private fun fetchAccountDetails() {
        uiScope.launch {
            accountDetails.value = repository.getAccountDetails(mAccountNumber)
        }
    }


    fun onClose() {
        _navigateToLandingFragment.value = true
    }
}