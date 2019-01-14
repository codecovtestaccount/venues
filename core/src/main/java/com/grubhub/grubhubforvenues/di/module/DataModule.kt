package com.grubhub.grubhubforvenues.di.module

import com.grubhub.grubhubforvenues.domain.BackgroundScheduler
import com.grubhub.grubhubforvenues.domain.UseCaseScheduler
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module
class DataModule {

    @Provides
    @Named(BACKGROUND_SCHEDULER)
    fun useCaseScheduler(@Named(IO_SCHEDULER) ioScheduler: Scheduler, @Named(UI_SCHEDULER) uiScheduler: Scheduler): UseCaseScheduler {
        return BackgroundScheduler(ioScheduler, uiScheduler)
    }

    @Provides
    @Named(IO_SCHEDULER)
    fun ioScheduler(): Scheduler {
        return Schedulers.io()
    }

    @Provides
    @Named(UI_SCHEDULER)
    fun uiScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    companion object {
        const val BACKGROUND_SCHEDULER = "DataModule/BACKGROUND_SCHEDULER"
        const val IO_SCHEDULER = "DataModule/IO_SCHEDULER"
        const val UI_SCHEDULER = "DataModule/UI_SCHEDULER"
    }
}