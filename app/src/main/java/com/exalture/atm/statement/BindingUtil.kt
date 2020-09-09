package com.exalture.atm.statement

import android.graphics.Color
import android.view.View.GONE
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.exalture.atm.Config
import com.exalture.atm.R
import com.exalture.atm.convertLongToDateString
import com.exalture.atm.convertLongToTimeString

@BindingAdapter("transactionId")
fun TextView.setTransactionId(item: MiniStatementFragment.Transaction) {
    item.let {
        text = item.transactionId.toString()
    }
}

@BindingAdapter("transactionToAccount")
fun TextView.setTransactionAccount(item: MiniStatementFragment.Transaction) {
    item.let {
        if (item.transactionType == Config.TYPE_TRANSFER) {
            text = item.transactionToAccount.toString()
        }
    }
}

@BindingAdapter("accountViewVisibility")
fun ConstraintLayout.setToAccountVisibility(item: MiniStatementFragment.Transaction) {
    item.let {
        if (item.transactionType != Config.TYPE_TRANSFER) {
            visibility = GONE
        }
    }
}

@BindingAdapter("transactionDateFormatted")
fun TextView.setTransactionDateFormatted(item: MiniStatementFragment.Transaction) {
    item.let {
        text = convertLongToDateString(item.transactionDate)
    }
}

@BindingAdapter("transactionTimeFormatted")
fun TextView.setTransactionTimeFormatted(item: MiniStatementFragment.Transaction) {
    item.let {
        text = convertLongToTimeString(item.transactionDate)
    }
}

@BindingAdapter("transactionRemarks")
fun TextView.setTransactionRemarks(item: MiniStatementFragment.Transaction) {
    item.let {
        text = item.transactionRemarks
    }
}

@BindingAdapter("transactionAmount")
fun TextView.setTransactionAmount(item: MiniStatementFragment.Transaction) {
    item.let {
        text = item.transactionAmount
    }
}

@BindingAdapter("transactionType")
fun TextView.setTransactionType(item: MiniStatementFragment.Transaction) {
    item.let {
        text = if (item.transactionType == Config.TYPE_DEPOSIT) {
            setTextColor(Color.GREEN)
            context.getString(R.string.text_dr)
        } else {
            setTextColor(Color.RED)
            context.getString(R.string.text_cr)
        }
    }
}

@BindingAdapter("transactionIcon")
fun ImageView.setTransactionIcon(item: MiniStatementFragment.Transaction) {
    item.let {
        setImageResource(
            when (item.transactionType) {
                Config.TYPE_DEPOSIT -> R.drawable.ic_deposit
                Config.TYPE_WITHDRAW -> R.drawable.ic_withdraw
                else -> R.drawable.ic_transfer
            }
        )
    }
}