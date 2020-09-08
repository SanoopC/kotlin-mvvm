package com.exalture.atm.statement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exalture.atm.Config

class MiniStatementViewModel : ViewModel() {
    private val _transaction = MutableLiveData<List<MiniStatementFragment.Transaction>>(
        // TODO: Remove dummy values
        listOf(
            MiniStatementFragment.Transaction(
                100023,
                "27 Aug 2020",
                "Deposit",
                "5,000.00",
                Config.TYPE_DEPOSIT
            ),
            MiniStatementFragment.Transaction(
                100024,
                "07 Sep 2020",
                "Withdraw from ATM",
                "1,000.00",
                Config.TYPE_WITHDRAW
            ),
            MiniStatementFragment.Transaction(
                100025,
                "10 Aug 2020",
                "Withdraw from ATM",
                "500.00",
                Config.TYPE_WITHDRAW
            ),
            MiniStatementFragment.Transaction(
                100026,
                "09 Aug 2020",
                "EMI",
                "1,000.00",
                Config.TYPE_TRANSFER
            ),
            MiniStatementFragment.Transaction(
                100027,
                "09 Aug 2020",
                "Withdraw from ATM",
                "200.00",
                Config.TYPE_WITHDRAW
            )
        )
    )
    val transaction: LiveData<List<MiniStatementFragment.Transaction>>
        get() = _transaction

}