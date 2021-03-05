package com.exalture.atm.account.di

import com.exalture.atm.account.AccountDetailsFragment
import dagger.Subcomponent

@Subcomponent(modules = [AccountDetailsModule::class])
interface AccountDetailsComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): AccountDetailsComponent
    }

    fun inject(fragment: AccountDetailsFragment)
}