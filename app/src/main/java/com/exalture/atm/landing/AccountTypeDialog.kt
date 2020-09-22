package com.exalture.atm.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.exalture.atm.R
import com.exalture.atm.databinding.DialogAccountTypeBinding
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
        val binding: DialogAccountTypeBinding =
            DataBindingUtil.inflate(inflater, R.layout.dialog_account_type, container, false)
        return binding.root
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