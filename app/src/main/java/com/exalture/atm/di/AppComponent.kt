package com.exalture.atm.di

import android.content.Context
import com.exalture.atm.about.di.AboutComponent
import com.exalture.atm.account.di.AccountDetailsComponent
import com.exalture.atm.details.di.DetailsComponent
import com.exalture.atm.landing.di.LandingComponent
import com.exalture.atm.register.di.RegisterComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelFactoryModule::class, SubcomponentsModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun aboutComponent(): AboutComponent.Factory
    fun detailsComponent(): DetailsComponent.Factory
    fun registrationComponent(): RegisterComponent.Factory
    fun landingComponent(): LandingComponent.Factory
    fun accountDetailsComponent(): AccountDetailsComponent.Factory
}