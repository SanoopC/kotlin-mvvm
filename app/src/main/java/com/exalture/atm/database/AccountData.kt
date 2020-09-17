package com.exalture.atm.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.exalture.atm.Config

@Entity(tableName = "account_table")
data class AccountData(
    @PrimaryKey(autoGenerate = true)
    val accountNumber: Long = 0L,

    @ColumnInfo(name = "pin")
    val pin: String = "1234",

    @ColumnInfo(name = "account_balance")
    var accountBalance: Double = 0.0,

    @ColumnInfo(name = "phone_number")
    val phoneNumber: String = "",

    @ColumnInfo(name = "full_name")
    val fullName: String = "",

    @ColumnInfo(name = "address")
    val address: String = ""
) {
    constructor(
        accountNumber: Long? = null,
        pin: String? = null,
        accountBalance: Double? = null,
        phoneNumber: String? = null,
        fullName: String? = null,
        address: String? = null
    ) : this(
        accountNumber ?: Config.INITIAL_ACCOUNT_NUMBER,
        pin ?: "1234",
        accountBalance ?: 0.0,
        phoneNumber ?: "",
        fullName ?: "",
        address ?: ""
    )
}
