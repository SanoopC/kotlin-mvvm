package com.exalture.atm.balance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.exalture.atm.R

class BalanceFragment : Fragment() {

    companion object {
        fun newInstance() = BalanceFragment()
    }

    private lateinit var viewModel: BalanceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.balance_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BalanceViewModel::class.java)
        // TODO: Use the ViewModel
    }

}