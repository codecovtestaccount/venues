package com.grubhub.grubhubforvenues.di.component

import com.grubhub.grubhubforvenues.BaseApplication
import com.grubhub.grubhubforvenues.search.presentation.MainActivity
import com.grubhub.grubhubforvenues.di.module.AppModule
import com.grubhub.grubhubforvenues.di.module.NetworkModule
import com.grubhub.grubhubforvenues.di.module.SearchModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, SearchModule::class])
interface AppComponent {
    fun inject(application: BaseApplication)

    fun inject(activity: MainActivity)
}