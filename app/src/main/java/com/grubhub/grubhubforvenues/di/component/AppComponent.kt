package com.grubhub.grubhubforvenues.di.component

import com.grubhub.grubhubforvenues.BaseApplication
import com.grubhub.grubhubforvenues.MainActivity
import com.grubhub.grubhubforvenues.di.module.AppModule
import com.grubhub.grubhubforvenues.di.module.DomainModule
import com.grubhub.grubhubforvenues.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DomainModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(application: BaseApplication)

    fun inject(activity: MainActivity)
}