package com.exalture.atm.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exalture.atm.database.AccountDatabaseDao

class AccountDetailsViewModelFactory(
    private val accountNumber: Long,
    private val dataSource: AccountDatabaseDao
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountDetailsViewModel::class.java)) {
            return AccountDetailsViewModel(accountNumber, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}