package com.grubhub.grubhubforvenues.di.module

import com.grubhub.venuesapi.service.VenueService
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun provideVenueApiService(): VenueService {
        return VenueService.create()
    }
}