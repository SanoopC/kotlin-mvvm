package com.exalture.atm.statement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.exalture.atm.Config
import com.exalture.atm.R
import com.exalture.atm.databinding.MiniStatementFragmentBinding
import kotlinx.android.synthetic.main.mini_statement_fragment.*

class MiniStatementFragment : Fragment() {
    data class Transaction(
        val transactionId: Int,
        val transactionDate: String,
        val transactionRemarks: String,
        val transactionAmount: String,
        val transactionType: Int
    )

    companion object {
        fun newInstance() = MiniStatementFragment()
    }

    private lateinit var viewModel: MiniStatementViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: MiniStatementFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.mini_statement_fragment, container, false)
        viewModel = ViewModelProvider(this).get(MiniStatementViewModel::class.java)
        binding.miniStatementViewModel = viewModel
        binding.lifecycleOwner = this
        val adapter = MiniStatementAdapter()
        binding.miniStatementRecyclerView.adapter = adapter
        binding.miniStatementRecyclerView.addItemDecoration(
            DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        )
        viewModel.transaction.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        return binding.root
    }
}