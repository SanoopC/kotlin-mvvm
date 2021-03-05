package com.exalture.atm.account.di

import androidx.lifecycle.ViewModel
import com.exalture.atm.account.AccountDetailsViewModel
import com.exalture.atm.details.DetailViewModel
import com.exalture.atm.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AccountDetailsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AccountDetailsViewModel::class)
    abstract fun bindViewModel(viewModel: AccountDetailsViewModel): ViewModel
}