package com.exalture.atm.pin

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.exalture.atm.R
import com.exalture.atm.databinding.PinNumberFragmentBinding
import com.poovam.pinedittextfield.PinField.OnTextCompleteListener
import com.poovam.pinedittextfield.SquarePinField
import kotlinx.android.synthetic.main.pin_number_fragment.*


class PinNumberFragment : Fragment() {

    private lateinit var viewModel: PinNumberViewModel
    private lateinit var binding: PinNumberFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.pin_number_fragment, container, false)
        viewModel = ViewModelProvider(this).get(PinNumberViewModel::class.java)
        binding.pinViewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.isPinVisible.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                editTextPin.inputType = InputType.TYPE_CLASS_NUMBER
                visibility.text = getString(R.string.text_hide)
                visibility.setCompoundDrawablesWithIntrinsicBounds(
                    context?.resources?.getDrawable(
                        R.drawable.icon_visibility_off,
                        null
                    ), null, null, null
                )
            } else {
                editTextPin.inputType =
                    InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD
                visibility.text = getString(R.string.text_show)
                visibility.setCompoundDrawablesWithIntrinsicBounds(
                    context?.resources?.getDrawable(
                        R.drawable.icon_visibility,
                        null
                    ), null, null, null
                )
            }
        })


        val squarePinField: SquarePinField = binding.editTextPin
        squarePinField.onTextCompleteListener = object : OnTextCompleteListener {
            override fun onTextComplete(enteredText: String): Boolean {
                Log.i("Test", enteredText)
                return true // Return false to keep the keyboard open else return true to close the keyboard
            }
        }
        return binding.root
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(PinNumberViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}