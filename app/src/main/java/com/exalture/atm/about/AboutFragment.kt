package com.exalture.atm.about

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.exalture.atm.databinding.AboutFragmentBinding


class AboutFragment : Fragment() {

    private val viewModel: AboutViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(
            this,
            AboutViewModel.Factory(activity.application)
        ).get(AboutViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = AboutFragmentBinding.inflate(inflater)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        binding.photosGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })
        viewModel.navigateToSelectedProject.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController()
                    .navigate(AboutFragmentDirections.actionAboutFragmentToDetailFragment(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })
        binding.shareButton.setOnClickListener {
            val myIntent = Intent(Intent.ACTION_SEND)
            myIntent.type = "text/plain"
            val shareSub = "http://www.exalture.com/portfolio"
            myIntent.putExtra(Intent.EXTRA_TEXT, shareSub)
            startActivity(Intent.createChooser(myIntent, "Share using"))
        }
        return binding.root
    }


}