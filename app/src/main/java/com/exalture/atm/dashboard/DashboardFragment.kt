package com.exalture.atm.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.exalture.atm.Config
import com.exalture.atm.R
import com.exalture.atm.SharedPreference
import com.exalture.atm.databinding.DashboardFragmentBinding

class DashboardFragment : Fragment() {

    companion object {
        fun newInstance() = DashboardFragment()
    }

    private lateinit var viewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: DashboardFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.dashboard_fragment, container, false)
        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        binding.dashboardModel = viewModel
        val sharedPreference = SharedPreference(context)
        sharedPreference.getAccountNumber(Config.ACCOUNT_NUMBER_KEY)
        binding.accountDetails.setOnClickListener {
            findNavController().navigate(
                DashboardFragmentDirections.actionDashboardFragmentToAccountDetailsFragment(
                    sharedPreference.getAccountNumber(Config.ACCOUNT_NUMBER_KEY)
                )
            )
        }
        binding.depositButton.setOnClickListener {
            findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToAmountFragment())
        }
        binding.withdrawButton.setOnClickListener {
            findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToPassCodeFragment())
        }
        binding.statementButton.setOnClickListener {
            findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToMiniStatementFragment())
        }
        binding.balanceButton.setOnClickListener {
            findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToBalanceFragment())
        }
        binding.pinChangeButton.setOnClickListener {
            findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToChangePinFragment())
        }
        binding.transferButton.setOnClickListener {
            findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToTransferFragment())
        }
        binding.othersButton.setOnClickListener {
            findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToPhoneFragment())
        }
        return binding.root
    }
}