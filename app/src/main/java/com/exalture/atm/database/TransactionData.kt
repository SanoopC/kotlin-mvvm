package com.exalture.atm.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_table")
data class TransactionData(
    @PrimaryKey
    var transactionId: Long = 0L,

    @ColumnInfo(name = "account_number")
    var accountBalance: Long = 0L,

    @ColumnInfo(name = "time_milli")
    val transactionTimeMilli: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "remarks")
    val address: String = "",

    @ColumnInfo(name = "amount")
    val phoneNumber: Long = 0L,

    @ColumnInfo(name = "to_account_number")
    var toAccountNumber: Long = 0L
)
