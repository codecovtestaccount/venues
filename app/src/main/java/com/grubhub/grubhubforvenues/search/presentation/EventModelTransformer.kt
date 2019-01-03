package com.grubhub.grubhubforvenues.search.presentation

import com.grubhub.venuesapi.model.EventResponseModel
import javax.inject.Inject

class EventModelTransformer @Inject constructor() {

    fun transform(events: List<EventResponseModel>): List<EventModel> {
        var e : ArrayList<EventModel> = arrayListOf()

        for (model in events) {
            e.add(EventModel(model.name, model.performerHeroImage, model.dateDisplay))
        }

        return e
    }
}