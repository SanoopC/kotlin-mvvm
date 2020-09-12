package com.exalture.atm.transfer

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

class TransferDialog : DialogFragment() {

    companion object {
        const val TAG = "TransferDialog"
        fun newInstance() = TransferDialog()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: TransactionDetailsDialogBinding =
            DataBindingUtil.inflate(inflater, R.layout.transfer_dialog, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

}