package com.grubhub.grubhubforvenues

import android.app.Activity
import android.app.Application
import com.grubhub.grubhubforvenues.di.component.AppComponent
import com.grubhub.grubhubforvenues.di.component.DaggerAppComponent

class BaseApplication: Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
            .build()

        component.inject(this)
    }

    companion object {
        fun getAppComponent(activity: Activity) =
            (activity.application as BaseApplication).component
    }
}