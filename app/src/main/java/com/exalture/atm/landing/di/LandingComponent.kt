package com.exalture.atm.landing.di

import com.exalture.atm.landing.LandingFragment
import dagger.Subcomponent

@Subcomponent
interface LandingComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): LandingComponent
    }

    fun inject(fragment: LandingFragment)
}