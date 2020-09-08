package com.exalture.atm.landing

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.exalture.atm.AccountTypeViewModel
import com.exalture.atm.R
import com.exalture.atm.databinding.DialogAccountTypeBinding
import com.exalture.atm.databinding.LandingFragmentBinding


class LandingFragment : Fragment() {

    companion object {
        fun newInstance() = LandingFragment()
    }

    private lateinit var dialogAccountType: Dialog
    private lateinit var viewModel: LandingViewModel
    private lateinit var binding: LandingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.landing_fragment, container, false)


        binding.submitButton.setOnClickListener {
            dialogAccountType = Dialog(requireActivity(), R.style.df_dialog)
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
                if (it == true) {
                    // TODO: Need to handle
                }
                if (it != null) {
                    findNavController().navigate(LandingFragmentDirections.actionLandingFragmentToDashboardFragment())
                    dialogViewModel.doneNavigation()
                    dialogAccountType.dismiss()
                }
            })
            dialogAccountType.setCancelable(true)
            dialogAccountType.show()
        }
        binding.createAccount.setOnClickListener {
            findNavController().navigate(LandingFragmentDirections.actionLandingFragmentToCreateAccountFragment())
        }
        binding.gmap.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:10.0129509,76.3466792?q=" + Uri.encode("Exalture Software Labs Private Limited"))
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            startActivity(mapIntent)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LandingViewModel::class.java)
    }

}