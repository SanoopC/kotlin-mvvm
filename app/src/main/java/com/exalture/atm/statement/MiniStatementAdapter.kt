package com.exalture.atm.statement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.exalture.atm.databinding.TransactionItemBinding

class MiniStatementAdapter(private val clickListener: MiniStatementListener) :
    ListAdapter<MiniStatementFragment.Transaction, MiniStatementAdapter.TransactionViewHolder>(
        MiniStatementDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class TransactionViewHolder private constructor(val binding: TransactionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            transaction: MiniStatementFragment.Transaction,
            clickListener: MiniStatementListener
        ) {
            binding.transactionItem = transaction
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): TransactionViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TransactionItemBinding.inflate(layoutInflater, parent, false)
                return TransactionViewHolder(binding)
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

class MiniStatementListener(val clickListener: (transaction: MiniStatementFragment.Transaction) -> Unit) {
    fun onClick(transaction: MiniStatementFragment.Transaction) =
        clickListener(transaction)
}