package com.grubhub.grubhubforvenues.search.domain

import com.grubhub.grubhubforvenues.domain.NoParamObservableUseCase
import com.grubhub.grubhubforvenues.search.data.IEventRepository
import com.grubhub.venuesapi.model.EventResponseModel
import io.reactivex.Single

class FetchEventListUseCase constructor(private val eventRepository: IEventRepository) : NoParamObservableUseCase<List<EventResponseModel>> {
    override fun build(): Single<List<EventResponseModel>> {
        return eventRepository.getEvents()
    }
}