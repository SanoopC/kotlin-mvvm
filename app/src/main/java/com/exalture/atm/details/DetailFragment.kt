package com.exalture.atm.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.exalture.atm.R
import com.exalture.atm.databinding.DetailsFragmentBinding

class DetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding = DetailsFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val marsProperty = DetailFragmentArgs.fromBundle(requireArguments()).selectedItem
        val viewModelFactory = DetailViewModelFactory(marsProperty, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}