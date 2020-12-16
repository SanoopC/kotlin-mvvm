package com.exalture.atm.statement

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.exalture.atm.R
import com.exalture.atm.base.BaseAuthFragment
import com.exalture.atm.databinding.MiniStatementFragmentBinding
import com.exalture.atm.databinding.TransactionDetailsDialogBinding

class MiniStatementFragment : BaseAuthFragment() {
    data class Transaction(
        val transactionId: Long,
        val transactionDate: Long,
        val transactionRemarks: String,
        val transactionAmount: String,
        val transactionType: Int,
        val transactionToAccount: Long
    )

    companion object {
        fun newInstance() = MiniStatementFragment()
    }

    private lateinit var viewModel: MiniStatementViewModel
    private lateinit var dialogTransactionDetails: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: MiniStatementFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.mini_statement_fragment, container, false)
        viewModel = ViewModelProvider(this).get(MiniStatementViewModel::class.java)
        binding.miniStatementViewModel = viewModel
        binding.lifecycleOwner = this
        val adapter = MiniStatementAdapter(MiniStatementListener { transactionId ->
            viewModel.onTransactionItemClicked(transactionId)
        })
        binding.miniStatementRecyclerView.adapter = adapter
        binding.miniStatementRecyclerView.addItemDecoration(
            DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        )
        viewModel.navigateToTransactionDetails.observe(
            viewLifecycleOwner,
            Observer { transactionItem ->
                transactionItem?.let {

                    dialogTransactionDetails = Dialog(requireActivity(), R.style.df_dialog)
                    val dialogTransactionDetailsBinding: TransactionDetailsDialogBinding =
                        DataBindingUtil.inflate(
                            LayoutInflater.from(context),
                            R.layout.transaction_details_dialog,
                            null,
                            false
                        )
                    val dialogViewModel =
                        ViewModelProvider(this).get(TransactionDialogViewModel::class.java)

                    dialogTransactionDetails.setContentView(dialogTransactionDetailsBinding.root)
                    dialogTransactionDetailsBinding.dialogViewModel = dialogViewModel
                    dialogTransactionDetailsBinding.transactionItem = transactionItem
                    dialogTransactionDetailsBinding.executePendingBindings()

                    dialogViewModel.closeButtonClicked.observe(viewLifecycleOwner, Observer {
                        if (it != null) {
                            dialogViewModel.doneNavigation()
                            dialogTransactionDetails.dismiss()
                        }
                    })
                    dialogTransactionDetails.setCancelable(true)

                    dialogTransactionDetails.show()

                    viewModel.onTransactionDetailsNavigated()
                }
            })
        viewModel.transaction.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        return binding.root
    }
}