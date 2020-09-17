package com.exalture.atm.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exalture.atm.database.AccountData
import com.exalture.atm.database.AccountDatabaseDao
import kotlinx.coroutines.*

class AccountDetailsViewModel(
    private val accountNumber: Long,
    private val database: AccountDatabaseDao
) : ViewModel() {
    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val accountDetails = MutableLiveData<AccountData?>()
    var mAccountNumber = ""
    var mHolderName = ""
    var mCommunicationAddress = ""
    var mPhoneNumber = ""

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
            accountDetails.value = getAccountFromDatabase()
            mAccountNumber = accountDetails.value?.accountNumber.toString()
            mHolderName = accountDetails.value?.fullName.toString()
            mCommunicationAddress = accountDetails.value?.address.toString()
            mPhoneNumber = accountDetails.value?.phoneNumber.toString()
        }
    }

    private suspend fun getAccountFromDatabase(): AccountData? {
        return withContext(Dispatchers.IO) {
            database.get(accountNumber)
        }
    }

    fun onClose() {
        _navigateToLandingFragment.value = true
    }
}