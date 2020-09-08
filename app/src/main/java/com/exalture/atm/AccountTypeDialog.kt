package com.exalture.atm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_account_type.view.*

class AccountTypeDialog : DialogFragment() {

    companion object {
        const val TAG = "SimpleDialog"
        fun newInstance() = AccountTypeDialog()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_account_type, container, false)
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
        view.btnCurrent.setOnClickListener {
            // TODO: Do some task here
            dismiss()
        }
    }

}