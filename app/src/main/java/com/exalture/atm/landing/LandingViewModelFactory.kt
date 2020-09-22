package com.exalture.atm.landing

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exalture.atm.database.AccountDatabaseDao

class LandingViewModelFactory(
    private val dataSource: AccountDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LandingViewModel::class.java)) {
            return LandingViewModel(application, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}