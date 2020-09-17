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
import com.exalture.atm.databinding.TransferSuccessDialogBinding

class TransferSuccessDialog : DialogFragment() {

    companion object {
        const val TAG = "TransferSuccessDialog"
        fun newInstance() = TransferSuccessDialog()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: TransferSuccessDialogBinding =
            DataBindingUtil.inflate(inflater, R.layout.transfer_success_dialog, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

}