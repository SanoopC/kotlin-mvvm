package com.exalture.atm.details.di

import androidx.lifecycle.ViewModel
import com.exalture.atm.details.DetailViewModel
import com.exalture.atm.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailsModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel
}