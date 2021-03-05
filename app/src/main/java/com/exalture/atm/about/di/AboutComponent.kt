package com.exalture.atm.about.di

import com.exalture.atm.about.AboutFragment
import com.exalture.atm.di.ActivityScope
import com.exalture.atm.details.di.DetailsModule
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface AboutComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AboutComponent
    }

    fun inject(fragment: AboutFragment)
}