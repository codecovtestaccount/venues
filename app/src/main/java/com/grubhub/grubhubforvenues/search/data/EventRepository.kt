package com.grubhub.grubhubforvenues.search.data

import com.grubhub.venuesapi.model.EventResponseModel
import com.grubhub.venuesapi.service.VenueService
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class EventRepository
@Inject constructor() {

    @Inject lateinit var venueService: VenueService

    fun getEvents(): Single<List<EventResponseModel>> {
        return venueService.events(true)
    }
}