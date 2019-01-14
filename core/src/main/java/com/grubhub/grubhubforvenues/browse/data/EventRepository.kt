package com.grubhub.grubhubforvenues.browse.data

import com.grubhub.venuesapi.model.EventResponseModel
import com.grubhub.venuesapi.service.VenueService
import io.reactivex.Single
import javax.inject.Inject

class EventRepository
@Inject constructor(var venueService: VenueService): IEventRepository {

    override fun getEvents(): Single<List<EventResponseModel>> {
        return venueService.events(true)
    }
}