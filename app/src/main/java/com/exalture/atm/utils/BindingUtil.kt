package com.exalture.atm.utils

import android.graphics.Color
import android.text.TextUtils
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.exalture.atm.Config
import com.exalture.atm.R
import com.exalture.atm.about.ApiStatus
import com.exalture.atm.about.PhotoGridAdapter
import com.exalture.atm.database.AccountData
import com.exalture.atm.network.ExaltureProjects
import com.exalture.atm.register.CreateAccountViewModel
import com.exalture.atm.statement.MiniStatementFragment
import com.google.android.material.textfield.TextInputEditText


@BindingAdapter("transactionId")
fun TextView.setTransactionId(item: MiniStatementFragment.Transaction) {
    item.let {
        text = item.transactionId.toString()
    }
}

@BindingAdapter("transactionToAccount")
fun TextView.setTransactionAccount(item: MiniStatementFragment.Transaction) {
    item.let {
        if (item.transactionType == Config.TYPE_TRANSFER) {
            text = item.transactionToAccount.toString()
        }
    }
}

@BindingAdapter("accountViewVisibility")
fun ConstraintLayout.setToAccountVisibility(item: MiniStatementFragment.Transaction) {
    item.let {
        if (item.transactionType != Config.TYPE_TRANSFER) {
            visibility = GONE
        }
    }
}

@BindingAdapter("transactionDateFormatted")
fun TextView.setTransactionDateFormatted(item: MiniStatementFragment.Transaction) {
    item.let {
        text = convertLongToDateString(item.transactionDate)
    }
}

@BindingAdapter("transactionTimeFormatted")
fun TextView.setTransactionTimeFormatted(item: MiniStatementFragment.Transaction) {
    item.let {
        text = convertLongToTimeString(item.transactionDate)
    }
}

@BindingAdapter("transactionRemarks")
fun TextView.setTransactionRemarks(item: MiniStatementFragment.Transaction) {
    item.let {
        text = item.transactionRemarks
    }
}

@BindingAdapter("transactionAmount")
fun TextView.setTransactionAmount(item: MiniStatementFragment.Transaction) {
    item.let {
        text = item.transactionAmount
    }
}

@BindingAdapter("transactionType")
fun TextView.setTransactionType(item: MiniStatementFragment.Transaction) {
    item.let {
        text = if (item.transactionType == Config.TYPE_DEPOSIT) {
            setTextColor(Color.GREEN)
            context.getString(R.string.text_dr)
        } else {
            setTextColor(Color.RED)
            context.getString(R.string.text_cr)
        }
    }
}

@BindingAdapter("transactionIcon")
fun ImageView.setTransactionIcon(item: MiniStatementFragment.Transaction) {
    setImageResource(
        when (item.transactionType) {
            Config.TYPE_DEPOSIT -> R.drawable.ic_deposit
            Config.TYPE_WITHDRAW -> R.drawable.ic_withdraw
            else -> R.drawable.ic_transfer
        }
    )
}

@BindingAdapter("nameValidator", "formErrors")
fun setNameValidator(
    editText: TextInputEditText,
    fullName: String?,
    formErrors: ObservableArrayList<CreateAccountViewModel.FormErrors>
) {
    // ignore infinite loops
    if (TextUtils.isEmpty(fullName) && formErrors.contains(CreateAccountViewModel.FormErrors.NAME_MISSING)) {
        editText.error = editText.resources.getString(R.string.error_required_validation)
        editText.requestFocus()
    } else if (TextUtils.isEmpty(fullName)) {
        editText.error = null
        return
    } else if (editText.text.toString().trim().length < Config.MINIMUM_NAME_LENGTH) {
        editText.error = editText.resources.getString(R.string.error_name_validation)
        editText.requestFocus()
    } else editText.error = null
}

@BindingAdapter("addressValidator", "formErrors")
fun setAddressValidator(
    editText: TextInputEditText, address: String?,
    formErrors: ObservableArrayList<CreateAccountViewModel.FormErrors>
) {
    // ignore infinite loops
    if (TextUtils.isEmpty(address) && formErrors.contains(CreateAccountViewModel.FormErrors.ADDRESS_MISSING)) {
        editText.error = editText.resources.getString(R.string.error_required_validation)
        editText.requestFocus()
    } else if (TextUtils.isEmpty(address)) {
        editText.error = null
        return
    } else if (editText.text.toString().trim().length < Config.MINIMUM_ADDRESS_LENGTH) {
        editText.error = editText.resources.getString(R.string.error_address_validation)
        editText.requestFocus()
    } else editText.error = null
}

@BindingAdapter("phoneValidator", "formErrors")
fun setPhoneValidator(
    editText: TextInputEditText, phoneNumber: String?,
    formErrors: ObservableArrayList<CreateAccountViewModel.FormErrors>
) {
    // ignore infinite loops
    if (TextUtils.isEmpty(phoneNumber) && formErrors.contains(CreateAccountViewModel.FormErrors.PHONE_MISSING)) {
        editText.error = editText.resources.getString(R.string.error_required_validation)
        editText.requestFocus()
    } else if (TextUtils.isEmpty(phoneNumber)) {
        editText.error = null
        return
    } else if (editText.text.toString().trim().length < Config.MINIMUM_PHONE_LENGTH) {
        editText.error = editText.resources.getString(R.string.error_phone_validation)
        editText.requestFocus()
    } else editText.error = null
}

@BindingAdapter("amountValidator", "isSavings", "formErrors")
fun setAmountValidator(
    editText: TextInputEditText, amount: String?, isSavings: Boolean?,
    formErrors: ObservableArrayList<CreateAccountViewModel.FormErrors>
) {
    // ignore infinite loops
    if (!isSavings!! && TextUtils.isEmpty(amount) && formErrors.contains(CreateAccountViewModel.FormErrors.AMOUNT_MISSING)) {
        editText.error = editText.resources.getString(R.string.error_required_validation)
        editText.requestFocus()
    } else if (TextUtils.isEmpty(amount)) {
        editText.error = null
        return
    } else if (!isSavings && Integer.valueOf(editText.text.toString()) < Config.MINIMUM_CURRENT_ACCOUNT_BALANCE) {
        editText.error = editText.resources.getString(R.string.error_opening_amount_validation)
        editText.requestFocus()
    } else editText.error = null
}

@BindingAdapter("accountNumber")
fun TextView.setAccountNumber(item: AccountData) {
    item.let {
        text = item.accountNumber.toString()
    }
}

@BindingAdapter("temporaryPin")
fun TextView.setTemporaryPin(item: AccountData) {
    item.let {
        text = item.pin
    }
}

@BindingAdapter("customOnEditorActionListener")
fun setCustomOnEditorActionListener(view: TextView, listener: CustomOnEditorActionListener?) {
    if (listener == null) {
        view.setOnEditorActionListener(null)
    } else {
        view.setOnEditorActionListener { _, _, _ ->
            listener.onEditorAction()
            false
        }
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("http").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
//                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<ExaltureProjects>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            statusImageView.visibility = VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.ERROR -> {
            statusImageView.visibility = VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ApiStatus.DONE -> {
            statusImageView.visibility = GONE
        }
    }
}