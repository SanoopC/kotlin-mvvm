package com.exalture.atm.register.di

import com.exalture.atm.register.CreateAccountFragment
import dagger.Subcomponent

@Subcomponent
interface RegisterComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): RegisterComponent
    }

    fun inject(fragment: CreateAccountFragment)
}