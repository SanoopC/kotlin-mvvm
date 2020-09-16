package com.exalture.atm.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AccountDatabaseDao {
    @Insert
    fun insert(accountData: AccountData)

    @Update
    fun update(accountData: AccountData)

    @Query("SELECT * from account_table WHERE accountNumber = :accountNumber")
    fun get(accountNumber: Long): AccountData?

    @Query("SELECT accountNumber from account_table ORDER BY accountNumber DESC LIMIT 1")
    fun getRecentAccountNumber(): Long?

    @Query("SELECT * from account_table WHERE phone_number = :phoneNumber")
    fun checkPhoneInDatabase(phoneNumber: String): List<AccountData>?

}