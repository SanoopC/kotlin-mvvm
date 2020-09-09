package com.exalture.atm.statement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.exalture.atm.R
import com.exalture.atm.databinding.TransactionDetailsDialogBinding
import kotlinx.android.synthetic.main.dialog_account_type.view.*

class TransactionDetailsDialog : DialogFragment() {

    companion object {
        const val TAG = "TransactionDetailsDialog"
        fun newInstance() = TransactionDetailsDialog()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: TransactionDetailsDialogBinding =
            DataBindingUtil.inflate(inflater, R.layout.transaction_details_dialog, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners(view)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setupClickListeners(view: View) {
        view.btnSavings.setOnClickListener {
            // TODO: Do some task here
            dismiss()
        }
    }

}