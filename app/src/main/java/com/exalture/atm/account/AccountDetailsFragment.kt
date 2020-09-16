package com.exalture.atm.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.exalture.atm.R
import com.exalture.atm.database.AccountDatabase
import com.exalture.atm.databinding.AccountDetailsFragmentBinding

class AccountDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = AccountDetailsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: AccountDetailsFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.account_details_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val arguments = AccountDetailsFragmentArgs.fromBundle(requireArguments())
        val dataSource = AccountDatabase.getInstance(application).accountDatabaseDao
        val viewModelFactory = AccountDetailsViewModelFactory(arguments.accountNumber, dataSource)
        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(AccountDetailsViewModel::class.java)
        binding.accountViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigateToLandingFragment.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController()
                    .navigate(AccountDetailsFragmentDirections.actionAccountDetailsFragmentToLandingFragment())
                viewModel.doneNavigation()
            }
        })
        return binding.root
    }

}