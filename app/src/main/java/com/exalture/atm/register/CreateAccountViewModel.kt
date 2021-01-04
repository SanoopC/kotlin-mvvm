package com.exalture.atm.register

import android.annotation.SuppressLint
import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.exalture.atm.Config
import com.exalture.atm.database.AccountData
import com.exalture.atm.database.ExaltureDatabase
import com.exalture.atm.repository.AccountRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class CreateAccountViewModel(application: Application) :
    AndroidViewModel(application) {
    private val viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var newAccountNumber = 0L
    private var randomPin = ""

    var fullName = MutableLiveData<String>()
    var address = MutableLiveData<String>()
    var phoneNumber = MutableLiveData<String>()
    var amount = MutableLiveData<String>()

    private var _isSavings = MutableLiveData<Boolean>()
        .apply { value = true }

    val isSavingAccount: LiveData<Boolean>
        get() = _isSavings

    fun onAccountType(isSavings: Boolean) {
        _isSavings.postValue(isSavings)
    }

    private var _isRegistered = MutableLiveData<Boolean>()

    val isRegisteredNumber: LiveData<Boolean>
        get() = _isRegistered

    private var _navigateToSuccessDialog = MutableLiveData<AccountData>()

    val navigateToSuccessDialog: LiveData<AccountData>
        get() = _navigateToSuccessDialog

    fun doneNavigation() {
        _navigateToSuccessDialog.value = null
    }

    private var _navigateToaLanding = MutableLiveData<Boolean>()

    val navigateToaLanding: LiveData<Boolean>
        get() = _navigateToaLanding

    fun backNavigation() {
        _navigateToaLanding.value = true
    }

    private val accountRepository = AccountRepository(ExaltureDatabase.getInstance(application))

    init {
        initializeAccount()
        generateRandomPin()
    }

    private fun initializeAccount() {
        uiScope.launch {
            newAccountNumber =
                accountRepository.getRecentAccountNumber()?.plus(1) ?: Config.INITIAL_ACCOUNT_NUMBER
        }
    }

    @SuppressLint("DefaultLocale")
    private fun generateRandomPin() {
        randomPin = String.format("%04d", Random().nextInt(10000))
    }

    val formErrors = ObservableArrayList<FormErrors>()

    private fun isFormValid(): Boolean {
        formErrors.clear()
        if (fullName.value.isNullOrEmpty()) {
            formErrors.add(FormErrors.NAME_MISSING)
        } else if (fullName.value!!.trim().length < Config.MINIMUM_NAME_LENGTH) {
            formErrors.add(FormErrors.NAME_INVALID)
        } else if (address.value.isNullOrEmpty()) {
            formErrors.add(FormErrors.ADDRESS_MISSING)
        } else if (address.value!!.trim().length < Config.MINIMUM_ADDRESS_LENGTH) {
            formErrors.add(FormErrors.ADDRESS_INVALID)
        } else if (phoneNumber.value.isNullOrEmpty()) {
            formErrors.add(FormErrors.PHONE_MISSING)
        } else if (phoneNumber.value!!.trim().length < Config.MINIMUM_PHONE_LENGTH) {
            formErrors.add(FormErrors.PHONE_INVALID)
        } else if (!isSavingAccount.value!! && amount.value.isNullOrEmpty()) {
            formErrors.add(FormErrors.AMOUNT_MISSING)
        } else if (!isSavingAccount.value!! && amount.value!!.toInt() < Config.MINIMUM_CURRENT_ACCOUNT_BALANCE) {
            formErrors.add(FormErrors.AMOUNT_INVALID)
        }
        return formErrors.isEmpty()
    }

    fun onCreateAccountClick() {
        if (isFormValid()) {
            checkRegisteredOrNot()
        }
    }

    private fun checkRegisteredOrNot() {
        uiScope.launch {
            if (accountRepository.isPhoneRegistered(phoneNumber.value.toString())) {
                _isRegistered.postValue(true)
            } else {
                _isRegistered.postValue(false)
            }
        }
    }

    fun createAccount() {
        uiScope.launch {
            val newAccount = AccountData(
                newAccountNumber,
                randomPin,
                amount.value?.toDouble(),
                phoneNumber.value.toString(),
                fullName.value.toString(),
                address.value.toString()
            )
            accountRepository.insertAccount(newAccount)
            _navigateToSuccessDialog.value = newAccount
        }
    }

    enum class FormErrors {
        NAME_MISSING,
        NAME_INVALID,
        ADDRESS_MISSING,
        ADDRESS_INVALID,
        PHONE_MISSING,
        PHONE_INVALID,
        AMOUNT_MISSING,
        AMOUNT_INVALID
    }
}