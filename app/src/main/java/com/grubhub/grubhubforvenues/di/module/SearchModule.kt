package com.grubhub.grubhubforvenues.di.module

import com.grubhub.grubhubforvenues.search.data.EventRepository
import com.grubhub.grubhubforvenues.search.domain.IEventRepository
import com.grubhub.grubhubforvenues.search.presentation.EventModelTransformer
import com.grubhub.grubhubforvenues.search.presentation.IEventModelTransformer
import com.grubhub.venuesapi.service.VenueService
import dagger.Module
import dagger.Provides

@Module
class SearchModule {

    @Provides
    fun eventRepository(venueService: VenueService): IEventRepository {
        return EventRepository(venueService)
    }

    @Provides
    fun eventTransformer(): IEventModelTransformer {
        return EventModelTransformer()
    }
}