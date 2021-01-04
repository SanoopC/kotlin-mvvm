package com.exalture.atm.pin

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.exalture.atm.R
import com.exalture.atm.databinding.PinNumberFragmentBinding
import com.poovam.pinedittextfield.PinField.OnTextCompleteListener
import com.poovam.pinedittextfield.SquarePinField


class PinNumberFragment : Fragment() {
    companion object {
        const val PIN_AUTHENTICATED: String = "PIN_AUTHENTICATED"
    }

    private lateinit var viewModel: PinNumberViewModel
    private lateinit var savedStateHandle: SavedStateHandle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: PinNumberFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.pin_number_fragment, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(PinNumberViewModel::class.java)
        binding.pinViewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.isPinVisible.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.editTextPin.inputType = InputType.TYPE_CLASS_NUMBER
                binding.visibility.text = getString(R.string.text_hide)
                binding.visibility.setCompoundDrawablesWithIntrinsicBounds(
                    ResourcesCompat.getDrawable(resources, R.drawable.icon_visibility_off, null),
                    null, null, null
                )
            } else {
                binding.editTextPin.inputType =
                    InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD
                binding.visibility.text = getString(R.string.text_show)
                binding.visibility.setCompoundDrawablesWithIntrinsicBounds(
                    ResourcesCompat.getDrawable(resources, R.drawable.icon_visibility, null),
                    null, null, null
                )
            }
        })

        val squarePinField: SquarePinField = binding.editTextPin
        squarePinField.onTextCompleteListener = object : OnTextCompleteListener {
            override fun onTextComplete(enteredText: String): Boolean {
                Log.i("Test", enteredText)
                if (enteredText.length == 4) {
                    viewModel.authenticated()
                    savedStateHandle.set(PIN_AUTHENTICATED, true)
                    findNavController().popBackStack()
                }
                return true // Return false to keep the keyboard open else return true to close the keyboard
            }
        }
        viewModel.navigateBack.observe(viewLifecycleOwner, Observer { proceedNavigation ->
            if (proceedNavigation) {
                findNavController().popBackStack()
                viewModel.doneNavigation()
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
        savedStateHandle.set(PIN_AUTHENTICATED, false)
    }
}