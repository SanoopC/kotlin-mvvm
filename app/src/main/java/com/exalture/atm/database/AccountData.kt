package com.exalture.atm.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account_table")
data class AccountData(
    @PrimaryKey(autoGenerate = true)
    val accountNumber: Long = 8008008001,

    @ColumnInfo(name = "pin")
    val pin: String = "1234",

    @ColumnInfo(name = "account_balance")
    var accountBalance: Double? = 0.0,

    @ColumnInfo(name = "phone_number")
    val phoneNumber: String = "",

    @ColumnInfo(name = "full_name")
    val fullName: String = "",

    @ColumnInfo(name = "address")
    val address: String = ""
)