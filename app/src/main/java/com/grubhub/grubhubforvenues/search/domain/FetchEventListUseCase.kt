package com.grubhub.grubhubforvenues.search.domain

import com.grubhub.grubhubforvenues.domain.NoParamObservableUseCase
import com.grubhub.grubhubforvenues.search.data.EventRepository
import com.grubhub.venuesapi.model.EventResponseModel
import io.reactivex.Single
import javax.inject.Inject

class FetchEventListUseCase
@Inject constructor(private val eventRepository: IEventRepository) : NoParamObservableUseCase<List<EventResponseModel>> {
    override fun build(): Single<List<EventResponseModel>> {
        return eventRepository.getEvents()
    }
}