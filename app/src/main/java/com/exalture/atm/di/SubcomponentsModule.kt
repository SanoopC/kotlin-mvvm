package com.exalture.atm.di

import com.exalture.atm.about.di.AboutComponent
import com.exalture.atm.details.di.DetailsComponent
import com.exalture.atm.register.di.RegisterComponent
import dagger.Module

@Module(
    subcomponents = [AboutComponent::class, DetailsComponent::class, RegisterComponent::class]
)
class SubcomponentsModule