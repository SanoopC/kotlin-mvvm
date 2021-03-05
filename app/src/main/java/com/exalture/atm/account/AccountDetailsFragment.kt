package com.exalture.atm.account

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.exalture.atm.MyApplication
import com.exalture.atm.R
import com.exalture.atm.databinding.AccountDetailsFragmentBinding
import javax.inject.Inject

class AccountDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: AccountDetailsViewModel by viewModels {
        viewModelFactory
    }

    private val params by navArgs<AccountDetailsFragmentArgs>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.accountDetailsComponent()
            .create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: AccountDetailsFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.account_details_fragment, container, false)
        binding.accountViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigateToLandingFragment.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController()
                    .navigate(AccountDetailsFragmentDirections.actionAccountDetailsFragmentToDashboardFragment())
                viewModel.doneNavigation()
            }
        })

        viewModel.setAccountNumber(params.accountNumber)
        return binding.root
    }

}