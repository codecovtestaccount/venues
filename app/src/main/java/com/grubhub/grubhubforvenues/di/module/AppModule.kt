package com.grubhub.grubhubforvenues.di.module

import android.content.Context
import com.grubhub.grubhubforvenues.BaseApplication
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val baseApplication: BaseApplication) {

    @Provides
    @Singleton
    fun providesApplication(): BaseApplication {
        return baseApplication
    }
}