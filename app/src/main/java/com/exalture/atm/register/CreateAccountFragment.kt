package com.exalture.atm.register

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.exalture.atm.MyApplication
import com.exalture.atm.R
import com.exalture.atm.database.AccountData
import com.exalture.atm.databinding.AccountSuccessDialogBinding
import com.exalture.atm.databinding.CreateAccountFragmentBinding
import javax.inject.Inject

class CreateAccountFragment : Fragment() {

    @Inject
    lateinit var viewModel: CreateAccountViewModel

    @Inject
    lateinit var dialogViewModel: AccountSuccessDialogViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.registrationComponent()
            .create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: CreateAccountFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.create_account_fragment, container, false)
        binding.createAccountViewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.isSavingAccount.observe(viewLifecycleOwner, Observer { isSavings ->
            if (isSavings) {
                binding.textInputAmount.hint =
                    getString(R.string.hint_account_opening_amount_optional)
            } else {
                binding.textInputAmount.hint = getString(R.string.hint_account_opening_amount)
            }
        })

        viewModel.isRegisteredNumber.observe(viewLifecycleOwner, Observer { isRegistered ->
            if (isRegistered) {
                binding.editTextPhone.error = getString(R.string.error_number_registered)
                binding.editTextPhone.requestFocus()
            } else {
                viewModel.createAccount()
            }
        })
        viewModel.navigateToSuccessDialog.observe(viewLifecycleOwner, Observer { accountData ->
            accountData?.let {
                openSuccessDialog(accountData)
            }
        })
        viewModel.navigateToaLanding.observe(viewLifecycleOwner, Observer {
            it?.let {
                findNavController().navigate(CreateAccountFragmentDirections.actionCreateAccountFragmentToLandingFragment())
            }
        })
        return binding.root
    }

    private fun openSuccessDialog(accountData: AccountData?) {
        val successDialog = Dialog(requireActivity(), R.style.df_dialog)
        val accountSuccessDialogBinding: AccountSuccessDialogBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.account_success_dialog,
                null,
                false
            )


        successDialog.setContentView(accountSuccessDialogBinding.root)
        accountSuccessDialogBinding.dialogViewModel = dialogViewModel
        accountSuccessDialogBinding.accountData = accountData
        accountSuccessDialogBinding.executePendingBindings()

        dialogViewModel.navigateToAuthentication.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                findNavController().navigate(
                    CreateAccountFragmentDirections.actionCreateAccountFragmentToLandingFragment()
                )
                dialogViewModel.doneNavigation()
                successDialog.dismiss()
            }
        })
        successDialog.setCancelable(false)
        successDialog.show()

        viewModel.doneNavigation()
    }

}