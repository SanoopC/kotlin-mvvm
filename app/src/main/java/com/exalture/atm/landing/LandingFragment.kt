package com.exalture.atm.landing

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.exalture.atm.Config
import com.exalture.atm.R
import com.exalture.atm.SharedPreference
import com.exalture.atm.database.ExaltureDatabase
import com.exalture.atm.databinding.DialogAccountTypeBinding
import com.exalture.atm.databinding.LandingFragmentBinding
import com.exalture.atm.utils.CustomOnEditorActionListener


class LandingFragment : Fragment() {

    companion object {
        fun newInstance() = LandingFragment()
    }

    private lateinit var viewModel: LandingViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This callback will only be called when MyFragment is at least Started.
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    // Handle the back button event
                    activity?.finish()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: LandingFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.landing_fragment, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = ExaltureDatabase.getInstance(application).accountDatabaseDao
        val viewModelFactory = LandingViewModelFactory(dataSource, application)
        val sharedPreference = SharedPreference(context)

        viewModel = ViewModelProvider(this, viewModelFactory).get(LandingViewModel::class.java)
        binding.viewModel = viewModel

        binding.keyboardListener = object : CustomOnEditorActionListener {
            override fun onEditorAction() {
                viewModel.onSubmitButtonClick()
            }
        }

        binding.lifecycleOwner = this

        viewModel.validationStatus.observe(viewLifecycleOwner, Observer { status ->
            when (status) {
                LandingViewModel.ValidationStatus.ACCOUNT_NUMBER_MISSING -> {
                    binding.editTextAccountNumber.error = getString(R.string.error_required_validation)
                    binding.editTextAccountNumber.requestFocus()
                }
                LandingViewModel.ValidationStatus.ACCOUNT_NUMBER_INVALID -> {
                    binding.editTextAccountNumber.error =
                        getString(R.string.error_account_number_invalid)
                    binding.editTextAccountNumber.requestFocus()
                }
                LandingViewModel.ValidationStatus.ACCOUNT_NUMBER_NOT_REGISTERED -> {
                    binding.editTextAccountNumber.error = getString(R.string.error_not_registered)
                    binding.editTextAccountNumber.requestFocus()
                }
                else -> binding.editTextAccountNumber.error = null
            }
        })

        viewModel.navigateToAccountTypeDialog.observe(
            viewLifecycleOwner,
            Observer { accountNumber ->
                accountNumber?.let {
                    sharedPreference.saveAccountNumber(Config.ACCOUNT_NUMBER_KEY, it)
                    openAccountTypeDialog()
                }
            })
        binding.aboutUs.setOnClickListener {
            findNavController().navigate(LandingFragmentDirections.actionLandingFragmentToAboutFragment())
            viewModel.doneNavigation()
        }
        binding.createAccountText.setOnClickListener {
            findNavController().navigate(LandingFragmentDirections.actionLandingFragmentToCreateAccountFragment())
            viewModel.doneNavigation()
        }
        binding.gmap.setOnClickListener {
            val gmmIntentUri =
                Uri.parse("geo:10.0129509,76.3466792?q=" + Uri.encode("Exalture Software Labs Private Limited"))
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            startActivity(mapIntent)
        }

        return binding.root
    }

    private fun openAccountTypeDialog() {
        val dialogAccountType = Dialog(requireActivity(), R.style.df_dialog)
        val dialogAccountTypeBinding: DialogAccountTypeBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_account_type,
            null,
            false
        )
        val dialogViewModel = ViewModelProvider(this).get(AccountTypeViewModel::class.java)

        dialogAccountType.setContentView(dialogAccountTypeBinding.root)
        dialogAccountTypeBinding.accountTypeViewModel = dialogViewModel

        dialogViewModel.isSavingsAccount.observe(viewLifecycleOwner, Observer {
            //TODO: Need to add logic
            if (it != null) {
                dialogAccountType.dismiss()
                findNavController().navigate(LandingFragmentDirections.actionLandingFragmentToDashboardFragment())
                dialogViewModel.doneNavigation()
            }
        })
        dialogAccountType.setCancelable(false)
        dialogAccountType.show()
        viewModel.doneNavigation()
    }

}