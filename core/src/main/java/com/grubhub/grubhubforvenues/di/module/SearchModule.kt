package com.grubhub.grubhubforvenues.di.module

import com.grubhub.grubhubforvenues.domain.NoParamObservableUseCase
import com.grubhub.grubhubforvenues.browse.data.EventRepository
import com.grubhub.grubhubforvenues.browse.domain.FetchEventListUseCase
import com.grubhub.grubhubforvenues.browse.data.IEventRepository
import com.grubhub.grubhubforvenues.browse.presentation.EventModelTransformer
import com.grubhub.grubhubforvenues.browse.presentation.IEventModelTransformer
import com.grubhub.venuesapi.model.EventResponseModel
import com.grubhub.venuesapi.service.VenueService
import dagger.Module
import dagger.Provides
import javax.inject.Named

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

    @Provides
    @Named(FETCH_EVENT_LIST_USE_CASE)
    fun fetchEventListUseCase(eventRepository: IEventRepository): NoParamObservableUseCase<List<EventResponseModel>> {
        return FetchEventListUseCase(eventRepository)
    }

    companion object {
        const val FETCH_EVENT_LIST_USE_CASE = "FetchEventListUseCase"
    }
}