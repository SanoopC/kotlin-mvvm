package com.exalture.atm.statement

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.exalture.atm.Config
import com.exalture.atm.R

//class MiniStatementAdapter(private val list: List<MiniStatementFragment.Transaction>) :
class MiniStatementAdapter() :
    ListAdapter<MiniStatementFragment.Transaction, MiniStatementAdapter.TransactionViewHolder>(
        MiniStatementDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction: MiniStatementFragment.Transaction = getItem(position)
        holder.bind(transaction)
    }

    class TransactionViewHolder private constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private var mIconView: ImageView? = null
        private var mTypeView: TextView? = null
        private var mDateView: TextView? = null
        private var mRemarksView: TextView? = null
        private var mAmountView: TextView? = null


        init {
            mDateView = itemView.findViewById(R.id.transaction_date)
            mRemarksView = itemView.findViewById(R.id.transaction_remarks)
            mAmountView = itemView.findViewById(R.id.transaction_amount)
            mTypeView = itemView.findViewById(R.id.transaction_type)
            mIconView = itemView.findViewById(R.id.transaction_icon)
        }

        fun bind(transaction: MiniStatementFragment.Transaction) {
            mDateView?.text = transaction.transactionDate
            mRemarksView?.text = transaction.transactionRemarks
            mAmountView?.text = transaction.transactionAmount.toString()
            if (transaction.transactionType == Config.TYPE_DEPOSIT) {
                mTypeView?.text = "Dr"
                mTypeView?.setTextColor(Color.GREEN)
                mIconView?.setImageResource(R.drawable.ic_deposit)
            } else if (transaction.transactionType == Config.TYPE_WITHDRAW) {
                mTypeView?.text = "Cr"
                mTypeView?.setTextColor(Color.RED)
                mIconView?.setImageResource(R.drawable.ic_withdraw)
            } else {
                mTypeView?.text = "Cr"
                mTypeView?.setTextColor(Color.RED)
                mIconView?.setImageResource(R.drawable.ic_transfer)
            }
        }

        companion object {
            fun from(parent: ViewGroup): TransactionViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.transaction_item, parent, false)
                return TransactionViewHolder(view)
            }
        }
    }
}

class MiniStatementDiffCallback : DiffUtil.ItemCallback<MiniStatementFragment.Transaction>() {
    override fun areItemsTheSame(
        oldItem: MiniStatementFragment.Transaction,
        newItem: MiniStatementFragment.Transaction
    ): Boolean {
        return oldItem.transactionId == newItem.transactionId
    }

    override fun areContentsTheSame(
        oldItem: MiniStatementFragment.Transaction,
        newItem: MiniStatementFragment.Transaction
    ): Boolean {
        return oldItem == newItem
    }

}
