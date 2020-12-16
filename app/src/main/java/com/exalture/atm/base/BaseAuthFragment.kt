package com.exalture.atm.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.exalture.atm.R
import com.exalture.atm.pin.PinNumberFragment
import com.exalture.atm.pin.PinNumberViewModel

const val NAVIGATION_KEY = "NAV_KEY"

abstract class BaseAuthFragment : Fragment() {
    private val userViewModel: PinNumberViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = findNavController()

        val currentBackStackEntry = navController.currentBackStackEntry!!
        val savedStateHandle = currentBackStackEntry.savedStateHandle

        userViewModel.reset()
        savedStateHandle.getLiveData<Boolean>(PinNumberFragment.PIN_AUTHENTICATED)
            .observe(currentBackStackEntry, Observer { success ->
                if (!success) {
                    navController.popBackStack()
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel.isAuthenticated.observe(viewLifecycleOwner, Observer { success ->
            if (!success) {
                try {
                    findNavController().navigate(R.id.passCodeFragment)
                } catch (e: IllegalArgumentException) {
                    activity?.finish()
                }
            }
        })
    }

}