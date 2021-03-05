package com.exalture.atm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.exalture.atm.database.AccountData
import com.exalture.atm.database.ExaltureDatabase
import com.exalture.atm.database.asDomainModel
import com.exalture.atm.domain.ExaltureProjects
import com.exalture.atm.network.ExaltureApi
import com.exalture.atm.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountRepository @Inject constructor(private val database: ExaltureDatabase) {
    suspend fun insertAccount(newAccount: AccountData) {
        withContext(Dispatchers.IO) {
            database.accountDatabaseDao.insert(newAccount)
        }
    }

    suspend fun isPhoneRegistered(phoneNumber: String): Boolean {
        return withContext(Dispatchers.IO) {
            database.accountDatabaseDao.checkPhoneInDatabase(phoneNumber)?.isNotEmpty() ?: false
        }
    }

    suspend fun getRecentAccountNumber(): Long? {
        return withContext(Dispatchers.IO) {
            database.accountDatabaseDao.getRecentAccountNumber()
        }
    }

    suspend fun isAccountNumberRegistered(accountNumber: String): Boolean {
        return withContext(Dispatchers.IO) {
            database.accountDatabaseDao.checkAccountInDatabase(accountNumber).isNotEmpty()
        }
    }
    suspend fun getAccountDetails(accountNumber: String): AccountData? {
        return withContext(Dispatchers.IO) {
            database.accountDatabaseDao.get(accountNumber)
        }
    }
}