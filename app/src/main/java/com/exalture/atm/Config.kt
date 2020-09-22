package com.exalture.atm

class Config {
    companion object {
        const val ACCOUNT_NUMBER_KEY = "account_number"
        const val MINIMUM_ACCOUNT_NUMBER_LENGTH = 10
        const val INITIAL_ACCOUNT_NUMBER = 8008008001
        const val TYPE_DEPOSIT = 1
        const val TYPE_WITHDRAW = 2
        const val TYPE_TRANSFER = 3
        const val MINIMUM_NAME_LENGTH = 3
        const val MINIMUM_ADDRESS_LENGTH = 5
        const val MINIMUM_PHONE_LENGTH = 10
        const val MINIMUM_CURRENT_ACCOUNT_BALANCE = 1000
        const val PREFERENCES_NAME = "eATM"
    }
}