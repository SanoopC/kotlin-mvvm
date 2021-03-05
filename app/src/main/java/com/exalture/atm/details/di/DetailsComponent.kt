package com.exalture.atm.details.di

import com.exalture.atm.details.DetailFragment
import com.exalture.atm.di.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [DetailsModule::class])
interface DetailsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailsComponent
    }

    fun inject(fragment: DetailFragment)
}