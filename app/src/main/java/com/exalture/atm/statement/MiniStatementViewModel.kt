package com.exalture.atm.statement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exalture.atm.Config

class MiniStatementViewModel : ViewModel() {
    private val _navigateToTransactionDetails = MutableLiveData<MiniStatementFragment.Transaction>()
    val navigateToTransactionDetails
        get() = _navigateToTransactionDetails

    fun onTransactionItemClicked(transactionId: MiniStatementFragment.Transaction) {
        _navigateToTransactionDetails.value = transactionId
    }

    fun onTransactionDetailsNavigated() {
        _navigateToTransactionDetails.value = null
    }

    private val _transaction = MutableLiveData<List<MiniStatementFragment.Transaction>>()
        .apply {
            value = listOf(
                MiniStatementFragment.Transaction(
                    123400001001,
                    1599563383000,
                    "Deposit",
                    "5,000.00",
                    Config.TYPE_DEPOSIT,
                    0
                ),
                MiniStatementFragment.Transaction(
                    123400001002,
                    1599476983000,
                    "Withdraw from ATM",
                    "1,000.00",
                    Config.TYPE_WITHDRAW,
                    0
                ),
                MiniStatementFragment.Transaction(
                    123400001003,
                    1599304183000,
                    "Withdraw from ATM",
                    "500.00",
                    Config.TYPE_WITHDRAW,
                    0
                ),
                MiniStatementFragment.Transaction(
                    123400001004,
                    1598353783000,
                    "EMI",
                    "1,000.00",
                    Config.TYPE_TRANSFER,
                    9841213132
                ),
                MiniStatementFragment.Transaction(
                    123400001005,
                    1596625783000,
                    "Withdraw from ATM",
                    "200.00",
                    Config.TYPE_WITHDRAW,
                    0
                )
            )
        }
    val transaction: LiveData<List<MiniStatementFragment.Transaction>>
        get() = _transaction

}